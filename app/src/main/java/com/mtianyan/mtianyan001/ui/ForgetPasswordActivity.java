package com.mtianyan.mtianyan001.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.ui
 * 文件名：ForgetPasswordActivity
 * 作者：mtianyan
 * 创建时间：2017/6/4 00:27
 * 描述：
 */
public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_now;
    private EditText et_new;
    private EditText et_new_password;
    private Button btn_update_password;
    private EditText et_email;
    private Button btn_forget_password;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.action_button_back_pressed_light);
        mToolbar.setTitle("忘记密码");
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                finish();
            }
        });
        et_now = (EditText) findViewById(R.id.et_now);
        et_new = (EditText) findViewById(R.id.et_new);
        et_new_password = (EditText) findViewById(R.id.et_new_password);
        btn_update_password = (Button) findViewById(R.id.btn_update_password);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_forget_password = (Button) findViewById(R.id.btn_forget_password);

        btn_update_password.setOnClickListener(this);
        btn_forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_update_password:
                //获取输入框值
                String passnow = et_now.getText().toString().trim();
                String pass = et_new.getText().toString().trim();
                String password = et_new_password.getText().toString().trim();
                //输入框不能为空
                if (!TextUtils.isEmpty(pass) & !TextUtils.isEmpty(passnow) & !TextUtils.isEmpty(password)){
                    //新密码两次一致
                    if (pass.equals(password)){
                        //重置密码
                        MyUser.updateCurrentUserPassword(passnow, password, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                //旧密码正确
                                if(e == null){
                                    Toast.makeText(ForgetPasswordActivity.this,
                                            "密码重置成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(ForgetPasswordActivity.this,"旧密码不正确"+e.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }else {
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_forget_password:
                //获取到邮箱值
                String email = et_email.getText().toString().trim();
                //邮箱不为kong
                if (!TextUtils.isEmpty(email)){
                    MyUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null){
                                Toast.makeText(ForgetPasswordActivity.this,"邮件已发送",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else {
                                Toast.makeText(ForgetPasswordActivity.this,"邮箱有误",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"输入邮箱不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
