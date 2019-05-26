package www.birdaaron.com.wanandroid.module;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.ProjectBean;
import www.birdaaron.com.wanandroid.bean.ProjectTypeBean;

public class ProjectModuleImpl implements ProjectModule {


    @Override
    public List<ProjectTypeBean> getProjectType(String response) {
        List<ProjectTypeBean> dataList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jo =jsonArray.getJSONObject(i);
                ProjectTypeBean ptb = new ProjectTypeBean();
                ptb.setId(jo.getInt("id"));
                ptb.setName(jo.getString("name"));
                ptb.setOrder(jo.getInt("order"));
                dataList.add(ptb);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataList;
    }

    @Override
    public List<ProjectBean> getProjectData(String response) {
        List<ProjectBean> dataList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("datas");
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jo =jsonArray.getJSONObject(i);
                ProjectBean pb = new ProjectBean();
                pb.setAuthor(jo.getString("author"));
                pb.setChapterId(jo.getInt("chapterId"));
                pb.setEnvelopePic(jo.getString("envelopePic"));
                pb.setDesc(jo.getString("desc"));
                pb.setLink(jo.getString("link"));
                pb.setNiceDate(jo.getString("niceDate"));
                pb.setTitle(jo.getString("title"));
                dataList.add(pb);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataList;
    }
}

