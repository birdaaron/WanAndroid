package www.birdaaron.com.wanandroid.util.Http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonUtil
{

    public final static int JSON_GET = 1;
    public void getJson(final String urlStr, final Handler handler)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil hu = new HttpUtil();
                HttpURLConnection connection = null;
                BufferedReader bufferedReader = null;
                try
                {
                    connection = hu.getConnection(urlStr,"GET");
                    bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line ;
                    while((line = bufferedReader.readLine())!=null)
                        response.append(line);
                    Bundle bundle = new Bundle();
                    bundle.putString("jsonData",response.toString());
                    Message message = new Message();
                    message.what = JSON_GET;
                    message.setData(bundle);
                    message.obj = response.toString();
                    handler.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(bufferedReader!=null)
                        try
                        {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    if(connection != null)
                        connection.disconnect();
                }
            }
        }).start();


    }

}
