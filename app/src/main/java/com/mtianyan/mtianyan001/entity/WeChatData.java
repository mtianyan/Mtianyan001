package com.mtianyan.mtianyan001.entity;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.entity
 * 文件名：WeChatData
 * 作者：mtianyan
 * 创建时间：2017/6/9 14:19
 * 描述：微信数据
 *
 */
public class WeChatData {
    //标题
    private String title;
    //出处
    private String source;
    //图片的url
    private String imgUrl;
    //新闻地址


    @Override
    public String toString() {
        return "WeChatData{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
