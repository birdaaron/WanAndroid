package www.birdaaron.com.wanandroid.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.ProjectAdapter;
import www.birdaaron.com.wanandroid.bean.ProjectBean;
import www.birdaaron.com.wanandroid.module.ProjectModule;
import www.birdaaron.com.wanandroid.module.ProjectModuleImpl;
import www.birdaaron.com.wanandroid.util.JsonUtil;

public class KnowledgeListFragment extends Fragment
{
    private int tabId;
    private ListView mListView;
    private final int KNOWLEDGE_DETAIL =0;
    public static KnowledgeListFragment newInstance(int tabId)
    {
        KnowledgeListFragment fragment = new KnowledgeListFragment();
        Bundle args = new Bundle();
        args.putInt("id",tabId);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)

    {
        View rootView = inflater.inflate(R.layout.fragment_project_detail,container,false);
        initView(rootView);
        initData();
        return rootView;
    }
    private void initView(View rootView)
    {
        mListView = rootView.findViewById(R.id.project_lv_detail);
    }
    private void initData()
    {
        this.tabId = getArguments().getInt("id");
        new JsonUtil().getJson("https://www.wanandroid.com/project/list/0/json?cid="+tabId,handler, KNOWLEDGE_DETAIL);
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
                case KNOWLEDGE_DETAIL:
                    List<ProjectBean> projectList = pm.getProjectData(response);
                    ProjectAdapter pda = new ProjectAdapter(getContext(),
                            R.layout.item_project,projectList);
                    mListView.setAdapter(pda);
                    break;
            }
        }
    };
}
