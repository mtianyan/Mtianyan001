package com.mtianyan.mtianyan001.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.entity.GrilData;
import com.mtianyan.mtianyan001.utils.PicassoUtils;

import java.util.List;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.adapter
 * 文件名：GridAdapter
 * 作者：mtianyan
 * 创建时间：2017/6/9 18:34
 * 描述：妹子图的adapter
 */
public class GridAdapter extends BaseAdapter{
    private Context context;
    private List<GrilData> mList;
    private LayoutInflater inflater;
    private GrilData data;
    private WindowManager wm;
    private int width,height;

    public GridAdapter(Context context, List<GrilData> mList) {
        this.context = context;
        this.mList = mList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.grid_item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        data = mList.get(position);
        //解析图片
        String url = data.getUrl();
        if (!TextUtils.isEmpty(url)){
            PicassoUtils.loadImageViewSize(context,url,width/2,400,viewHolder.imageView);
        }
        return convertView;
    }
    class ViewHolder{
        private ImageView imageView;
    }
}
