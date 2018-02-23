package com.mtianyan.mtianyan001.ui;

import butterknife.ButterKnife;

import com.mtianyan.mtianyan001.MainActivity;
import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.utils.ShareUtils;
import com.mtianyan.mtianyan001.utils.StaticClass;
import com.mtianyan.mtianyan001.utils.UtilTools;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.ui
 * 文件名：SplashActivity
 * 作者：mtianyan
 * 创建时间：2017/5/31 22:26
 * 描述：闪屏
 */
public class SplashActivity extends Activity {

    private TextView tv_splash;
  private ImageView mSplashImage;

  private static final int ANIMATION_TIME = 2000;

  private static final float SCALE_END = 1.13F;

  private Subscription subscribe;


  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
      tv_splash = (TextView) findViewById(R.id.tv_splash);
      //设置字体
      UtilTools.setfont(this,tv_splash);
    mSplashImage = (ImageView) findViewById(R.id.splash_default_iv);
    ButterKnife.bind(this);
    subscribe = Observable.timer(1000, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(aLong -> {
          startAnim();
        });
  }


  private void startAnim() {

    ObjectAnimator animatorX = ObjectAnimator.ofFloat(mSplashImage, "scaleX", 1f, SCALE_END);
    ObjectAnimator animatorY = ObjectAnimator.ofFloat(mSplashImage, "scaleY", 1f, SCALE_END);

    AnimatorSet set = new AnimatorSet();
    set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
    set.start();

    set.addListener(new AnimatorListenerAdapter() {

      @Override
      public void onAnimationEnd(Animator animation) {

          if (isFirst()) {
              startActivity(new Intent(SplashActivity.this, GuideActivity.class));
          } else {
              startActivity(new Intent(SplashActivity.this, LoginActivity.class));
          }
          finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
      }
    });
  }


  @Override
  public void onBackPressed() {

    super.onBackPressed();
    if (subscribe != null && !subscribe.isUnsubscribed()) {
      subscribe.unsubscribe();
    }
  }
      //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST,true);
        if(isFirst){
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            //是第一次运行
            return true;
        }else {
            return false;
        }

    }
}
