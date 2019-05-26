package www.birdaaron.com.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import www.birdaaron.com.wanandroid.view.WebActivity;

/**
 * 轮播图adpter
 */
public class CycleAdapter extends PagerAdapter
{
    private List<ImageView> mImageList;
    private List<String> mURLList;
    private Context mContext;
    public CycleAdapter(Context context,List<ImageView> mImageList,List<String> mURLList)
    {
        this.mContext = context;
        this.mImageList = mImageList;
        this.mURLList = mURLList;
    }

    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int positon)
    {
        positon = positon% mImageList.size();
        final View child = mImageList.get(positon);
        if(child.getParent()!=null)
            container.removeView(child);
        container.addView(mImageList.get(positon));

        final int finalPositon = positon;
        mImageList.get(positon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("webURL",mURLList.get(finalPositon));
                mContext.startActivity(intent);
            }
        });
        return mImageList.get(positon);
    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object){}
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }
}
