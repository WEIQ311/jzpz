package com.jzpz.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * 利用Java实现搜索引擎爬虫技术
 */
public class DataDownUtil {
    /**
     * 根据网址和页面的编码集获取网页的源代码
     * @author js
     * @param url 需要下载源代码的网址
     * @param encoding 页面的编码集
     * @return String 页面的源代码
     *
     */
    public static String getHtmlResourceByUrl(String url,String encoding){
        //存储源代码的容器
        StringBuffer buffer = new StringBuffer();
        URL urlObj =null;
        URLConnection uc = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            //建立网络连接
            urlObj = new URL(url);
            //打开网络连接
            uc = urlObj.openConnection();
            //建立文件的写入流
            isr = new InputStreamReader(uc.getInputStream(),encoding);
            //建立缓冲写入流，内存读取速度快，是为了加快速度
            reader = new BufferedReader(isr);
            String temp = null;
            while ((temp = reader.readLine())!=null ) {
                buffer.append(temp+"\n");  //一边读，一边写
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("您的网络连接失败...");
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开网络连接失败...");
        }finally{
            if(isr!=null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 根据图片的url地址，下载图片到服务器
     * @author js
     * @param filepath 文件存放路径
     * @param imageUrl 图片url
     * @return void
     */
    public static void downImage(String filepath,String imageUrl){
        String fileName = imageUrl.substring(imageUrl.lastIndexOf("/"));
        //创建一个文件目录
        try {
            File files = new File(filepath);
            if(!files.exists()){
                files.mkdirs();
            }
            //获取下载链接
            URL url = new URL(imageUrl);
            //连接网络地址
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            //获取连接的输出流
            InputStream is = connection.getInputStream();
            //创建文件
            File file = new File(filepath+fileName);
            //建立输入流，写入文件
            FileOutputStream fos = new FileOutputStream(file);
            int temp = 0;
            while((temp = is.read())!=-1){
                fos.write(temp);
            }
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Java入口
    public static void main(String[] args){
       //根据网址和页面的编码集获取网页的源代码
        String url = "http://m.blog.csdn.net/john_bian/article/details/74502323?from=singlemessage&isappinstalled=1";
        String encoding = "UTF-8";
        String htmlResour = getHtmlResourceByUrl(url,encoding);
        System.out.println(htmlResour);
        //解析源代码
        Document document = Jsoup.parse(htmlResour);
        //获取所有图片的地址<img src="" alt="" width="" height=""/>
        Elements elements = document.getElementsByTag("img");
        for(Element element:elements){
            String imgSrc = element.attr("src");
            System.out.println("网络图片的地址:"+imgSrc);
            if(StringUtils.isBlank(imgSrc)&&imgSrc.startsWith("http://")) {
            }
            {
                System.out.println("正在批量下载图片..."+imgSrc);
                downImage("F:\\Ksoftware", imgSrc);
            }
        }
        //返回进行操作

    }
}
