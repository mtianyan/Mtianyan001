package com.mtianyan.mtianyan001.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mtianyan.mtianyan001.MainActivity;
import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.entity.MyUser;
import com.mtianyan.mtianyan001.utils.HttpUtils;
import com.mtianyan.mtianyan001.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.ui
 * 文件名：RegisterActivity
 * 作者：mtianyan
 * 创建时间：2017/6/2 13:11
 * 描述：注册
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_user;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_email;
    private Button btnRegistered;
    private ImageView iv_icon_right;
    private ImageView iv_icon_left;
    private Toolbar mToolbar;
    private boolean isGender = true;
    private ImageView mDeleteUserName;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getPermission();
    }
    //获取手机状态权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 102);
        } else {
            initView();
        }
    }
    @SuppressLint("NewApi")
    private void initView() {
        et_desc = (EditText) findViewById(R.id.et_desc);
        mDeleteUserName = (ImageView) findViewById(R.id.delete_username);
        mDeleteUserName.setOnClickListener(this);
        iv_icon_left = (ImageView) findViewById(R.id.iv_icon_left);
        iv_icon_right = (ImageView) findViewById(R.id.iv_icon_right);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        mToolbar.setNavigationIcon(R.drawable.action_button_back_pressed_light);
        mToolbar.setTitle("注册");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                finish();
            }
        });
        et_user = (EditText) findViewById(R.id.et_user);
        et_user.addTextChangedListener(new TextWatcher() {
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
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);

        btnRegistered.setOnClickListener(this);

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                iv_icon_left.setImageResource(R.drawable.ic_22_hide);
                iv_icon_right.setImageResource(R.drawable.ic_33_hide);
            }
        });
        et_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                iv_icon_left.setImageResource(R.drawable.ic_22_hide);
                iv_icon_right.setImageResource(R.drawable.ic_33_hide);
            }
        });
        et_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && et_user.getText().length() > 0)
                {
                    mDeleteUserName.setVisibility(View.VISIBLE);
                } else
                {
                    mDeleteUserName.setVisibility(View.GONE);
                }
                iv_icon_left.setImageResource(R.drawable.ic_22);
                iv_icon_right.setImageResource(R.drawable.ic_33);
            }
        });
        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                iv_icon_left.setImageResource(R.drawable.ic_22);
                iv_icon_right.setImageResource(R.drawable.ic_33);
            }
        });

    }
    private void parsingJson(String t) {
        try {
            JSONObject jsonObhect = new JSONObject(t);
            String jsonresult = jsonObhect.getString("userstatus");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistered:
                //获取到输入框的值:trim去空格
                String name = et_user.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                //判断是否为空
                if(!TextUtils.isEmpty(name)
                        & !TextUtils.isEmpty(email)
                        & !TextUtils.isEmpty(pass)
                        & !TextUtils.isEmpty(password)){
                    //判断两次输入的密码是否一致
                    if (pass.equals(password)){
                        //先把性别判断一下
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId ==R.id.rb_boy){
                                    isGender = true;
                                }else if (checkedId == R.id.rb_girl){
                                    isGender = false;
                                }
                            }
                        });
                        //判断简介是否为空
                        if(TextUtils.isEmpty(desc)){
                            desc = "这个人很懒，什么都没有填";
                        }
                        //注册
//                        MyUser user = new MyUser();
//                        user.setUsername(name);
//                        user.setPassword(password);
//                        user.setEmail(email);
//                        user.setSex(isGender);
//                        user.setDesc(desc);
//
//                        user.signUp(new SaveListener<MyUser>() {
//                            @Override
//                            public void done(MyUser myUser, BmobException e) {
//                                if(e==null){
//                                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }else{
//                                    Toast.makeText(RegisterActivity.this,"注册失败: "+e.toString(),Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
                        //注册
                        try {
                            String status = HttpUtils.getRequest(HttpUtils.BASE_URL+"?username="+name+"&password="+password);

                            if (status.equals("success")){
                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else{

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
