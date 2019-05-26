package www.birdaaron.com.wanandroid.module;

import java.util.List;

import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.BannerBean;

public interface HomeModule
{
    List<BannerBean> getBannerData(String response);
    List<ArticleBean> getArticleData(String response);
}
