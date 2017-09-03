package com.jzpz.util;

import com.jzpz.domain.HostConfig;
import com.jzpz.domain.HostInfo;
import com.jzpz.domain.Result;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/15.
 */
public class JzpzUtil {

    public static Pattern pattern = Pattern.compile("^[\\d]*$");

    public static Pattern ipPattern =  Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
            +"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

    public static String getBase64(InputStream inputStream){
        byte[] data = null;
        //读取图片字节数组
        try {
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

    /**
     * 批量生成hosts ip
     * @param hostConfig
     * @return
     */
    public static Result bathHost(HostConfig hostConfig){
        if(StringUtils.isBlank(hostConfig.ip)){
            return Result.builder().flag(false).message("ip不能为空!").build();
        }
        if(StringUtils.isBlank(hostConfig.num1)){
            return Result.builder().flag(false).message("第三段ip不能为空!").build();
        }
        if(StringUtils.isBlank(hostConfig.num2)){
            return Result.builder().flag(false).message("第四段ip不能为空!").build();
        }
        if(StringUtils.isBlank(hostConfig.name1)){
            return Result.builder().flag(false).message("主机不能为空!").build();
        }
        Result result = new Result();
        StringBuffer ipBuffer = new StringBuffer(hostConfig.ip);
        List<HostInfo> hostConfigs = new ArrayList<>();
        ipBuffer.append(hostConfig.ip.endsWith(".")?"":".");
        if(hostConfig.num1.indexOf("-")>0){
            String[] num1Limit = hostConfig.num1.split("-");
            if(num1Limit.length==2&&isNumber(num1Limit[0])&&isNumber(num1Limit[1])){
                if(Integer.parseInt(num1Limit[0])>Integer.parseInt(num1Limit[1])){
                    return Result.builder().flag(false).message("第三段ip范围不合法").build();
                }
                for(int i = Integer.parseInt(num1Limit[0]);i<=Integer.parseInt(num1Limit[1]);i++){
                    ipBuffer = new StringBuffer(hostConfig.ip).append(hostConfig.ip.endsWith(".")?"":".");
                    result = num2Compare(ipBuffer.toString(),String.valueOf(i),hostConfig,hostConfigs);
                    if(!result.isFlag()){
                        return result;
                    }
                }
            }else{
                return Result.builder().flag(false).message("第三段'"+hostConfig.num1+"'ip不合法").build();
            }
        }else{
            result = num2Compare(ipBuffer.toString(),hostConfig.num1,hostConfig,hostConfigs);
            if(!result.isFlag()){
                return result;
            }
        }
        hostConfigs.forEach(hostInfo -> {
            hostConfig.setRemark(hostConfig.remark);
            hostInfo.setUserName(hostConfig.userName);
            hostInfo.setPassWord(hostConfig.passWord);
            hostInfo.setPort(hostConfig.port);
            hostInfo.setInsertTime(new Date());
            hostInfo.setUpdateTime(new Date());
        });
        return Result.builder().flag(true).message("批量生成ip成功!").data(hostConfigs).build();
    }

    /**
     * 处理第四段ip
     * @param ip
     * @param num3 ip中的第三段
     * @param hostConfig
     * @param hostConfigs
     * @return
     */
    private static Result num2Compare(String ip, String num3, HostConfig hostConfig, List<HostInfo> hostConfigs) {
        if(hostConfig.num2.contains("-")){
            String[] num2Limit = hostConfig.num2.split("-");
            if(num2Limit.length==2&&isNumber(num2Limit[0])&&isNumber(num2Limit[1])){
                if(Integer.parseInt(num2Limit[0])>Integer.parseInt(num2Limit[1])){
                    return Result.builder().flag(false).message("第四段ip范围不合法").build();
                }
                for(int i = Integer.parseInt(num2Limit[0]);i<=Integer.parseInt(num2Limit[1]);i++){
                    StringBuffer stringBuffer = new StringBuffer(ip).append(num3).append(".").append(i);
                    String name = "";
                    if(StringUtils.isBlank(hostConfig.name2)){
                        name = hostConfig.name1.concat(hostConfig.num1.contains("-")?num3:"").concat(String.valueOf(i));
                    }else{
                        name = hostConfig.name1.concat(num3).concat(hostConfig.name2).concat(String.valueOf(i));
                    }
                    if(!ipPattern.matcher(stringBuffer.toString()).matches()){
                        return Result.builder().flag(false).message("生成的ip"+stringBuffer.toString()+"不合法").build();
                    }
                    hostConfigs.add(
                            HostInfo.builder().hostIp(stringBuffer.toString()).hostName(name).build());
                }
            }else{
                return Result.builder().flag(false).message("第四段'"+hostConfig.num2+"'ip不合法").build();
            }
        }else{
            ip = ip.concat(ip.endsWith(".")?"":".").concat(num3).concat(num3.endsWith(".")?"":".").concat(hostConfig.num2);
            String name = "";
            if(StringUtils.isBlank(hostConfig.name2)){
                name = hostConfig.name1.concat(hostConfig.num1.contains("-")?num3:"").concat(hostConfig.num2);
            }else{
                name = hostConfig.name1.concat(num3).concat(hostConfig.name2).concat(hostConfig.num2);
            }
            if(!ipPattern.matcher(ip).matches()){
                return Result.builder().flag(false).message("生成的ip"+ip+"不合法").build();
            }
            hostConfigs.add(HostInfo.builder().hostIp(ip).hostName(name).build());
        }
        return Result.builder().flag(true).data(hostConfigs).build();
    }

    /**
     * 是否是数字
     * @param num
     * @return
     */
    public static boolean isNumber(String num) {
        return pattern.matcher(num).matches();
    }
}
