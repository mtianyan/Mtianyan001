package com.mtianyan.mtianyan001.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.adapter.ChatAdapter;
import com.mtianyan.mtianyan001.entity.ChatListData;
import com.mtianyan.mtianyan001.utils.L;
import com.mtianyan.mtianyan001.utils.StaticClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.fragment
 * 文件名：UserFragment
 * 作者：mtianyan
 * 创建时间：2017/5/30 18:51
 * 描述：
 */
public class Fragment3 extends Fragment {
    @BindView(R.id.mChatListView)
    ListView mChatListView;
    Unbinder unbinder;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.btn_send)
    Button btnSend;
    private ChatAdapter adapter;
    private List<ChatListData> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_3, null);
        unbinder = ButterKnife.bind(this, view);
        //设置适配器
        adapter = new ChatAdapter(getActivity(), mList);
        mChatListView.setAdapter(adapter);
        addLeftItem("你好，我是小优");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //    @OnClick({R.id.btn_left, R.id.btn_right})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_left:
//                addLeftItem("左边");
//                break;
//            case R.id.btn_right:
//                addRightItem("右边");
//                break;
//        }
//
//    }
    //添加左边
    private void addLeftItem(String text) {
        ChatListData data = new ChatListData();
        data.setType(ChatAdapter.VALUE_LEFT_TEXT);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mChatListView.setSelection(mChatListView.getBottom());
    }

    private void addRightItem(String text) {
        ChatListData data = new ChatListData();
        data.setType(ChatAdapter.VALUE_RIGHT_TEXT);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mChatListView.setSelection(mChatListView.getBottom());
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        /**
         * 逻辑
         * 1.获取输入框的内容
         * 2.判断是否为空
         * 3.判断长度不能大于30
         * 4.清空当前的输入框
         * 5.添加你输入的内容到right item
         * 6.发送给机器人请求返回内容
         * 7.拿到机器人的返回值之后添加在left item
         */

        //1.获取输入框的内容
        String text = etText.getText().toString();
        //2.判断是否为空
        if (!TextUtils.isEmpty(text)) {
            //3.判断长度不能大于30
            if (text.length() > 30) {
                Toast.makeText(getActivity(), "您的输入过长请限制在30字以内", Toast.LENGTH_SHORT).show();
            } else {
                //4.清空当前的输入框
                etText.setText("");
                //5.添加你输入的内容到right item
                addRightItem(text);
                //6.发送给机器人请求返回内容
                String url = "http://op.juhe.cn/robot/index?info=" + text
                        + "&key=" + StaticClass.CHAT_LIST_KEY;
                RxVolley.get(url, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
//                        Toast.makeText(getActivity(), "Json:" + t, Toast.LENGTH_SHORT).show();
                        L.i("Json" + t);
                        parsingJson(t);
                    }
                });
            }
        } else {
            Toast.makeText(getActivity(),"输入不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObhect = new JSONObject(t);
            JSONObject jsonresult = jsonObhect.getJSONObject("result");
            //拿到返回值
            String text = jsonresult.getString("text");
            //7.拿到机器人的返回值之后添加在left item
            addLeftItem(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

