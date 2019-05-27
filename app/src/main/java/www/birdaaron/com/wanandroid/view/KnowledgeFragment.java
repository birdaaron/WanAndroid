package www.birdaaron.com.wanandroid.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.KnowledgeAdapter;
import www.birdaaron.com.wanandroid.bean.KnowledgeBean;
import www.birdaaron.com.wanandroid.bean.KnowledgeChildBean;
import www.birdaaron.com.wanandroid.module.KnowledgeModule;
import www.birdaaron.com.wanandroid.module.KnowledgeModuleImpl;
import www.birdaaron.com.wanandroid.util.JsonUtil;

public class KnowledgeFragment extends Fragment implements Serializable
{
    private final int KNOWLEDGE_TYPE =0;
    private LinearLayout mTypeNavigation;
    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_knowledge,container,false);
        initData();
        initView(rootView);
        return rootView;
    }
    private void initData()
    {
        new JsonUtil().getJson("https://www.wanandroid.com/tree/json",handler,KNOWLEDGE_TYPE);
    }
    private void initView(View rootView)
    {
        mTypeNavigation = rootView.findViewById(R.id.knowledge_tl_type);
        mListView = rootView.findViewById(R.id.knowledge_lv_container);
    }
    private void initNavigation(final List<KnowledgeBean> knowledgeList)
    {
        List<Fragment> fragmentList = new ArrayList<>();

        for(KnowledgeBean type : knowledgeList)
            fragmentList.add(KnowledgeListFragment.newInstance(type.getId()));

        //构建导航栏
        for(final KnowledgeBean type : knowledgeList)
        {
            final List<KnowledgeChildBean> knowledgeChildList = type.getChildren();
            Button button = new Button(getContext());
            button.setText(type.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    KnowledgeAdapter ka = new KnowledgeAdapter(getContext(),
                            R.layout.item_knowledge_children,knowledgeChildList);
                    mListView.setAdapter(ka);
                }
            });
            mTypeNavigation.addView(button);
        }
        //默认打开第一个知识体系
        mListView.setAdapter(new KnowledgeAdapter(getContext(),
                R.layout.item_knowledge_children,knowledgeList.get(0).getChildren()));
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String response = msg.getData().getString("jsonData");
            KnowledgeModule km = new KnowledgeModuleImpl();
            switch (msg.what)
            {
                case KNOWLEDGE_TYPE:
                    List<KnowledgeBean> knowledgeList = km.getKnowledgeData(response);
                    initNavigation(knowledgeList);
                    break;
            }
        }
    };
}
