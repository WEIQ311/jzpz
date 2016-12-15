package com.jzpz.test;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/15.
 */
public class MapTest implements Cloneable, Serializable {

    public static <T extends Cloneable,Serializable> T  deepClone(T obj) throws Exception {
        T clonedObj = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        //从流里读出来
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bais);
        clonedObj = (T) oi.readObject();
        return clonedObj;
    }

    @Test
    public void testObjectClone() throws InstantiationException, IllegalAccessException {
       String str = "ssss";
       //String newStr = MapTest.deepClone(str);
    }

    @Test
    public void testClone() throws Exception {
        HashMap<String, String> oldMap = new HashMap<>();
        oldMap.put("name", "value");
        HashMap<String, String> newMap =MapTest.deepClone(oldMap);
        System.out.println(oldMap == newMap);
        System.out.println(oldMap.getClass().getDeclaredAnnotations());
        System.out.println(newMap.getClass().getDeclaredClasses());
    }



    @Test
    public void mapTest() throws InstantiationException, IllegalAccessException {
        Map<String, String> oldMaps = new HashMap<>();
        oldMaps.put("name", "value");
        HashMap<String, String> oldMap = new HashMap<>();
        oldMap.put("name", "value");
        Map<String, String> newMap = new HashMap<>();
        //newMap = (Map<String, String>) cloneObject(oldMap);
        newMap.putAll(oldMap);
        Map<String, String> newMap1 = newMap;
        System.out.println(newMap1==oldMap);
        System.out.println("+++++");
        Map<String,String> cloneMap = clone(oldMap);
        System.out.println(newMap.getClass().getDeclaredClasses());
        System.out.println(oldMap.getClass().getDeclaredClasses());
        System.out.println(cloneMap.getClass().getDeclaredClasses());
        System.out.println(cloneMap==oldMap);
        System.out.println(cloneMap.hashCode()+"\n"+oldMap.hashCode()+"\n"+newMap.hashCode()+"\n"+oldMaps.hashCode());
        System.out.println("---------");
        System.out.println(Integer.toHexString(cloneMap.hashCode())+"\n"+Integer.toHexString(oldMap.hashCode())+"\n"+newMap.hashCode()+"\n"+Integer.toHexString(oldMaps.hashCode()));
        System.out.println("---------");
        System.out.println(Integer.toHexString("{name=value}".hashCode()));

    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T clone(T obj) {
        T clonedObj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            clonedObj = (T) ois.readObject();
            ois.close();
        }catch (Exception e){
            e.printStackTrace();

        }
        return clonedObj;
        }

        public static Object cloneObject(Object o) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        if (null == o)
            return null;
// 使用Map保存原对象和副本对象之间的结构，防止被多次引用的对象重复重建
        Map<Object, Object> map = new HashMap<Object, Object>();
        return cloneObject(o, map);
    }

    private static Object cloneObject(Object o, Map<Object, Object> map)
            throws IllegalArgumentException, IllegalAccessException,
            InstantiationException {
        if (null == o)
            return null;
        Object newInstance = null;
        newInstance = map.get(o);
        if (null != newInstance) {
            return newInstance;
        }
        if (isSimpleObject(o))
            return o;
// 数组类型
        if (o.getClass().isArray()) {
            return cloneArray(o, map);
        }
        Class<?> type = o.getClass();
        newInstance = type.newInstance();
        map.put(o, newInstance);
        cloneFields(o, newInstance, map);
        return newInstance;
    }

    private static Object cloneArray(Object o, Map<Object, Object> map) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        if (null == o)
            return null;
        if (!o.getClass().isArray()) {
            return cloneObject(o, map);
        }
        int len = Array.getLength(o);
        Object array = Array.newInstance(o.getClass().getComponentType(), len);
        map.put(o, array);
        for (int i = 0; i < len; i++) {
            Array.set(array, i, cloneObject(Array.get(o, i), map));
        }
        return array;
    }

    private static void cloneFinalObject(Object object, Object newObject, Map<Object, Object> map) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        if (object == null || newObject == null || object == newObject || !newObject.getClass().equals(newObject.getClass()))
            return;
// 对于final类型的变量
        if (null != map.get(newObject)) {
            return;
        }
        map.put(newObject, newObject);
        cloneFields(object, newObject, map);
        return;
    }

    private static void cloneFields(Object object, Object newObject,Map<Object, Object> map) throws SecurityException,
            IllegalArgumentException, IllegalAccessException,
            InstantiationException {
        if (null == object || null == newObject) {
            return;
        }
        List<Field> fields = getAllFieads(object);
        for (Field f : fields) {
// 静态变量过滤掉 或者final的变量
            if (Modifier.isStatic(f.getModifiers()))
                continue;
// 常量
            if (Modifier.isFinal(f.getModifiers())) {
                cloneFinalObject(f.get(object), f.get(newObject), map);
            } else {
                f.setAccessible(true);
                f.set(newObject, cloneObject(f.get(object), map));
            }
        }
    }

    private static List<Field> getAllFieads(Object o) {
        List<Field> fields = new ArrayList<Field>();
        if (null == o)
            return fields;
        Class<?> type = o.getClass();
        do {
            for (Field f : type.getDeclaredFields()) {
                fields.add(f);
            }
            type = type.getSuperclass();
        } while (null != type);
        return fields;
    }

    public static boolean isSimpleObject(Object o) {
        Class<?> type = o.getClass();
        if (type.isPrimitive()) { // 基本类型
            return true;
        }
// 不可更改的变量类型 如 String，Long
        if (type.equals(String.class))
            return true;
        if (type.equals(Long.class))
            return true;
        if (type.equals(Boolean.class))
            return true;
        if (type.equals(Short.class))
            return true;
        if (type.equals(Integer.class))
            return true;
        if (type.equals(Character.class))
            return true;
        if (type.equals(Float.class))
            return true;
        if (type.equals(Double.class))
            return true;
        if (type.equals(Byte.class))
            return true;
        return false;
    }
}
