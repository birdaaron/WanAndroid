package www.birdaaron.com.wanandroid.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.TabBean;
import www.birdaaron.com.wanandroid.module.ProjectModule;
import www.birdaaron.com.wanandroid.module.ProjectModuleImpl;
import www.birdaaron.com.wanandroid.util.JsonUtil;
import www.birdaaron.com.wanandroid.widget.tabArticle.TabArticleView;

public class ProjectFragment extends Fragment {

    private TabArticleView mTabArticleView;
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
       mTabArticleView = rootView.findViewById(R.id.project_tav_tabArticle);
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
                    List<TabBean> tabList = pm.getProjectType(response);
                    mProject = new ArrayList<>();
                    for(TabBean tab : tabList)
                        mProject.add(ProjectListFragment.newInstance(tab.getId()));
                    mTabArticleView.setData(tabList,mProject,getActivity());
                    break;
            }
        }
    };

}
