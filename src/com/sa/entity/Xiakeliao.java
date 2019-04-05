package com.sa.entity;


public class Xiakeliao {
	private int xklid;
	private String xkltitle;
	private String xkltext;
	private int type;
	private int umid;
	private String cjdate;
	
	//用户信息
	private UserMain userMain;
	//评论数量
	private int replyCount;
	
	public Xiakeliao() {
	}


	public Xiakeliao(int xklid, String xkltitle, String xkltext, int type,
			int umid, String cjdate) {
		super();
		this.xklid = xklid;
		this.xkltitle = xkltitle;
		this.xkltext = xkltext;
		this.type = type;
		this.umid = umid;
		this.cjdate = cjdate;
	}



	public String getXkltitle() {
		return xkltitle;
	}



	public void setXkltitle(String xkltitle) {
		this.xkltitle = xkltitle;
	}



	public int getXklid() {
		return xklid;
	}



	public void setXklid(int xklid) {
		this.xklid = xklid;
	}
	public String getXkltext() {
		return xkltext;
	}
	public void setXkltext(String xkltext) {
		this.xkltext = xkltext;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUmid() {
		return umid;
	}
	public void setUmid(int umid) {
		this.umid = umid;
	}
	public String getCjdate() {
		return cjdate;
	}
	public void setCjdate(String cjdate) {
		this.cjdate = cjdate;
	}

	public UserMain getUserMain() {
		return userMain;
	}


	public void setUserMain(UserMain userMain) {
		this.userMain = userMain;
	}


	public int getReplyCount() {
		return replyCount;
	}


	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}


	@Override
	public String toString() {
		return "Xiakeliao [cjdate=" + cjdate + ", type=" + type + ", umid="
				+ umid + ", xklid=" + xklid + ", xkltext=" + xkltext
				+ ", xkltitle=" + xkltitle + "]";
	}
	
	
	
}
