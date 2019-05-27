package www.birdaaron.com.wanandroid.module;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.bean.ProjectBean;
import www.birdaaron.com.wanandroid.bean.TabBean;
/**
 * 项目模块的Json处理
 */
public class ProjectModuleImpl implements ProjectModule
{
    //获得项目文章类型
    @Override
    public List<TabBean> getProjectType(String response) {
        List<TabBean> dataList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject jo =jsonArray.getJSONObject(i);
                TabBean tb = new TabBean();
                tb.setId(jo.getInt("id"));
                tb.setName(jo.getString("name"));
                dataList.add(tb);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataList;
    }
    //获得项目数据
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

