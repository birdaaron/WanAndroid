package www.birdaaron.com.wanandroid.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil
{
    public HttpURLConnection getConnection(String urlStr, String requestMethod) //final
    {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  connection;
    }

}
