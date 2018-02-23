package com.mtianyan.mtianyan001.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.mtianyan.mtianyan001.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.ui
 * 文件名：MainActivity
 * 作者：mtianyan
 * 创建时间：2017/5/31 22:26
 * 描述：设置
 */
public class SettingActivity extends AppCompatActivity {
//    @BindView(R.id.sw_speak)
//    Switch swSpeak;
//    @BindView(R.id.textView2)
//    TextView textView2;
//    @BindView(R.id.sw_sms)
//    Switch swSms;
//    @BindView(R.id.tv_version)
//    TextView tvVersion;
//    @BindView(R.id.ll_update)
//    LinearLayout llUpdate;
//    @BindView(R.id.tv_scan_result)
//    TextView tvScanResult;
//    @BindView(R.id.ll_scan)
//    LinearLayout llScan;
//    @BindView(R.id.ll_qr_code)
//    LinearLayout llQrCode;
//    @BindView(R.id.textView)
//    TextView textView;
//    @BindView(R.id.ll_my_location)
//    LinearLayout llMyLocation;
    @BindView(R.id.ll_about)
    LinearLayout llAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

    }

    }

//    //TabLayout
//    private TabLayout mTablayout;
//    //ViewPager
//    private ViewPager mViewPager;
//
//    //Title
//    private List<String> mTitle;
//    //Fragment
//    private List<Fragment> mFragment;
//    //fab_setting
//    private FloatingActionButton mfab_seeting;
//    private DrawerLayout mDrawLayout;
//    private NavigationView mNavigationView;
//    private Fragment[] fragments;
//
//    private int currentTabIndex;
//
//    private int index;
//
//    private long exitTime;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        //去除阴影
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("");
//        this.setSupportActionBar(mToolbar);
//        initView();
//        initData();
//        initHomePage();
//
//        CircleImageView mCircleImageView;
//        mCircleImageView = (CircleImageView) findViewById(R.id.toolbar_user_avatar);
//        mCircleImageView.setImageResource(R.drawable.icon_mtianyan);
//
//    }
//
//    private void initHomePage() {
//        mTablayout = (TabLayout) findViewById(R.id.mTabLayout);
//        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
//        mfab_seeting = (FloatingActionButton) findViewById(R.id.fab_setting);
//        //默认隐藏
//        mfab_seeting.setVisibility(View.GONE);
//        //设置点击监听器
////        mfab_seeting.setOnClickListener(this);
//        //预加载
//        mViewPager.setOffscreenPageLimit(mFragment.size());
//        //设置适配器
//        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            //选中的fragment
//            @Override
//            public Fragment getItem(int position) {
//                return mFragment.get(position);
//            }
//
//            //返回item的个数
//            @Override
//            public int getCount() {
//                return mFragment.size();
//            }
//
//            //设置标题
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return mTitle.get(position);
//            }
//        });
//        //viewpager设置滑动监听器
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if(position ==0){
//                    mfab_seeting.setVisibility(View.GONE);
//                }else{
//                    mfab_seeting.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        //绑定
//        mTablayout.setupWithViewPager(mViewPager);
//    }
//    private void initData() {
//        mTitle = new ArrayList<>();
//        mTitle.add("直播");
//        mTitle.add("推荐");
//        mTitle.add("追番");
//        mTitle.add("分区");
//        mTitle.add("动态");
//        mTitle.add("发现");
//
//        mFragment = new ArrayList<>();
//        mFragment.add(new Fragment1());
//        mFragment.add(new Fragment2());
//        mFragment.add(new Fragment3());
//        mFragment.add(new Fragment4());
//        mFragment.add(new Fragment5());
//        mFragment.add(new Fragment6());
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//
//    private void initView() {
//       mDrawLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
//        mNavigationView.setNavigationItemSelectedListener(this);
//
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }
//}
