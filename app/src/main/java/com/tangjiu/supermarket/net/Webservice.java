package com.tangjiu.supermarket.net;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket
 * @ClassName: Webservice
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/2 11:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/2 11:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Webservice {
    private static Webservice webservice;

    public static Webservice getInstance(Context content) {

        if (webservice == null) {
            webservice = new Webservice();
        }
        return webservice;

    }

    public void httpURLGetConnection(String soap, SoapCallback soapCallback) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://218.26.102.234:48612/WebService1.asmx/" + soap);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            connection.setRequestProperty("SOAPAction", "http://tempuri.org/" + soap);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                byte[] b = getBytesFromInputStream(connection.getInputStream());
                String back = new String(b);
                JSONObject jsonObject = new JSONObject(back);
                soapCallback.onResponseResult(jsonObject.getString("Result"));
            } else {
                soapCallback.onFailResult(connection.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    public void login(String soap, String pwd, String username, SoapCallback<String> soapCallback) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://218.26.102.234:48612/WebService1.asmx/" + soap);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            //设置请求属性
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("accept", "application/json");
            StringBuffer params = new StringBuffer();
            // 表单参数与get形式一样
            params.append("userno").append("=").append(username).append("&")
                    .append("pwd").append("=").append(pwd);
            byte[] bypes = params.toString().getBytes();
            connection.getOutputStream().write(bypes);
//            // 转换为字节数组  
//            connection.connect();
//            OutputStream out = new DataOutputStream(connection.getOutputStream());
//            // 写入请求的字符串  params
//            out.flush();
//            out.close();


            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                byte[] b = getBytesFromInputStream(connection.getInputStream());
                String back = new String(b);
                soapCallback.onResponseResult(back);
            } else {
                soapCallback.onFailResult(connection.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 从输入流获取数据
     *
     * @param in
     * @return
     * @throws IOException
     */
    private static byte[] getBytesFromInputStream(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = in.read(b)) != -1) {
            baos.write(b, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    /**
     * 向输入流发送数据
     *
     * @param
     * @param
     * @throws IOException
     */
    private static JSONObject getOutputStream(String userno, String pwd) throws IOException {
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("userno", userno);
            jsonParam.put("pwd", pwd);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonParam;

    }
}