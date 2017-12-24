package com.wx.util;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WxUtil {

    public static final String OS_NAME = "linux";
    public static final String UNKNOWN = "unknown";

    /**
     * 判断是否是linux系统
     *
     * @return
     */
    public static boolean isOsLinux() {
        Properties properties = System.getProperties();
        String os = properties.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf(OS_NAME) > -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 一次性判断多个或单个对象是否为空
     *
     * @param objects
     * @return
     */
    public static Boolean isBlank(Object... objects) {
        if (null == objects || objects.length == 0) {
            return true;
        }
        Boolean result = false;
        for (Object object : objects) {
            if (null == object || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())
                    || "[null]".equals(object.toString().trim())
                    || "[]".equals(object.toString().trim())
                    || "{}".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 随机产生数字
     *
     * @param bound 位数
     * @return
     */
    public static Integer randomInt(Integer bound) {
        return new Random().nextInt(bound);
    }

    /**
     * 根据系统转换为windows格式或者linux格式
     *
     * @param path 路径
     * @return
     */
    public static String winOrLinuxPath(String path) {
        if (!isOsLinux()) {
            path = path.replace("/", "\\");
        }
        return path;
    }

    /**
     * 文件夹不存在就创建
     *
     * @param path 路径
     */
    public static void createFilePath(String path) {
        path = winOrLinuxPath(path);
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
    }

    /**
     * 资源转为base64
     *
     * @param
     * @return
     */

    public static String getBase64(String path) {
        String imgFile = path;
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }


    /**
     * 批量压缩文件
     *
     * @param files
     * @param out
     * @throws IOException
     */
    public static void doCompress(List<File> files, ZipOutputStream out) throws IOException {
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            if (file.exists()) {
                byte[] buffer = new byte[1024];
                FileInputStream fis = new FileInputStream(file);
                String fileName = file.getName();
                out.putNextEntry(new ZipEntry(fileName));
                int len = 0;
                // 读取文件的内容,打包到zip文件
                while ((len = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                fis.close();
                out.flush();
            }
        }
        out.close();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    /**
     * 下载文件
     *
     * @param path
     * @param fileName
     * @param response
     */
    public static void downLoadFile(String path, String fileName, HttpServletResponse response) {
        File file = new File(path + fileName);
        if (file.exists()) {
            BufferedInputStream bis = null;
            BufferedOutputStream out = null;
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
                bis = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[2048];
                while (true) {
                    int bytesRead;
                    if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) {
                        break;
                    }
                    out.write(buff, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }

    /**
     * 将集合转字符串用,并且每一项用单引号包裹
     *
     * @param list
     * @return
     */
    public static String joinStr(List<String> list) {
        String result = "";
        if (null != list && list.size() > 0) {
            List<String> stringList = new ArrayList<>();
            for (String str : list) {
                stringList.add("'" + str + "'");
            }
            result = String.join(",", stringList);
        }
        return result;
    }

    /**
     * https密钥
     */
    public static void pkcKey() {
        String[] commands = new String[]{
                "cmd",
                "/k",
                //cmd Shell命令
                "start",
                "keytool",
                //genkey表示生成密钥
                "-genkey",
                //别名
                "-alias", "tomcat",
                //store类型
                "-storetype", "PKCS12",
                //加密算法
                "-keyalg", "RSA",
                //密钥大小
                "-keysize", "2048",
                //key位置
                "-keystore", "D:/account.p12",
                //证书有效期(单位:天)
                "-validity", "3650",
                //密钥库密码,至少为6位
                "-storepass", "rootdzwl",
                //别名条目密码
                "-keypass", "rootdzwl",
                //CN=名字与姓氏,OU=组织单位名称,O=组织名称,L=城市或区域名 称,ST=州或省份名称,C=单位的两字母国家代码
                "-dname",
                "CN=(WQ),OU=(WQ),O=(WQ),L=(BJ),ST=(BJ),C=(CN)",
                //显示证书详情
                "-v"
        };
        try {
            execCommand(commands);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 执行cmd命令
     *
     * @param commands
     * @throws IOException
     */
    public static void execCommand(String... commands) throws IOException {
        Runtime.getRuntime().exec(commands);
    }
}
