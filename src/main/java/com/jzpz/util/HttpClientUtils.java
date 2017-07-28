package com.jzpz.util;

import net.sf.json.JSONSerializer;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * http请求工具类
 *
 * @author weiQiang
 * @date 2017/7/28
 * @email 1107438082@qq.com
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static final int timeout = 10 * 1000;

    /**
     * post请求
     *
     * @param url   url
     * @param param 请求参数
     * @return
     */
    public static String httpPostClientServer(String url, Map<String, String> param) {
        return httpClientServer(url, param, true, timeout, true);
    }

    /**
     * post请求
     *
     * @param url     url
     * @param param   请求参数
     * @param timeout 超时时间
     * @return
     */
    public static String httpPostClientServer(String url, Map<String, String> param, Integer timeout) {
        return httpClientServer(url, param, true, timeout, true);
    }

    /**
     * post请求
     *
     * @param url           url
     * @param param         请求参数
     * @param postParamType 发送post请求是否使用setRequestEntity默认为true，false为postParamType方式
     * @return
     */
    public static String httpPostClientServer(String url, Map<String, String> param, boolean postParamType) {
        return httpClientServer(url, param, true, timeout, postParamType);
    }

    /**
     * post请求
     *
     * @param url           url
     * @param param         请求参数
     * @param timeout       超时时间
     * @param postParamType 发送post请求是否使用setRequestEntity默认为true，false为postParamType方式
     * @return
     */
    public static String httpPostClientServer(String url, Map<String, String> param, Integer timeout, boolean postParamType) {
        return httpClientServer(url, param, true, timeout, postParamType);
    }

    /**
     * get请求
     *
     * @param url   url
     * @param param 请求参数
     * @return
     */
    public static String httpGetClientServer(String url, Map<String, String> param) {
        return httpClientServer(url, param, false, timeout);
    }

    /**
     * get请求
     *
     * @param url     url
     * @param param   请求参数
     * @param timeout 超时时间
     * @return
     */
    public static String httpGetClientServer(String url, Map<String, String> param, Integer timeout) {
        return httpClientServer(url, param, false, timeout);
    }

    /**
     * 发送请求
     *
     * @param url         url
     * @param param       请求参数
     * @param requestType 是否为post
     * @param timeout     超时时间
     * @return
     */
    public static String httpClientServer(String url, Map<String, String> param, boolean requestType, Integer timeout) {
        return httpClientServer(url, param, requestType, timeout, false);
    }

    /**
     * 发送http请求
     *
     * @param url           url
     * @param param         请求参数
     * @param requestType   是否为post
     * @param timeout       超时时间
     * @param postParamType post请求参数类型 发送post请求是否使用setRequestEntity默认为true，false为postParamType方式
     * @return
     */
    public static String httpClientServer(String url, Map<String, String> param, boolean requestType, Integer timeout, boolean postParamType) {
        long beginTime = System.currentTimeMillis();
        String result = "";
        if (null == param) {
            param = new HashMap<>();
        }
        logger.info("请求url:{},发送:{}请求,请求大小:{}条", url, requestType ? "post" : "get", param.size());
        if (StringUtils.isBlank(url)) {
            result = "请求url为空";
            return result;
        }
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = null;
        GetMethod getMethod = null;
        InputStream inputStream = null;
        if (requestType) {//post请求
            postMethod = new PostMethod(url);
            httpClient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, timeout);
            NameValuePair[] nameValuePair = new NameValuePair[param.size()];
            final int[] i = {0};
            param.forEach((key, value) -> {
                nameValuePair[i[0]] = new NameValuePair(key, value);
                i[0]++;
            });
            try {
                //一般接口用以上setRequestBody方法可以接受到值,但是有一些旧接口适用于setRequestEntity的方式接受post参数
                logger.info("post传参方式:{}", postParamType ? "setRequestBody" : "setRequestEntity");
                if (postParamType) {
                    postMethod.setRequestBody(nameValuePair);
                } else {
                    String postParam = JSONSerializer.toJSON(param).toString();
                    if (StringUtils.isNotBlank(postParam)) {
                        postMethod.setRequestEntity(new StringRequestEntity(postParam, "", "UTF-8"));
                    }
                }
                postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
                postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                httpClient.executeMethod(postMethod);
                inputStream = postMethod.getResponseBodyAsStream();
            } catch (IOException e) {
                logger.error("请求发生错误:{}", e.getMessage());
            } catch (Exception e) {
                logger.error("请求发生错误:{}", e.getMessage());
            }
        } else {//get请求
            StringBuffer getParamBuffer = new StringBuffer();
            param.forEach((key, value) -> getParamBuffer.append(key).append("=").append(value).append("&"));
            if (null != getParamBuffer && getParamBuffer.length() > 0) {
                getParamBuffer.deleteCharAt(getParamBuffer.length() - 1);
            }
            String getParam = getParamBuffer.toString();
            url += "?" + getParam;
            logger.info("Get请求url:{}", url);
            getMethod = new GetMethod(url);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            try {
                httpClient.executeMethod(getMethod);
                inputStream = getMethod.getResponseBodyAsStream();
            } catch (IOException e) {
                logger.error("请求发生错误:{}", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("请求发生错误:{}", e.getMessage());
            }
        }
        try {
            if (null == inputStream) {
                return result;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while (null != (line = bufferedReader.readLine())) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            logger.error("请求发生UnsupportedEncodingException错误:{}", e.getMessage());
        } catch (IOException e) {
            logger.error("请求发生错误:{}", e.getMessage());
        } catch (Exception e) {
            logger.error("请求发生错误:{}", e.getMessage());
        } finally {
            //释放连接
            if (null != postMethod) {
                postMethod.releaseConnection();
            }
            if (null != getMethod) {
                getMethod.releaseConnection();
            }
        }
        logger.info("请求结果大小:{}", result.length());
        long endTime = System.currentTimeMillis();
        logger.info("请求耗时:{}ms", (endTime - beginTime));
        return result;
    }
}
