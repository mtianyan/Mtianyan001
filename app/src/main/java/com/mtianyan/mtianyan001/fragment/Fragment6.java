package com.mtianyan.mtianyan001.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mtianyan.mtianyan001.R;
import com.mtianyan.mtianyan001.entity.MyUser;
import com.mtianyan.mtianyan001.ui.CourierActivity;
import com.mtianyan.mtianyan001.ui.LoginActivity;
import com.mtianyan.mtianyan001.ui.PhoneActivity;
import com.mtianyan.mtianyan001.utils.L;
import com.mtianyan.mtianyan001.utils.UtilTools;
import com.mtianyan.mtianyan001.view.CustomDialog;
import com.mtianyan.mtianyan001.widget.CircleImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.fragment
 * 文件名：Fragment6
 * 作者：mtianyan
 * 创建时间：2017/6/5 13:19
 * 描述：
 */
public class Fragment6 extends Fragment implements View.OnClickListener {
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    Unbinder unbinder;
    private Button btn_exit_user;
    private TextView et_name;
    private EditText et_username;
    private EditText sex;
    private EditText et_desc;
    private Button btn_update_ok;
    private Button btn_cancel;
    private Button btn_picture;
    private Button btn_camera;
    boolean sexattr;
    private CustomDialog dialog;
    private CircleImageView profile_image;
    private File tempfile;
    private TextView tv_courier;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page_6, null);
        findView(view);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //初始化view
    private void findView(View view) {
        tv_courier = (TextView) view.findViewById(R.id.tv_courier);
        tv_courier.setOnClickListener(this);
        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        UtilTools.getImageToShare(getActivity(), profile_image);
        btn_update_ok = (Button) view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);
        et_name = (TextView) view.findViewById(R.id.edit_user);
        et_name.setOnClickListener(this);
        btn_exit_user = (Button) view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        //启动相机等的dialog
        dialog = new CustomDialog(getActivity(), 100, 100,
                R.layout.dialog_photo, R.style.Theme_dialog, Gravity.BOTTOM, R.style.pop_anim_style);
        dialog.setCancelable(false);
        btn_camera = (Button) dialog.findViewById(R.id.btn_camera);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_picture = (Button) dialog.findViewById(R.id.btn_picture);
        btn_camera.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_picture.setOnClickListener(this);
        et_name = (TextView) view.findViewById(R.id.et_username);
        sex = (EditText) view.findViewById(R.id.et_sex);
        et_desc = (EditText) view.findViewById(R.id.et_desc);

        //默认是不可输入的
        et_name.setEnabled(false);
        sex.setEnabled(false);
        et_desc.setEnabled(false);
//默认不可见
        btn_update_ok.setVisibility(View.GONE);
        //设置具体的值
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        et_name.setText(user.getUsername());
        et_desc.setText(user.getDesc());
        if (user.isSex()) {
            sex.setText("男");
        } else {
            sex.setText("女");
        }
        //控制焦点
        setEnabled(false);
    }

    private void setEnabled(boolean is) {
        et_name.setEnabled(is);
        sex.setEnabled(is);
        et_desc.setEnabled(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exit_user:
                MyUser.logOut();
                BmobUser currentuser = MyUser.getCurrentUser();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
//            编辑资料
            case R.id.edit_user:
                setEnabled(true);
                btn_update_ok.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_update_ok:
                //获取输入框值
                String username = et_name.getText().toString().trim();
                String sexty = sex.getText().toString().trim();

                if (sexty.equals("男")) {
                    sexattr = true;
                } else if (sexty.equals("女")) {
                    sexattr = false;
                }
                String desc = et_desc.getText().toString().trim();
                MyUser newUser = new MyUser();
                newUser.setDesc(desc);
                newUser.setSex(sexattr);
                newUser.setUsername(username);
                BmobUser bmobUser = BmobUser.getCurrentUser();
                newUser.update(bmobUser.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(getContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                et_name.setEnabled(false);
                sex.setEnabled(false);
                et_desc.setEnabled(false);
                et_name.setText(username);
                et_desc.setText(desc);
//                if (newUser.isSex()){
//                    sex.setText("男");
//                }else {
//                    sex.setText("女");
//                }
                sex.setText(newUser.isSex() ? "男" : "女");
                btn_update_ok.setVisibility(View.GONE);
                break;
            case R.id.profile_image:
                dialog.show();
                break;
            case R.id.btn_camera:
                toCamera();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;

            case R.id.btn_picture:
                toPicture();
                break;
            case R.id.tv_courier:
                startActivity(new Intent(getActivity(), CourierActivity.class));
                break;


        }
    }

    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_REQUEST_CODE = 101;
    public static final int RESULT_REQUEST_CODE = 102;
    private File tempFile = null;

    //跳转相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断内存卡是否可用，可用的话就进行储存
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME)));
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                //相册数据
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                //相机数据
                case CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                    startPhotoZoom(Uri.fromFile(tempFile));
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //既然已经设置了图片，我们原先的就应该删除
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }

    //裁剪
    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
            L.e("uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            profile_image.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //保存
        UtilTools.putImageToShare(getActivity(), profile_image);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_phone)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), PhoneActivity.class));
    }
}
