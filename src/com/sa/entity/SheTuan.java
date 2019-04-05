package com.sa.entity;

import java.io.File;


public class SheTuan {
	private int stid;
	private String sname;
	private String snotice;
	private String sdescribe;
	private String cjdate;
	private String touxiang;
	private int umid;
	//创始人姓名
	private String umName;
	
	//保存图片方法
	private File pic;
	private String picContentType;
	private String picFileName;
	public SheTuan() {
	}
	



	public SheTuan(int stid, String sname, String snotice, String sdescribe,
			String cjdate, String touxiang, int umid) {
		super();
		this.stid = stid;
		this.sname = sname;
		this.snotice = snotice;
		this.sdescribe = sdescribe;
		this.cjdate = cjdate;
		this.touxiang = touxiang;
		this.umid = umid;
	}




	public String getTouxiang() {
		return touxiang;
	}
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSnotice() {
		return snotice;
	}
	public void setSnotice(String snotice) {
		this.snotice = snotice;
	}
	public String getSdescribe() {
		return sdescribe;
	}
	public void setSdescribe(String sdescribe) {
		this.sdescribe = sdescribe;
	}
	public String getCjdate() {
		return cjdate;
	}
	public void setCjdate(String cjdate) {
		this.cjdate = cjdate;
	}
	public File getPic() {
		return pic;
	}
	public void setPic(File pic) {
		this.pic = pic;
	}
	public String getPicContentType() {
		return picContentType;
	}
	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public int getUmid() {
		return umid;
	}
	public void setUmid(int umid) {
		this.umid = umid;
	}

	public String getUmName() {
		return umName;
	}
	public void setUmName(String umName) {
		this.umName = umName;
	}
	@Override
	public String toString() {
		return "SheTuan [cjdate=" + cjdate + ", sdescribe=" + sdescribe
				+ ", sname=" + sname + ", snotice=" + snotice + ", stid="
				+ stid + ", touxiang=" + touxiang + ", umid=" + umid + "]";
	}

}
