package www.birdaaron.com.wanandroid.module;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.bean.KnowledgeBean;

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
                JSONObject jo =jsonArray.getJSONObject(i);
                JSONArray ja = jo.getJSONArray("children");
                List<String> list = new ArrayList<>();
                for(int j = 0;j<ja.length();j++)
                    list.add(ja.getJSONObject(j).getString("name"));
                KnowledgeBean kb = new KnowledgeBean();
                kb.setName(jo.getString("name"));
                kb.setChildren(list);
                dataList.add(kb);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataList;
    }

}
