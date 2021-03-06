package com.jzpz.test;

import com.jzpz.util.JsonValidator;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.*;

/**
 * Created by weiQiang on 2016/10/8.
 */
public class UserTest {
    @Test
    public void getPwd() {
        //bcrypt算法与md5/sha算法有一个很大的区别，每次生成的hash值都是不同的，
        // 这样暴力猜解起来或许要更困难一些。同时加密后的字符长度比较长，有60位，
        // 所以用户表中密码字段的长度，如果打算采用bcrypt加密存储，字段长度不得低于60.
        //TODO　疑问每次提交时比较密码是如何匹配的
        String password = "bonc";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
        System.out.println(hashedPassword.length());
        System.out.println("$2a$10$DhZBzfapM8CqY1I6gr6KSOiqTwKEhJbmYn5rSA3x6qZbV9E.Jnj.S".length());
    }

    @Test
    public void test() {
        List<Map<String, String>> listMap = getData();
        String incomes = "user_id-user_name";
        String spliteInfo = "-";
        setMapByCondition(listMap, incomes, spliteInfo);
    }

    private void setMapByCondition(List<Map<String, String>> listMap, String incomes, String spliteInfo) {
        String[] incomeValues = incomes.split(spliteInfo);
        List<Map<String, String>> tempList = new ArrayList<>();
        Map<String, List<Map<String, String>>> serverMap = new HashMap<>();
        for (Map<String, String> dataMap : listMap) {

            StringBuffer incomeKeyBf = new StringBuffer();
            for (String income : incomeValues) {
                if (dataMap.containsKey(income)) {
                    incomeKeyBf.append(dataMap.get(income) + spliteInfo);
                }
            }
            String incomeKey = incomeKeyBf.toString();
            incomeKey = incomeKey.endsWith(spliteInfo) ? incomeKey.substring(0, incomeKey.length() - spliteInfo.length()) : incomeKey;
            String[] incomeKeys = incomeKey.split(spliteInfo);
            //处理tempList
            for (int i = 0; i < incomeValues.length; i++) {
                //tempList = new ArrayList<>();
                int j = 1;
                for (Map tempMap : listMap) {
                    //incomeKeys的最后一个也满足
                    if (tempMap.containsKey(incomeValues[i]) && tempMap.get(incomeValues[i]).equals(incomeKeys[i]) && !tempList.contains(tempMap)) {
                        System.out.println(incomeValues[i] + ">>" + tempMap + "<<" + j);
                        //if(j==incomeValues.length){
                        tempList.add(tempMap);
                        //  }
                        j++;
                    }
                }
            }
            serverMap.put(incomeKey, tempList);

        }
        System.out.println(serverMap);
    }

    private List<Map<String, String>> getData() {
        List<Map<String, String>> listMap = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("user_id", "123");
        map.put("user_name", "123");
        map.put("user_pwd", "123");
        listMap.add(map);
        map = new HashMap<>();
        map.put("user_id", "234");
        map.put("user_name", "234");
        map.put("user_pwd", "234");
        listMap.add(map);
        map = new HashMap<>();
        map.put("user_id", "123");
        map.put("user_name", "123");
        map.put("user_pwd", "234");
        listMap.add(map);
        return listMap;
    }

    @Test
    public void base64Test() {
        String strImg = GetImageStr();
        System.out.println(strImg.replace("\r","").replace("\n",""));
        //GenerateImage(strImg);
    }

    //图片转化成base64字符串
    public static String GetImageStr() {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "d://Video123.wmv";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "d://222.jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void testJava8(){
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );
        System.out.println("----");
        Arrays.asList( "a", "b", "d" ).forEach( e -> {
            System.out.print( e );
            System.out.print( e );
        } );
        System.out.println("+++");
        String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> System.out.print( e + separator ) );
        System.out.println("))))))____");
        final String separators = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> System.out.print( e + separators ) );

        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );

        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );
    }

    @Test
    public void test26(){
        for (char i = 'a'; i <='z' ; i++) {
            System.out.print(i);
        }
        System.out.println();
        for (char i = 'A'; i <='Z' ; i++) {
            System.out.print(i);
        }
        System.out.println();
    }

    @Test
    public void chengfa(){
        for(int i =1;i<10;i++){
            for (int j =1 ;j<=i; j++) {
                System.out.print(j+"X"+i+"="+i*j+"\t");
            }
            System.out.println();
        }
    }

    @Test
    public void jsonValidatorTest(){
        String jsonStr = "{\"website\":\"oschina.net\"}";
        System.out.println(jsonStr + ":" + new JsonValidator().validate(jsonStr));
    }
}
