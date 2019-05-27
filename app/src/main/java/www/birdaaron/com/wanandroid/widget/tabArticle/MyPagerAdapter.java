package www.birdaaron.com.wanandroid.widget.tabArticle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.io.Serializable;
import java.util.List;

import www.birdaaron.com.wanandroid.bean.TabBean;

public class MyPagerAdapter <T extends TabBean>extends FragmentPagerAdapter
{

    private List<Fragment> mFragement;
    private List<T> mTab;

    public MyPagerAdapter(FragmentManager fm,List<Fragment> mFragement,List<T> mTab)
    {
        super(fm);
        this.mFragement = mFragement;
        this.mTab = mTab;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragement.get(position);
    }

    @Override
    public int getCount() {
        return mFragement.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mTab.get(position).getName();
    }
}
