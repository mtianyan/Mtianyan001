package com.mtianyan.mtianyan001.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.adapter.GridAdapter;
import com.mtianyan.mtianyan001.entity.GrilData;
import com.mtianyan.mtianyan001.utils.PicassoUtils;
import com.mtianyan.mtianyan001.utils.StaticClass;
import com.mtianyan.mtianyan001.utils.ToastUtils;
import com.mtianyan.mtianyan001.view.CustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.bugly.crashreport.crash.c.d;
import static com.tencent.bugly.crashreport.crash.c.j;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.fragment
 * 文件名：Fragment5
 * 作者：mtianyan
 * 创建时间：2017/6/5 13:17
 * 描述：
 */
public class Fragment5 extends Fragment {
    private CustomDialog dialog;
    private GridView mGridView;
    private List<GrilData> mList = new ArrayList<>();
    private GridAdapter gridAdapter;
    //预览托图片
    private ImageView imageViewPh;
    //图片地址的数据
    private List<String> mListUrl = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_5,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        //初始化提示框
                dialog = new CustomDialog(getActivity(), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,R.layout.dialog_girl,R.style.Theme_dialog, Gravity.CENTER);
        imageViewPh = (ImageView) dialog.findViewById(R.id.iv_img);
      mGridView = (GridView) view.findViewById(R.id.mGridView);

        //解析
        RxVolley.get(StaticClass.GIRL_URL, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
//                Toast.makeText(getContext(),t,Toast.LENGTH_SHORT).show();
                ParseJson(t);

                mGridView.setOnItemClickListener((parent, view1, position, id) -> {

                    //加载图片
                    PicassoUtils.loadImaheView(getActivity(),mListUrl.get(position),imageViewPh);
                    //photoView实现缩放
                    dialog.show();

                });
            }
        });
    }

    private void ParseJson(String t) {
        try {
            JSONObject jsonobject = new JSONObject(t);
            JSONArray jsonarry = jsonobject.getJSONArray("results");
            for (int i = 0; i < jsonarry.length(); i++){
               JSONObject json = (JSONObject) jsonarry.get(i);
                String url =json.getString("url");
                mListUrl.add(url);

                GrilData data = new GrilData();
                data.setUrl(url);
                mList.add(data);
            }
            gridAdapter = new GridAdapter(getActivity(),mList);
            mGridView.setAdapter(gridAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
