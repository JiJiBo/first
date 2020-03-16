package com.rulerbug.first.Utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static String httpRequestData(String url, JSONObject obj) throws IOException {
        URL u;
        HttpURLConnection con;
        DataOutputStream osw;
        StringBuffer buffer = new StringBuffer();
        u = new URL(url);
        con = (HttpURLConnection) u.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setUseCaches(false);
        con.setInstanceFollowRedirects(true);
        con.setRequestProperty("Content-Type", "application/json");
        osw = new DataOutputStream(con.getOutputStream());
        osw.writeBytes(obj.toString());
        osw.flush();
        osw.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String temp;
        while ((temp = br.readLine()) != null) {
            buffer.append(temp);
            buffer.append('\n');
        }
        return buffer.toString();
    }

    public static String httpToBase64(String url) throws IOException {
        URL u;
        HttpURLConnection con;
        DataOutputStream osw;
        StringBuffer buffer = new StringBuffer();
        u = new URL(url);
        con = (HttpURLConnection) u.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setUseCaches(false);
        con.setInstanceFollowRedirects(true);
        con.setRequestProperty("Content-Type", "application/json");
        osw = new DataOutputStream(con.getOutputStream());
        osw.flush();
        osw.close();

        InputStream inputStream = con.getInputStream();

//        FileInputStream fos2 = new FileInputStream(insToFile(inputStream));
        String base64FromInputStream = getBase64FromInputStream(inputStream);
        return base64FromInputStream;
    }

    public static String getBase64FromInputStream(InputStream in) throws IOException {

        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = in.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        data = swapStream.toByteArray();
        if (in != null) {
            in.close();
        }
        return new String(Base64.encodeBase64(data));
    }

//    public static File insToFile(InputStream inputStream) throws IOException {
//
//        File file = new File("e:\\1.png");
//        file.delete();
//        byte[] buffer = new byte[inputStream.available()];
//        inputStream.read(buffer);
//
//        OutputStream outStream = new FileOutputStream(file);
//        outStream.write(buffer);
//        return file;
//    }
}