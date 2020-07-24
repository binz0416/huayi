package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TMachineLog {
    private Long machineLogId;

    private Long machineId;

    private Date connectDate;

    private String connectResult;

    private String connectLog;

    public Long getMachineLogId() {
        return machineLogId;
    }

    public void setMachineLogId(Long machineLogId) {
        this.machineLogId = machineLogId;
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Date getConnectDate() {
        return connectDate;
    }

    public void setConnectDate(Date connectDate) {
        this.connectDate = connectDate;
    }

    public String getConnectResult() {
        return connectResult;
    }

    public void setConnectResult(String connectResult) {
        this.connectResult = connectResult;
    }

    public String getConnectLog() {
        return connectLog;
    }

    public void setConnectLog(String connectLog) {
        this.connectLog = connectLog;
    }
}