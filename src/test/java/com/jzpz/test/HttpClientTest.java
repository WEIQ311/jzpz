package com.jzpz.test;

import com.jzpz.util.HttpClientUtils;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/28.
 */
public class HttpClientTest {

    @Test
    public void getTest(){
        /**
         * maven引用
         *
         * <dependency>
         <groupId>commons-httpclient</groupId>
         <artifactId>commons-httpclient</artifactId>
         <version>3.1</version>
         </dependency>
         */
        //发生请求的参数为map
        //get请求可以有超时时间
        String result = HttpClientUtils.httpGetClientServer("http://localhost:5253/jzpz/http/testGet", new HashMap<String, String>() {{
            put("key", "thisKey");
            put("value", "thisValue");
        }});
        //post请求可以加超时时间
        //对于有些系统不能接受setRequestBody传参时，请使用setRequestEntity方式，具体是在调用时设置postParamType为false即为setRequestEntity
        String postResult = HttpClientUtils.httpPostClientServer("http://localhost:5253/jzpz/http/testGet", new HashMap<String, String>() {{
            put("key", "thisKey");
            put("value", "thisValue");
        }});
        String postEntityResult = HttpClientUtils.httpPostClientServer("http://localhost:5253/jzpz/http/testGet", new HashMap<String, String>() {{
            put("key", "thisKey");
            put("value", "thisValue");
        }}, false);
        System.out.println(result);
        System.out.println(postResult);
        System.out.println(postEntityResult);
    }
}
