package com.jzpz.test;

import org.junit.Test;

import java.io.*;

/**
 * Created by Administrator on 2016/12/15.
 */
public class ShallowClone implements Cloneable, Serializable {
    private int id;
    private String name;
    //存在引用person类
    private Person person;

    public ShallowClone(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object deepClone() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        //从流里读出来
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bais);
        return (oi.readObject());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public static void main(String []args) throws Exception {
        //创建ShallowClone对象，设置值
        ShallowClone shallowClone = new ShallowClone(1, "Ay");
        Person person = new Person(1, "A1");
        shallowClone.setPerson(person);
        //对ShallowClone对象进行深复制，得到deepClone
        ShallowClone deepClone = (ShallowClone) shallowClone.deepClone();
        System.out.println(deepClone.getId() + " " + deepClone.getName());
        //result:1 Ay
        System.out.println(deepClone.getPerson().getId() + " " + deepClone.getPerson().getName());
        //result:1 A1
        shallowClone.getPerson().setId(2);
        shallowClone.getPerson().setName("Al2");
        //无论对ShallowClone中的person如何修改值，都不影响deepClone，因为深复制呗
        System.out.println(deepClone.getId() + " " + deepClone.getName());
        //result:1 Ay
        System.out.println(deepClone.getPerson().getId() + " " + deepClone.getPerson().getName());
        //result:1 A1

        System.out.println(person.getClass().getDeclaredAnnotations());
        System.out.println(deepClone.getClass().getDeclaredAnnotations());
        System.out.println("+++++");
        System.out.println(person.getClass().getDeclaredClasses());
        System.out.println(deepClone.getClass().getDeclaredClasses());
        System.out.println(person.equals(deepClone));
    }
}

class Person implements Serializable {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}