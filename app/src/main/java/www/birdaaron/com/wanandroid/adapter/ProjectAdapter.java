package www.birdaaron.com.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import www.birdaaron.com.wanandroid.bean.ProjectTypeBean;

/**
 * 项目模块viewPager的Adapter
 */
public class ProjectAdapter extends FragmentPagerAdapter
{
    private List<Fragment> mFragement;
    private List<ProjectTypeBean> mProjectType;
    public ProjectAdapter(FragmentManager fm,List<Fragment> mFragement, List<ProjectTypeBean> mProjectType)
    {
        super(fm);
        this.mFragement = mFragement;
        this.mProjectType = mProjectType;
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragement.get(position);
    }

    @Override
    public int getCount()
    {
        return mProjectType.size();
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        return mProjectType.get(position).getName();
    }
}
