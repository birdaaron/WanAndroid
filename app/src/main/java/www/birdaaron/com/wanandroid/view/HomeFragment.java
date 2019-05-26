package www.birdaaron.com.wanandroid.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.ArticleAdapter;
import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.BannerBean;
import www.birdaaron.com.wanandroid.module.HomeModule;
import www.birdaaron.com.wanandroid.module.HomeModuleImpl;
import www.birdaaron.com.wanandroid.util.JsonUtil;
import www.birdaaron.com.wanandroid.widget.CycleRotationView;

public class HomeFragment extends Fragment {

    private CycleRotationView mBanner;
    private ListView mListView;
    private final int BANNER_DATA=0,ARTICLE_DATA=1;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        initData();
        initView(rootView);
        return rootView;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String response = msg.getData().getString("jsonData");
            HomeModule hm = new HomeModuleImpl();
            switch (msg.what)
            {
                case BANNER_DATA:
                    List<BannerBean> bannerData = hm.getBannerData(response);
                    mBanner.initData(bannerData);
                    break;
                case ARTICLE_DATA:
                    List<ArticleBean> articleData = hm.getArticleData(response);
                    ArticleAdapter aa = new ArticleAdapter(getContext(),R.layout.item_article,articleData);
                    mListView.setAdapter(aa);
                    break;
            }
        }
    };
   private void initView(View rootView)
    {
       mBanner = rootView.findViewById(R.id.home_crv_banner);
       mListView = rootView.findViewById(R.id.home_lv_article);
    }
    private void initData()
    {
        new JsonUtil().getJson("https://www.wanandroid.com/banner/json",handler,BANNER_DATA);
        new JsonUtil().getJson("https://www.wanandroid.com/article/list/0/json",handler,ARTICLE_DATA);
    }

}
