package com.tangjiu.supermarket.net;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

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

    public void login(String soap, String pwd, String username, SoapCallback<String> soapCallback) {
        HttpURLConnection connection = null;
        String params = "userno=" + username + "&pwd=" + pwd;
        try {
            URL url = new URL("http://218.26.102.234:48612/WebService1.asmx/" + soap);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("SOAPAction", "http://tempuri.org/" + soap);
            connection.setRequestProperty("Content-Length", Integer.toString(params.getBytes(Charset.forName("UTF-8")).length));
            connection.connect();
            // 设置请求的参数
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.close();
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

    public void GetStoreOrders(String soap, String storeno, SoapCallback<String> soapCallback) {
        HttpURLConnection connection = null;
        String params = "storeno=" + storeno;
        try {
            URL url = new URL("http://218.26.102.234:48612/WebService1.asmx/" + soap);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("SOAPAction", "http://tempuri.org/" + soap);
            connection.setRequestProperty("Content-Length", Integer.toString(params.getBytes(Charset.forName("UTF-8")).length));
            connection.connect();
            // 设置请求的参数
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.close();
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


    //7、	订货到店
    public void OrderConfirm(String soap, int id, SoapCallback<String> soapCallback) {
        HttpURLConnection connection = null;
        String params = "DeliveryOrderID=" + id;
        try {
            URL url = new URL("http://218.26.102.234:48612/WebService1.asmx/" + soap);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("SOAPAction", "http://tempuri.org/" + soap);
            connection.setRequestProperty("Content-Length", Integer.toString(params.getBytes(Charset.forName("UTF-8")).length));
            connection.connect();
            // 设置请求的参数
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.close();
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


}