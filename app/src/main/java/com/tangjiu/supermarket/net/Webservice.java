package com.tangjiu.supermarket.net;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
            // setBytesToOutputStream(connection.getOutputStream(), soap.getBytes());
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
            connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
            connection.setRequestProperty("SOAPAction", "http://tempuri.org/" + soap);
            connection.connect();
            setBytesToOutputStream(connection.getOutputStream(), username, pwd);


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
     * @param out
     * @param
     * @throws IOException
     */
    private static void setBytesToOutputStream(OutputStream out, String userno, String pwd) throws IOException {

        String params = "userno=" + userno + "&pwd=" + pwd;
        out.write(params.getBytes());


    }
}