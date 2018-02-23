package com.mtianyan.mtianyan001;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mtianyan.mtianyan001.fragment.Fragment1;
import com.mtianyan.mtianyan001.fragment.Fragment2;
import com.mtianyan.mtianyan001.fragment.Fragment3;
import com.mtianyan.mtianyan001.fragment.Fragment4;
import com.mtianyan.mtianyan001.fragment.Fragment5;
import com.mtianyan.mtianyan001.fragment.Fragment6;
import com.mtianyan.mtianyan001.ui.MtianyanInfoActivity;
import com.mtianyan.mtianyan001.ui.VipActivity;
import com.mtianyan.mtianyan001.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.ui
 * 文件名：MainActivity
 * 作者：mtianyan
 * 创建时间：2017/5/31 22:26
 * 描述：设置
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private CircleImageView circleImageView;
    //TabLayout
    private SlidingTabLayout mTablayout;
    //ViewPager
    private ViewPager mViewPager;
    private DrawerLayout mDrawerLayout;
    private long exitTime;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    private NavigationView mNavigationView;
    private MaterialSearchView mSearchView;
    private
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
            } else if(y2 - y1 > 50) {
                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
            } else if(x2 - x1 > 50) {
                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolbar();
        initData();
        initView();
        initHomePage();

        CircleImageView mCircleImageView;
        mCircleImageView = (CircleImageView) findViewById(R.id.toolbar_user_avatar);
        mCircleImageView.setImageResource(R.drawable.icon_mtianyan);

    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        this.setSupportActionBar(mToolbar);
    }

    private void initHomePage() {
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mTablayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
//        MaterialSearchView mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());


        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的fragment
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);

            }

            //返回item的个数
            @Override
            public int getCount() {

                return mFragment.size();
            }

            //设置标题

            @Override
            public CharSequence getPageTitle(int position) {

                return mTitle.get(position);
            }
        });
        //viewpager设置滑动监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //绑定
        mTablayout.setViewPager(mViewPager);
    }
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("视频");
        mTitle.add("分区");
        mTitle.add("管家");
        mTitle.add("微信");
        mTitle.add("美女");
        mTitle.add("个人");

        mFragment = new ArrayList<>();
        mFragment.add(new Fragment1());
        mFragment.add(new Fragment2());
        mFragment.add(new Fragment3());
        mFragment.add(new Fragment4());
        mFragment.add(new Fragment5());
        mFragment.add(new Fragment6());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }



    private void initView() {
        circleImageView = (CircleImageView) findViewById(R.id.toolbar_user_avatar);
        circleImageView.setOnClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                // 主页
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;


            case R.id.item_vip:
                //大会员
                startActivity(new Intent(MainActivity.this, VipActivity.class));
                return true;

            case R.id.item_favourite:
                // 我的收藏
                Toast.makeText(this,"我的收藏",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_history:
                Toast.makeText(this,"历史记录",Toast.LENGTH_SHORT).show();
                // 历史记录
                return true;

            case R.id.item_group:
                // 关注的人
                Toast.makeText(this,"关注的人",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_tracker:
                // 我的钱包
                Toast.makeText(this,"我的钱包",Toast.LENGTH_SHORT).show();
                return true;


            case R.id.item_settings:
                // 设置中心
                startActivity(new Intent(this,MtianyanInfoActivity.class));
                return true;
        }

        return false;
    }
    /**
     * 初始化Fragments
     */



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_user_avatar:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }
}



