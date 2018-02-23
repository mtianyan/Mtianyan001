package com.mtianyan.mtianyan001.entity;

/**
 * 项目名：Mtianyan001
 * 包名：com.mtianyan.mtianyan001.entity
 * 文件名：ChatListData
 * 作者：mtianyan
 * 创建时间：2017/6/9 00:27
 * 描述：
 */
public class ChatListData {
    private int type;
    private String text;

    @Override
    public String toString() {
        return "ChatListData{" +
                "type=" + type +
                ", text='" + text + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
