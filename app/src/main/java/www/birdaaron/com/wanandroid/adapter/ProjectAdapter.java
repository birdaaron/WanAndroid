package www.birdaaron.com.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.bean.ProjectBean;
import www.birdaaron.com.wanandroid.util.ImageUtil;
import www.birdaaron.com.wanandroid.view.WebActivity;
/**
 * 项目模块文章列表listview的adapter
 */
public class ProjectAdapter extends BaseAdapter
{
    Context mContext;
    private int resourceId;
    private List<ProjectBean> mData;

    public ProjectAdapter(Context mContext, int resourceId, List<ProjectBean> mData)
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
        final ProjectBean project =mData.get(position);

        View view = LayoutInflater.from(mContext).inflate(resourceId,parent,false);
        TextView title = view.findViewById(R.id.project_tv_title);
        TextView time = view.findViewById(R.id.project_tv_time);
        TextView author = view.findViewById(R.id.project_tv_author);
        TextView desc = view.findViewById(R.id.project_tv_desc);
        title.setText(project.getTitle());
        time.setText(project.getNiceDate());
        author.setText(project.getAuthor());
        desc.setText(project.getDesc());
        //点击文章打开相应网站
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("webURL",project.getLink());
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
