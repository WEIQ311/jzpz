package com.jzpz.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weiQiang
 */
public class StudyPacking {

    static long[] a = new long[10];
    String str = new String("good");
    char[] ch = {'a', 'b', 'c'};

    private static String sendGet(String url) {
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        try {
            // 将string转成url对象
            URL realUrl = new URL(url);
            // 初始化一个链接到那个url的连接
            URLConnection connection = realUrl.openConnection();
            // 开始实际的连接
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null) {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    static String regexString(String targetStr, String patternStr) {
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        // 相当于埋好了陷阱匹配的地方就会掉下去
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        // 如果找到了
        if (matcher.find()) {
            // 打印出结果
            return matcher.group(1);
        }
        return "Nothing";
    }

    public static void mains(String[] args) {
        // 定义即将访问的链接
        String url = "http://www.ibeifeng.cn/page/vr.html";
        // 访问链接并获取页面内容
        String result = sendGet(url);
        System.out.println(result);
        // 使用正则匹配图片的src内容
        String imgSrc = regexString(result, "src=\"(.+?)\"");
        // 打印结果
        System.out.println(imgSrc);
    }

    public static void main(String args[]) {
        StudyPacking ex = new StudyPacking();
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + "and");
        System.out.print(ex.ch);
        System.out.println(a[6]);

        int a = -67, b = 116, c = 78;
        int d = ~a | b & c;
        System.out.println(~a);
        System.out.println("66|b&c:" + (66 | b & c));
        System.out.println(d);

    }

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] = 'g';
    }

}
