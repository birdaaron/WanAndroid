package www.birdaaron.com.wanandroid.view;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.BannerBean;
import www.birdaaron.com.wanandroid.module.HomeModule;
import www.birdaaron.com.wanandroid.module.HomeModuleImpl;
import www.birdaaron.com.wanandroid.util.Http.HttpUtil;
import www.birdaaron.com.wanandroid.util.Http.ImageUtil;
import www.birdaaron.com.wanandroid.util.Http.JsonUtil;
import www.birdaaron.com.wanandroid.widget.CycleRotationView;

public class HomeFragment extends Fragment {

    private CycleRotationView banner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        initView(rootView);
        initData();
        return rootView;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case JsonUtil.JSON_GET:
                    String response = msg.getData().getString("jsonData");
                    HomeModule hm = new HomeModuleImpl();
                    List<BannerBean> list = hm.getBannerData(response);

                    banner.initData(list);
                    break;

            }
        }
    };
   private void initView(View rootView)
    {
       banner = rootView.findViewById(R.id.home_crv_banner);
    }
    private void initData()
    {
        new JsonUtil().getJson("https://www.wanandroid.com/banner/json",handler);
    }

}
