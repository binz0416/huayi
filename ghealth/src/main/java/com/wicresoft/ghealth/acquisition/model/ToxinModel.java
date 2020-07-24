package com.wicresoft.ghealth.acquisition.model;

import java.util.Date;
import java.util.List;

public class ToxinModel {
    private Long toxinId;

    private String path;

    private String no;

    private Date inspectionTime;
    
    List<String> exdetail;
    
    List<ToxinDetailModel> detail;

	public Long getToxinId() {
		return toxinId;
	}

	public void setToxinId(Long toxinId) {
		this.toxinId = toxinId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(Date inspectionTime) {
		this.inspectionTime = inspectionTime;
	}

	public List<String> getExdetail() {
		return exdetail;
	}

	public void setExdetail(List<String> exdetail) {
		this.exdetail = exdetail;
	}

	public List<ToxinDetailModel> getDetail() {
		return detail;
	}

	public void setDetail(List<ToxinDetailModel> detail) {
		this.detail = detail;
	}


}
