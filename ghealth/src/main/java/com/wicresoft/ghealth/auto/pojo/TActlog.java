package com.wicresoft.ghealth.auto.pojo;

public class TActlog {
    private Long actlogId;

    private String url;

    private String accesstime;

    private String username;

    private String stat;

    private String info;

    public Long getActlogId() {
        return actlogId;
    }

    public void setActlogId(Long actlogId) {
        this.actlogId = actlogId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccesstime() {
        return accesstime;
    }

    public void setAccesstime(String accesstime) {
        this.accesstime = accesstime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}