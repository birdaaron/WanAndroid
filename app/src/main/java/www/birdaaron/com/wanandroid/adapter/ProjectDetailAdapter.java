package www.birdaaron.com.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.ProjectBean;

public class ProjectDetailAdapter extends BaseAdapter
{
    Context mContext;
    private int resourceId;
    private List<ProjectBean> mData;
    public ProjectDetailAdapter(Context mContext,int resourceId,List<ProjectBean> mData)
    {
        super();
        this.resourceId = resourceId;
        this.mData = mData;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return 0;
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
        final ProjectBean project =mData.get(position);
        View view = LayoutInflater.from(mContext).inflate(resourceId,parent,false);
        return null;
    }
}
