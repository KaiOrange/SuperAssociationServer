package com.sa.entity;


public class ActivityTopic {
	private int atid; 
	private int aid; 
	private String attext;
	private int umid;
	private String atdate;
	
	//回复数量 不往数据库中插入 查询时需要;
	private int replyCount;
	
	public ActivityTopic() {
	}
	public ActivityTopic(int atid, int aid, String attext, int umid,
			String atdate) {
		super();
		this.atid = atid;
		this.aid = aid;
		this.attext = attext;
		this.umid = umid;
		this.atdate = atdate;
	}
	public int getAtid() {
		return atid;
	}
	public void setAtid(int atid) {
		this.atid = atid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAttext() {
		return attext;
	}
	public void setAttext(String attext) {
		this.attext = attext;
	}
	public int getUmid() {
		return umid;
	}
	public void setUmid(int umid) {
		this.umid = umid;
	}
	public String getAtdate() {
		return atdate;
	}
	public void setAtdate(String atdate) {
		this.atdate = atdate;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

}
