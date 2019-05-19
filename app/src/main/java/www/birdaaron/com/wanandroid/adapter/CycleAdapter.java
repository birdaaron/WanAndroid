package www.birdaaron.com.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class CycleAdapter extends PagerAdapter
{
    private List<ImageView> mList;
    private Context mContext;
    public CycleAdapter(Context context,List<ImageView> mList)
    {
        this.mContext = context;
        this.mList = mList;
    }

    @Override
    public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int positon)
    {
        positon = positon%mList.size();
        final View child = mList.get(positon);
        if(child.getParent()!=null)
            container.removeView(child);
        container.addView(mList.get(positon));
        return mList.get(positon);
    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object){}
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }
}
