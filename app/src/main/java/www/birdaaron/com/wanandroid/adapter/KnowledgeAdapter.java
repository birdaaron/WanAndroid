package www.birdaaron.com.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.KnowledgeTypeBean;

/**
 * 知识体系listview的adapter
 */
public class KnowledgeAdapter extends BaseAdapter
{
    private Context mContext;
    private int resourceId;
    private List<String> mData;
    public KnowledgeAdapter(Context mContext, int resourceId, List<String> mData)
    {
        super();
        this.resourceId = resourceId;
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
        final String children = mData.get(position);
        View view = LayoutInflater.from(mContext).inflate(resourceId,parent,false);
        Button child = view.findViewById(R.id.knowledge_btn_child);
        child.setText(children);
        return view;
    }
}
