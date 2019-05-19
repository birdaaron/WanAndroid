package www.birdaaron.com.wanandroid.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.CycleAdapter;

public class CycleRotationView extends LinearLayout
{
    private ViewPager mViewPager;
    private LinearLayout mPointGroup;
    private Handler mHandler;
    private List<ImageView> mList = new ArrayList<>();
    private int image[] = {
            R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4
    };

    public CycleRotationView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initData(context);
        initView(context);
    }
    private void initData(Context mContext)
    {
        for(int i = 0;i<image.length;i++)
        {
            ImageView iv = new ImageView(mContext);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(image[i]);
            mList.add(iv);
        }
    }
    private void initView(Context mContext)
    {
        CycleAdapter adapter = new CycleAdapter(mContext,mList);
        LayoutInflater.from(mContext).inflate(R.layout.widget_cycle_rotation, this, true);
        mViewPager = findViewById(R.id.widget_vp_ImageContainer);
        mPointGroup = findViewById(R.id.widget_ll_pointGroup);
        mViewPager.setAdapter(adapter);
        addPoints(mContext);
        changePoints();
        startRotate();
    }

    private void addPoints(Context mContext)
    {
        for(int i = 0 ;i<image.length;i++)
        {
            ImageView point = new ImageView(mContext);
            LayoutParams params = new LayoutParams(20,20);
            point.setImageResource(R.drawable.shape_point_selector);
            if(i>0)
                point.setSelected(false);
            else
                point.setSelected(true);
            point.setLayoutParams(params);
            mPointGroup.addView(point);
        }
    }
    private void changePoints()
    {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            int lastPostion;
            @Override
            public void onPageSelected(int position)
            {
                position = position % mList.size();
                mPointGroup.getChildAt(position).setSelected(true);
                mPointGroup.getChildAt(lastPostion).setSelected(false);
                lastPostion = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });
    }
    private void startRotate()
    {
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                int currentItem = mViewPager.getCurrentItem();
                if(currentItem == mViewPager.getAdapter().getCount()-1)
                    mViewPager.setCurrentItem(1);
                else
                    mViewPager.setCurrentItem(currentItem+1);
                mHandler.postDelayed(this,3000);
            }
        },3000);
    }

}
