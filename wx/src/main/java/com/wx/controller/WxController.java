package com.wx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;

/**
 * @author weiQiang
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/wx")
public class WxController {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Value("${redirect.uri}")
    private String redirectUri;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void userLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Map<String, String[]>  map = request.getParameterMap();
        map.forEach((key,value)->{
            System.out.println(key+"\t"+Arrays.deepToString(value));
        });
        String getCode = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri, "utf-8")+
                "&response_type=code&scope=snsapi_userinfo&state=SRATE#wechat_redirect";
        response.sendRedirect(getCode);
    }

    @RequestMapping(value = "callBack", method = RequestMethod.GET)
    public JSONObject index(String code) {
        JSONObject jsonObject = new JSONObject();
        System.out.println(code);
        jsonObject.put("code", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";
        String result = HttpClientUtils.httpGetClientServer(url, null);
        jsonObject = JSON.parseObject(result);
        String accessToken = "";
        String openId = "";
        if (jsonObject.containsKey("access_token") && jsonObject.containsKey("openid")) {
            accessToken = jsonObject.getString("access_token");
            openId = jsonObject.getString("openid");
        }
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken +
                "&openid=" + openId +
                "&lang=zh_CN";
        result = HttpClientUtils.httpGetClientServer(userInfoUrl, null);
        jsonObject = JSON.parseObject(result);
        return jsonObject;
    }
}
