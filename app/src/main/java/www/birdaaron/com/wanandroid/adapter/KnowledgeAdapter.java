package www.birdaaron.com.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.KnowledgeBean;
import www.birdaaron.com.wanandroid.bean.KnowledgeChildBean;
import www.birdaaron.com.wanandroid.view.KnowledgeActivity;

/**
 * 知识体系模块ListView的adapter
 */
public class KnowledgeAdapter extends BaseAdapter
{
    private Context mContext;
    private int resourceId;
    private String knowledgeName;
    private List<KnowledgeChildBean> mData;
    public KnowledgeAdapter(Context mContext, int resourceId, String knowledgeName,
                            List<KnowledgeChildBean> mData)
    {
        super();
        this.resourceId = resourceId;
        this.knowledgeName = knowledgeName;
        this.mData = mData;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final KnowledgeChildBean children = mData.get(position);
        final ArrayList<Integer> tabIdList = new ArrayList<>();
        final ArrayList<String> tabNameList = new ArrayList<>();
        for(KnowledgeChildBean knowledgeChild: mData)
        {
            int id = knowledgeChild.getId();
            String name = knowledgeChild.getName();
            tabIdList.add(id);
            tabNameList.add(name);
        }
        View view = LayoutInflater.from(mContext).inflate(resourceId,parent,false);
        Button child = view.findViewById(R.id.knowledge_btn_child);
        child.setText(children.getName());
        //点击跳转到相应的知识体系文章列表中
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, KnowledgeActivity.class);
                intent.putExtra("currentTab",children.getOrder());
                intent.putExtra("knowledgeName",knowledgeName);
                intent.putIntegerArrayListExtra("tabId",tabIdList);
                intent.putStringArrayListExtra("tabName",tabNameList);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
