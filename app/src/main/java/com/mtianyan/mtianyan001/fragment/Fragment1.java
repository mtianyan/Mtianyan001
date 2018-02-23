package com.mtianyan.mtianyan001.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtianyan.mtianyan001.R;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.fragment
 * 文件名：GrilFragment
 * 作者：mtianyan
 * 创建时间：2017/5/30 18:51
 * 描述：
 */
public class Fragment1 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_1,null);
        return view;
    }
}
