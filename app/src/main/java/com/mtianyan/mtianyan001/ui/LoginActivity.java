package com.mtianyan.mtianyan001.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mtianyan.mtianyan001.MainActivity;
import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.entity.MyUser;
import com.mtianyan.mtianyan001.utils.HttpUtils;
import com.mtianyan.mtianyan001.utils.MtianyanUtils;
import com.mtianyan.mtianyan001.utils.ShareUtils;
import com.mtianyan.mtianyan001.utils.ToastUtils;
import com.mtianyan.mtianyan001.widget.CustomDialog;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.ui
 * 文件名：LoginActivity
 * 作者：mtianyan
 * 创建时间：2017/6/2 11:40
 * 描述：登录
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //注册按钮
    private Button btn_registered;
    private Button btn_login;
    private EditText et_name;
    private EditText et_password;
    private CheckBox keep_password;
    private TextView tv_forget;
    private ImageView iv_icon_right;
    private ImageView iv_icon_left;
    private Toolbar mToolbar;
    private ImageView mDeleteUserName;
    private CustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        dialog = new CustomDialog(this, 100, 100, R.layout.dialog_loding, R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
        dialog.setCancelable(false);
        mDeleteUserName = (ImageView) findViewById(R.id.delete_username);
        mDeleteUserName.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_cancle);
        mToolbar.setTitle("登录");
        mToolbar.setNavigationOnClickListener(v -> finish());
        iv_icon_left = (ImageView) findViewById(R.id.iv_icon_left);
        iv_icon_right = (ImageView) findViewById(R.id.iv_icon_right);

        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        btn_registered = (Button) findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_name = (EditText) findViewById(R.id.et_name);
        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 如果用户名清空了 清空密码 清空记住密码选项
                et_password.setText("");
                if (s.length() > 0)
                {
                    // 如果用户名有内容时候 显示删除按钮
                    mDeleteUserName.setVisibility(View.VISIBLE);
                } else
                {
                    // 如果用户名有内容时候 显示删除按钮
                    mDeleteUserName.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login.setOnClickListener(this);
        et_password.setOnFocusChangeListener((v, hasFocus) -> {
                iv_icon_left.setImageResource(R.drawable.ic_22_hide);
                iv_icon_right.setImageResource(R.drawable.ic_33_hide);

        });
        et_name.setOnFocusChangeListener((v, hasFocus) ->  {
                if (hasFocus && et_name.getText().length() > 0)
                {
                    mDeleteUserName.setVisibility(View.VISIBLE);
                } else
                {
                    mDeleteUserName.setVisibility(View.GONE);
                }
                iv_icon_left.setImageResource(R.drawable.ic_22);
                iv_icon_right.setImageResource(R.drawable.ic_33);
            }
            );
        //设置默认选中的状态
        boolean isCheck = ShareUtils.getBoolean(this,"keeppass",false);
        keep_password.setChecked(isCheck);
        if (isCheck){
            //设置密码用户名
            et_name.setText(ShareUtils.getString(this,"name",""));
            et_password.setText(ShareUtils.getString(this,"password",""));
        }
    }

    public void initBar() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_username:
                // 清空用户名以及密码
                et_name.setText("");
                et_password.setText("");
                mDeleteUserName.setVisibility(View.GONE);
                et_name.setFocusable(true);
                et_name.setFocusableInTouchMode(true);
                et_name.requestFocus();
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
            case R.id.btn_registered:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.btn_login:

                login();




       }
    }

    private void login() {
        boolean isNetConnected = MtianyanUtils.isNetworkAvailable(this);
        if (!isNetConnected)
        {
            ToastUtils.ShortToast("当前网络不可用,请检查网络设置");
            return;
        }
        //获取输入框的值
        String name = et_name.getText().toString().trim();
        String pass = et_password.getText().toString().trim();
        //判断值不为空
        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(pass)){
            //小组后端实现
            dialog.show();
            //Bmob后端实现

            final MyUser user = new MyUser();
            user.setUsername(name);
            user.setPassword(pass);
            user.login(new SaveListener<MyUser>() {

                @Override
                public void done(MyUser myUser, BmobException e) {
                    dialog.dismiss();
                    if (e == null){

                        //判断邮箱是否验证
                        if (user.getEmailVerified()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"请前往邮箱进行验证"+e.getErrorCode()+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this,"登录失败"+e.getErrorCode()+e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

                    //电子竞拍系统实现
//                    if(loginPro()){
//                        startActivity(new Intent(this,MainActivity.class));
//                    }
//                    else {
//                        Toast.makeText(this,"您输入的用户名或密码有误",Toast.LENGTH_SHORT).show();
//                    }
        }else {
            Toast.makeText(this,"输入框值不能为空",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean loginPro() {
        JSONObject jsonObj;
        try{
            String name = et_name.getText().toString().trim();
            String pass = et_password.getText().toString().trim();
            jsonObj = query(name, pass);
            // 如果userId 大于0
            if (jsonObj.getInt("userId") > 0)
            {
                return true;
            }
        }catch (Exception e){
            Toast.makeText(this,"输入框值不能为空",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private JSONObject query(String name, String pass) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("user",name);
        map.put("pass",pass);
        //定义发送请求的URl
        String url = HttpUtils.BASE_URL;
        //发送请求
        return new JSONObject(HttpUtils.postRequest(url,map));

    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        //保存当前CheckBox状态
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());
        //是否记住密码
        if (keep_password.isChecked()){
            //记住用户名和密码
            ShareUtils.putString(this,"name",et_name.getText().toString().trim());
            ShareUtils.putString(this,"password",et_password.getText().toString().trim());

        }else {
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");

        }
    }
}
