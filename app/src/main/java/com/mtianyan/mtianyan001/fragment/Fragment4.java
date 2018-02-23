package com.mtianyan.mtianyan001.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.adapter.WeChatAdapter;
import com.mtianyan.mtianyan001.entity.WeChatData;
import com.mtianyan.mtianyan001.ui.BrowserActivity;
import com.mtianyan.mtianyan001.ui.WebViewActivity;
import com.mtianyan.mtianyan001.utils.L;
import com.mtianyan.mtianyan001.utils.StaticClass;
import com.mtianyan.mtianyan001.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.fragment
 * 文件名：WeChatFragment
 * 作者：mtianyan
 * 创建时间：2017/5/30 18:51
 * 描述：
 */
public class Fragment4 extends Fragment {
    @BindView(R.id.mListView)
    ListView mListView;
    Unbinder unbinder;
    private List<WeChatData> mList = new ArrayList<>();
    //标题
    private List<String> mListTitle = new ArrayList<>();
    //地址
    private List<String> mListUrl = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_4, null);
        unbinder = ButterKnife.bind(this, view);
        //解析接口
        parseInterface();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                L.i("postion"+position);
                Intent intent = new Intent(getActivity(),BrowserActivity.class);
                intent.putExtra("title", mListTitle.get(position));
                intent.putExtra("url", mListUrl.get(position));
                startActivity(intent);
            }
        });
        return view;
    }

    private void parseInterface() {
        String url = "http://v.juhe.cn/weixin/query?key="+ StaticClass.WECHAT_KEY+"&ps=50";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
//                Toast.makeText(getActivity(),t,Toast.LENGTH_LONG).show();
                parseJson(t);
                super.onSuccess(t);
            }
        });
    }
//    {
//        "reason": "success",
//            "result": {
//        "list": [
//        {
//            "id": "wechat_20150401071581",
//                "title": "号外：集宁到乌兰花的班车出事了！！！！！",
//                "source": "内蒙那点事儿",
//                "firstImg": "http://zxpic.gtimg.com/infonew/0/wechat_pics_-214279.jpg/168",
//                "mark": "",
//                "url": "http://v.juhe.cn/weixin/redirect?wid=wechat_20150401071581"
//        },
    private void parseJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonresult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonresult.getJSONArray("list");
            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                WeChatData data = new WeChatData();
                String title = json.getString("title");
                String url = json.getString("url");
                data.setTitle(json.getString("title"));
                data.setSource(json.getString("source"));
                data.setImgUrl(json.getString("firstImg"));
                mList.add(data);
                mListTitle.add(title);
                mListUrl.add(url);
                }
            WeChatAdapter adapter = new WeChatAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
