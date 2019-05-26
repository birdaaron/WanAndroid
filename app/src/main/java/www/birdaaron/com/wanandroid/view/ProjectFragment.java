package www.birdaaron.com.wanandroid.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.ProjectAdapter;
import www.birdaaron.com.wanandroid.bean.ProjectTypeBean;
import www.birdaaron.com.wanandroid.module.ProjectModule;
import www.birdaaron.com.wanandroid.module.ProjectModuleImpl;
import www.birdaaron.com.wanandroid.util.JsonUtil;

public class ProjectFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mProject;
    private final int PROJECT_TYPE=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)

    {
        View rootView = inflater.inflate(R.layout.fragment_project,container,false);
        initView(rootView);
        initData();
       return rootView;
    }
    private void initView(View rootView)
    {
        mTabLayout = rootView.findViewById(R.id.project_tl_tab);
        mViewPager = rootView.findViewById(R.id.project_vp_container);
    }
    private void initData()
    {
        new JsonUtil().getJson("https://www.wanandroid.com/project/tree/json",handler,PROJECT_TYPE);
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String response = msg.getData().getString("jsonData");
            ProjectModule pm = new ProjectModuleImpl();
            switch (msg.what)
            {
                case PROJECT_TYPE:
                    List<ProjectTypeBean> typeList = pm.getProjectType(response);
                    mProject = new ArrayList<>();
                    for(ProjectTypeBean typeBean : typeList)
                        mProject.add(ProjectDetailFragment.newInstance(typeBean.getId()));
                    ProjectAdapter pa = new ProjectAdapter(getActivity().getSupportFragmentManager(),mProject,typeList);
                    mViewPager.setAdapter(pa);
                    mTabLayout.setupWithViewPager(mViewPager);
                    break;
            }
        }
    };
}
