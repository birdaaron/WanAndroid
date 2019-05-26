package www.birdaaron.com.wanandroid.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.ProjectAdapter;
import www.birdaaron.com.wanandroid.bean.KnowledgeBean;
import www.birdaaron.com.wanandroid.bean.ProjectTypeBean;
import www.birdaaron.com.wanandroid.module.KnowledgeModule;
import www.birdaaron.com.wanandroid.module.KnowledgeModuleImpl;
import www.birdaaron.com.wanandroid.module.ProjectModule;
import www.birdaaron.com.wanandroid.module.ProjectModuleImpl;
import www.birdaaron.com.wanandroid.util.JsonUtil;

public class KnowledgeFragment extends Fragment
{
    private final int KNOWLEDGE_TYPE =0;
    private LinearLayout mTypeNavigation;
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
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("知识体系");
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

                    for(KnowledgeBean type : knowledgeList)
                    {
                        Button button = new Button(getContext());
                        button.setText(type.getName());
                        mTypeNavigation.addView(button);
                    }
                    break;
            }
        }
    };
}
