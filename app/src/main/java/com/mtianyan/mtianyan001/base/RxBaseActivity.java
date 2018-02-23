package com.mtianyan.mtianyan001.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mtianyan.mtianyan001.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hcc on 16/8/7 21:18
 * 100332338@qq.com
 * <p/>
 * Activity基类
 */
public abstract class RxBaseActivity extends AppCompatActivity
   {

  private Unbinder bind;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    //设置布局内容
    setContentView(getLayoutId());
    //初始化黄油刀控件绑定框架
    bind = ButterKnife.bind(this);
    //初始化控件
    initViews(savedInstanceState);
    //初始化ToolBar
    initToolBar();
  }


  public abstract int getLayoutId();

  public abstract void initViews(Bundle savedInstanceState);

  public abstract void initToolBar();


  public void loadData() {}


  public void showProgressBar() {}


  public void hideProgressBar() {}


  public void initRecyclerView() {}


  public void initRefreshLayout() {}


  public void finishTask() {}







  @Override
  protected void onDestroy() {

    super.onDestroy();
    bind.unbind();
  }
}
