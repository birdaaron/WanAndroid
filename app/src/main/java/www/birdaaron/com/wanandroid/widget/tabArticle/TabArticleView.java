package www.birdaaron.com.wanandroid.widget.tabArticle;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.TabBean;

/**
 * 结合TabLayout和ViewPager的自定义控件
 */
public class TabArticleView extends LinearLayout
{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<TabBean> mTabList;
    private List<Fragment> mFragementList;
    public TabArticleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_tab_article, this, true);
        initView();
    }
    private void initView()
    {
        mTabLayout = findViewById(R.id.widget_tl_tab);
        mViewPager = findViewById(R.id.widget_vp_container);
    }
    public void setData(List<TabBean> tabList,List<Fragment> fragmentList,FragmentActivity parent)
    {
        this.mTabList = tabList;
        this.mFragementList = fragmentList;
        MyPagerAdapter mpa = new MyPagerAdapter(parent.getSupportFragmentManager(),mFragementList,mTabList);
        mViewPager.setAdapter(mpa);
        mTabLayout.setupWithViewPager(mViewPager);
    }
    public void setCurrentTab(int tabId)
    {
        mViewPager.setCurrentItem(tabId);
    }
}
