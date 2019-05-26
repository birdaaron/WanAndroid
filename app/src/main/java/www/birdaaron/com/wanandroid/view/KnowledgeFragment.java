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

import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.adapter.KnowledgeAdapter;
import www.birdaaron.com.wanandroid.bean.KnowledgeTypeBean;
import www.birdaaron.com.wanandroid.module.KnowledgeModule;
import www.birdaaron.com.wanandroid.module.KnowledgeModuleImpl;
import www.birdaaron.com.wanandroid.util.JsonUtil;

public class KnowledgeFragment extends Fragment
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
                    List<KnowledgeTypeBean> knowledgeList = km.getKnowledgeData(response);
                    //默认打开第一个知识体系
                    mListView.setAdapter(new KnowledgeAdapter(getContext(),R.layout.item_knowledge_children,
                            knowledgeList.get(0).getChildren()));
                    //构建导航栏
                    for(final KnowledgeTypeBean type : knowledgeList)
                    {
                        Button button = new Button(getContext());
                        button.setText(type.getName());
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                KnowledgeAdapter ka = new KnowledgeAdapter(getContext(),R.layout.
                                        item_knowledge_children,type.getChildren());
                                mListView.setAdapter(ka);
                            }
                        });
                        mTypeNavigation.addView(button);
                    }
                    break;
            }
        }
    };
}
