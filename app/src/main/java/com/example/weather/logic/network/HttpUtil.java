package com.example.weather.logic.network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.CharBuffer;

public class HttpUtil {
    private static final int CONNECTION_TIMEOUT = 8000;
    private static final int READ_TIMEOUT = 10000;

    public static String HttpURLConnection(String urlString, String requestMethod) {
        HttpURLConnection httpURLConnection = null;
        String response = null;
        try{
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            InputStream in = httpURLConnection.getInputStream();
            response = readStream(new BufferedInputStream(in));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return response;
    }

    public static String readStream(BufferedInputStream in) {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int len = 0;
        try {
            while ((len = in.read(buf)) != -1) {
                sb.append(new String(buf,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static String readStream(BufferedReader in) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            CharBuffer cb = CharBuffer.allocate(1024);
            while (in.read(cb) != -1) {
                cb.flip();
                sb.append(cb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
