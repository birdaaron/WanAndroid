package www.birdaaron.com.wanandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;
import www.birdaaron.com.wanandroid.bean.TabBean;
import www.birdaaron.com.wanandroid.widget.tabArticle.TabArticleView;

public class KnowledgeActivity extends AppCompatActivity
{
    private Toolbar mToolBar;
    private TabArticleView mTabArticleView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        initView();
        initData();
    }
    private void initView()
    {
        mTabArticleView = findViewById(R.id.knowledge_tav_container);
        mToolBar = findViewById(R.id.knowledge_tb_toolbar);
        setSupportActionBar(mToolBar);
    }
    private void initData()
    {
        Intent intent = getIntent();
        initTab(intent);
        initToolBar(intent);
    }
    private void initToolBar(Intent intent)
    {
        String knowledgeName = intent.getStringExtra("knowledgeName");
        getSupportActionBar().setTitle(knowledgeName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
    private void initTab(Intent intent)
    {
        List<Integer> tabIdList = intent.getIntegerArrayListExtra("tabId");
        List<String> tabNameList = intent.getStringArrayListExtra("tabName");
        int currentTab = intent.getIntExtra("currentTab",tabIdList.get(0));

        List<TabBean> tabBeanList = new ArrayList<>();
        for(int i=0;i<tabIdList.size();i++)
        {
            TabBean tabBean = new TabBean();
            tabBean.setName(tabNameList.get(i));
            tabBean.setId(tabIdList.get(i));
            tabBeanList.add(tabBean);
        }

        List<Fragment> fragmentList = new ArrayList<>();
        for(TabBean tabBean : tabBeanList)
            fragmentList.add(KnowledgeListFragment.newInstance(tabBean.getId()));

        mTabArticleView.setData(tabBeanList,fragmentList,this);
        mTabArticleView.setCurrentTab(currentTab);
    }

}
