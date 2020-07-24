package com.wicresoft.ghealth.auto.pojo;

import java.util.Date;

public class TArchives {
    private String archivesId;

    private String customerId;

    private Date archivesCreateTime;

    private String advise;

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getArchivesCreateTime() {
        return archivesCreateTime;
    }

    public void setArchivesCreateTime(Date archivesCreateTime) {
        this.archivesCreateTime = archivesCreateTime;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }
}