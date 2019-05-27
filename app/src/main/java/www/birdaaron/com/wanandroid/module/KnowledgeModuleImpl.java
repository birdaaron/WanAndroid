package www.birdaaron.com.wanandroid.module;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.bean.KnowledgeBean;
import www.birdaaron.com.wanandroid.bean.KnowledgeChildBean;

public class KnowledgeModuleImpl implements KnowledgeModule
{
    @Override
    public List<KnowledgeBean> getKnowledgeData(String response)
    {
        List<KnowledgeBean> dataList = new ArrayList<>();
        try
        {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0;i<jsonArray.length();i++)
            {
                JSONObject dataObject =jsonArray.getJSONObject(i);
                JSONArray childrenArray = dataObject.getJSONArray("children");
                List<KnowledgeChildBean> childrenList = new ArrayList<>();
                KnowledgeBean ktb = new KnowledgeBean();
                ktb.setName(dataObject.getString("name"));
                for(int j = 0;j<childrenArray.length();j++)
                {
                    JSONObject childrenObject = childrenArray.getJSONObject(j);
                    KnowledgeChildBean kcb = new KnowledgeChildBean();
                    kcb.setName(childrenObject.getString("name"));
                    kcb.setId(childrenObject.getInt("id"));
                    childrenList.add(kcb);
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
