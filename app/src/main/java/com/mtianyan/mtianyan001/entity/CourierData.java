package com.mtianyan.mtianyan001.entity;

/*
 *  项目名：  Mtianyan001
 *  包名：    com.mtianyan.mtianyan001.entity
 *  文件名:   CourierData
 *  创建者:   mtianyan
 *  创建时间:  2017/6/8 23:37
 *  描述：    快递查询实体
 */

public class CourierData {

    //时间
    private String datetime;
    //状态
    private String remark;
    //城市
    private String zone;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "CourierData{" +
                "datetime='" + datetime + '\'' +
                ", remark='" + remark + '\'' +
                ", zone='" + zone + '\'' +
                '}';
    }
}
