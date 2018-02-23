package com.mtianyan.mtianyan001.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mtianyan.mtianyan001.base.RxBaseActivity;
import com.mtianyan.mtianyan001.R;

import butterknife.BindView;

/**
 * <p/>
 * 关于我
 */
public class MtianyanInfoActivity extends RxBaseActivity {

  @BindView(R.id.toolbar)
  Toolbar mToolbar;


  @Override
  public int getLayoutId() {

    return R.layout.activity_mtianyaninfo;
  }


  @Override
  public void initViews(Bundle savedInstanceState) {

  }


  @Override
  public void initToolBar() {

    mToolbar.setTitle("关于我");
    setSupportActionBar(mToolbar);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onOptionsItemSelected(item);
  }
}
