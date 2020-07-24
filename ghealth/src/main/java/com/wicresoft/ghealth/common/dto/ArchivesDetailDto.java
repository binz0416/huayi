package com.wicresoft.ghealth.common.dto;

import java.util.Date;

public class ArchivesDetailDto {
    private Long archivesDetailId;

    private String archivesId;

    private Date inspectionTime;

    private Integer type;

    private Long scId;

    private String advise;

    public Long getArchivesDetailId() {
        return archivesDetailId;
    }

    public void setArchivesDetailId(Long archivesDetailId) {
        this.archivesDetailId = archivesDetailId;
    }

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public Date getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getScId() {
        return scId;
    }

    public void setScId(Long scId) {
        this.scId = scId;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }
}