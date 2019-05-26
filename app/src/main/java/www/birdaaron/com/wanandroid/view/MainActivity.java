package www.birdaaron.com.wanandroid.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import www.birdaaron.com.wanandroid.R;

public class MainActivity extends AppCompatActivity
{

    private BottomNavigationView mBottomNavigationView;
    private List<Fragment> mFragment;
    private Fragment oldFragment;
   // private DrawerLayout mDrawerLayout;
    //private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView()
    {
        mBottomNavigationView = findViewById(R.id.main_bn_navigation);
        mToolbar = findViewById(R.id.widget_tb_toolbar);
        //mDrawerLayout = findViewById(R.id.widget_dl_user);
        setSupportActionBar(mToolbar);

       initNavigation();

    }

    private void initNavigation()
    {
        mFragment = new ArrayList<>();
        mFragment.add(new HomeFragment());
        mFragment.add(new KnowledgeFragment());
        mFragment.add(new ProjectFragment());

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        oldFragment = mFragment.get(0);
        ft.add(R.id.main_fl_container,oldFragment).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemReselectedListener =
           new BottomNavigationView.OnNavigationItemSelectedListener()
           {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item)
               {
                   Fragment selectedFragment = null;
                   String toolBarTitle=null;
                   switch (item.getItemId())
                   {
                       case R.id.navigation_home:
                           selectedFragment = mFragment.get(0);
                           toolBarTitle = "主页";
                           break;
                       case R.id.navigation_knowledge:
                           selectedFragment = mFragment.get(1);
                           toolBarTitle = "知识体系";
                           break;
                       case R.id.navigation_project:
                           selectedFragment = mFragment.get(2);
                           toolBarTitle = "项目";
                           break;
                   }
                   FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                   if(selectedFragment.isAdded())
                   {
                       ft.hide(oldFragment).show(selectedFragment).commit();
                       getSupportActionBar().setTitle(toolBarTitle);
                   }
                   else
                       ft.hide(oldFragment).add(R.id.main_fl_container,selectedFragment).commit();
                   oldFragment = selectedFragment;
                   return true;
               }
           };

}
