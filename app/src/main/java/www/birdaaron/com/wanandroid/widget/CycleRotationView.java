package www.birdaaron.com.wanandroid.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.CycleAdapter;
import www.birdaaron.com.wanandroid.bean.BannerBean;
import www.birdaaron.com.wanandroid.util.Http.ImageUtil;


public class CycleRotationView extends LinearLayout
{
    private Context mContext;
    private ViewPager mViewPager;
    private LinearLayout mPointGroup;
    private TextView mTitle;
    private Handler mHandler;
    private List<ImageView> mImageList = new ArrayList<>();
    private List<String> mURLList = new ArrayList<>();
    private List<BannerBean> mData;

    public CycleRotationView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.widget_cycle_rotation, this, true);
        mViewPager = findViewById(R.id.widget_vp_ImageContainer);
        mPointGroup = findViewById(R.id.widget_ll_pointGroup);
        mTitle = findViewById(R.id.widget_tv_title);
    }
    public void initData(List<BannerBean> data)
    {
        mData = data;
        setImage();
        setURL();
        initView();
    }
    private void setURL()
    {
        for(BannerBean data : mData)
            mURLList.add(data.getUrl());
    }
    private void setImage()
    {
        for(BannerBean data : mData)
        {
            try
            {
                ImageView iv = new ImageView(mContext);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                new ImageUtil(iv).execute(data.getImagePath());
                mImageList.add(iv);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void initView()
    {
        CycleAdapter adapter = new CycleAdapter(mContext, mImageList,mURLList);
        mViewPager.setAdapter(adapter);
        addPoints();
        changePoints();
        startRotate();
    }

    private void addPoints()
    {
        for(int i = 0 ;i<mData.size();i++)
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
                position = position % mImageList.size();
                mPointGroup.getChildAt(position).setSelected(true);
                mPointGroup.getChildAt(lastPostion).setSelected(false);
                lastPostion = position;
                mTitle.setText(mData.get(position).getTitle());
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
