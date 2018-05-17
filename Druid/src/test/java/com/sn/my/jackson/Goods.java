package com.sn.my.jackson;

import java.math.BigInteger;

public class Goods {
    private BigInteger goodIds;
    private String data;
    private String status;
    private String ctime;
    private Long version;

    public BigInteger getGoodIds() {
        return goodIds;
    }

    public void setGoodIds(BigInteger goodIds) {
        this.goodIds = goodIds;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
