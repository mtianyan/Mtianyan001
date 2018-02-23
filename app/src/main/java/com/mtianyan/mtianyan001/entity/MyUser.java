package com.mtianyan.mtianyan001.entity;

import cn.bmob.v3.BmobUser;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.entity
 * 文件名：MyUser
 * 作者：mtianyan
 * 创建时间：2017/6/2 13:37
 * 描述：用户属性
 */
public class MyUser extends BmobUser {

    private boolean sex;
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }



}
