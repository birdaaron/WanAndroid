package www.birdaaron.com.wanandroid.view;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import www.birdaaron.com.wanandroid.R;

public class MainActivity extends AppCompatActivity
{

    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {//Bundle?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView()
    {
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener);
        //?
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_container,new HomeFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemReselectedListener =
           new BottomNavigationView.OnNavigationItemSelectedListener()
           {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item)
               {
                   Fragment selectedFragment = null;
                   switch (item.getItemId())
                   {
                       case R.id.navigation_home:
                           selectedFragment = new HomeFragment();
                           break;
                       case R.id.navigation_knowledge:
                           selectedFragment = new KnowledgeFragment();
                           break;
                       case R.id.navigation_project:
                           selectedFragment = new ProjectFragment();
                           break;
                   }
                   getSupportFragmentManager().beginTransaction().
                           replace(R.id.main_fl_container,selectedFragment).commit();
                    return true;
               }
           };
}
