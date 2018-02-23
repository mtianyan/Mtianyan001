package com.mtianyan.mtianyan001.entity;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.entity
 * 文件名：GrilData
 * 作者：mtianyan
 * 创建时间：2017/6/9 18:33
 * 描述：妹子图
 */
public class GrilData {
    @Override
    public String toString() {
        return "GrilData{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
}
