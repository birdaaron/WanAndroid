package www.birdaaron.com.wanandroid.module;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.KnowledgeTypeBean;

public class KnowledgeModuleImpl implements KnowledgeModule
{
    @Override
    public List<KnowledgeTypeBean> getKnowledgeData(String response)
    {
        List<KnowledgeTypeBean> dataList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject dataObject =jsonArray.getJSONObject(i);
                JSONArray childrenArray = dataObject.getJSONArray("children");
                List<String> childrenList = new ArrayList<>();
                KnowledgeTypeBean ktb = new KnowledgeTypeBean();

                ktb.setName(dataObject.getString("name"));
                for(int j = 0;j<childrenArray.length();j++)
                {
                    JSONObject childrenObject = childrenArray.getJSONObject(j);
                    System.out.println("test"+childrenObject.getString("name"));
                    childrenList.add(childrenObject.getString("name"));
                }
                ktb.setChildren(childrenList);
                dataList.add(ktb);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataList;
    }

}
