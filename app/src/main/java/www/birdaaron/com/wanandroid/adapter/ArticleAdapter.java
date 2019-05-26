package www.birdaaron.com.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.ArticleBean;
import www.birdaaron.com.wanandroid.view.WebActivity;

/**
 * 首页文章列表的adpter
 */
public class ArticleAdapter extends BaseAdapter
{
    Context mContext;
    private int resourceId;
    private List<ArticleBean> mData;
    public ArticleAdapter(Context mContext,int resourceId,List<ArticleBean> mData)
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
        final ArticleBean article =mData.get(position);
        View view = LayoutInflater.from(mContext).inflate(resourceId,parent,false);
        TextView author = view.findViewById(R.id.article_tv_author);
        TextView time = view.findViewById(R.id.article_tv_time);
        TextView chapter = view.findViewById(R.id.article_tv_chapter);
        TextView title = view.findViewById(R.id.article_tv_title);
        author.setText(article.getAuthor());
        time.setText(article.getNiceDate());
        chapter.setText(article.getSuperChapterName()+"/"+article.getChapterName());
        title.setText(article.getTitle());
        //点击文章时打开文章网站
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("webURL",article.getLink());
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
