package com.wicresoft.ghealth.common.dto;

public class PhysicalDetailInfo {
    private Long physicalDetailId;

    private Long physicalId;

    private String projectId;

    private String projectValue;

    public Long getPhysicalDetailId() {
        return physicalDetailId;
    }

    public void setPhysicalDetailId(Long physicalDetailId) {
        this.physicalDetailId = physicalDetailId;
    }

    public Long getPhysicalId() {
        return physicalId;
    }

    public void setPhysicalId(Long physicalId) {
        this.physicalId = physicalId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(String projectValue) {
        this.projectValue = projectValue;
    }
}