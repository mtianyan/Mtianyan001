package com.mtianyan.mtianyan001.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.utils.StaticClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.ui
 * 文件名：PhoneActivity
 * 作者：mtianyan
 * 创建时间：2017/6/8 00:04
 * 描述：
 */
public class PhoneActivity extends AppCompatActivity {
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.iv_company)
    ImageView ivCompany;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_del)
    Button btnDel;
    @BindView(R.id.btn_4)
    Button btn4;
    @BindView(R.id.btn_5)
    Button btn5;
    @BindView(R.id.btn_6)
    Button btn6;
    @BindView(R.id.btn_0)
    Button btn0;
    @BindView(R.id.btn_7)
    Button btn7;
    @BindView(R.id.btn_8)
    Button btn8;
    @BindView(R.id.btn_9)
    Button btn9;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    //标记位
    private boolean flag = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.action_button_back_pressed_light);
        mToolbar.setTitle("归属地查询");
        mToolbar.setNavigationOnClickListener(v -> finish());
        btnDel.setOnLongClickListener(v -> {
            etNumber.setText("");
            return false;
        });
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_del, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_0, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_query})
    public void onViewClicked(View view) {
        /**
         * 逻辑
         * 1.获取输入框的内容
         * 2.判断是否为空
         * 3.网络请求
         * 4.解析Json
         * 5.结果显示
         *
         * ------
         * 键盘逻辑
         */
        String str = etNumber.getText().toString();
        switch (view.getId()) {
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_0:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if (flag) {
                    flag = false;
                    str = "";
                    etNumber.setText("");
                }
                etNumber.setText(str+((Button)view).getText());
                //移动光标
                etNumber.setSelection(str.length()+1);
                break;
            case R.id.btn_query:
                getPhone(str);
                break;
            case R.id.btn_del:
                if (!TextUtils.isEmpty(str) && str.length() >0){
                    //每次结尾减去1
                    etNumber.setText(str.substring(0,str.length()-1));
                    etNumber.setSelection(str.length()-1);

                }
                break;
        }
    }

    private void getPhone(String str) {
        RxVolley.get("http://apis.juhe.cn/mobile/get?phone=" + str + "&key="+ StaticClass.PHONE_KEY, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
//                Toast.makeText(PhoneActivity.this,t,Toast.LENGTH_LONG).show();
                jsonParse(t);
//
            }
        });
    }
    /**
     * "province":"浙江",
     * "city":"杭州",
     * "areacode":"0571",
     * "zip":"310000",
     * "company":"中国移动",
     * "card":"移动动感地带卡"
     */

    private void jsonParse(String t) {
        try {
            JSONObject json = new JSONObject(t);
            JSONObject jsonReslut = json.getJSONObject("result");
            String province = jsonReslut.getString("province");
            String city = jsonReslut.getString("city");
            String areacode = jsonReslut.getString("areacode");
            String zip = jsonReslut.getString("zip");
            String company = jsonReslut.getString("company");
            tvResult.setText("归属地:" + province + city + "\n"
                    + "区号:" + areacode + "\n"
                    + "邮编:" + zip + "\n"
                    + "运营商:" + company + "\n"
                    );

            //图片显示
            switch (company)
            {
                case "移动":
                    ivCompany.setBackgroundResource(R.drawable.china_mobile);
                    break;
                case "联通":
                    ivCompany.setBackgroundResource(R.drawable.china_unicom);
                    break;
                case "电信":
                    ivCompany.setBackgroundResource(R.drawable.china_telecom);
                    break;

            }
            flag = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
