package www.birdaaron.com.wanandroid.module;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.BannerBean;

/**
 * 主页的Json处理
 */
public class HomeModuleImpl implements HomeModule
{
    //返回轮播图数据
    @Override
    public List<BannerBean> getBannerData(String response)
    {
        List<BannerBean> dataList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jo =jsonArray.getJSONObject(i);
                BannerBean bd = new BannerBean();
                bd.setType(jo.getInt("type"));
                bd.setOrder(jo.getInt("order"));
                bd.setId(jo.getInt("id"));
                bd.setIsVisible(jo.getInt("isVisible"));
                bd.setUrl(jo.getString("url"));
                bd.setImagePath(jo.getString("imagePath"));
                bd.setTitle(jo.getString("title"));
                bd.setDesc(jo.getString("desc"));
                dataList.add(bd);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataList;
    }
    //返回文章数据
    @Override
    public List<ArticleBean> getArticleData(String response)
    {
        List<ArticleBean> dataList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("datas");
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jo =jsonArray.getJSONObject(i);
                ArticleBean ab = new ArticleBean();
                ab.setAuthor(jo.getString("author"));
                ab.setChapterName(jo.getString("chapterName"));
                ab.setLink(jo.getString("link"));
                ab.setNiceDate(jo.getString("niceDate"));
                ab.setTitle(jo.getString("title"));
                ab.setSuperChapterName(jo.getString("superChapterName"));
                dataList.add(ab);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataList;
    }
}
