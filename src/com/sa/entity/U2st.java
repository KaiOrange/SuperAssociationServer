package com.sa.entity;

public class U2st {
	private int U2stid;
	private int umid;
	private int stid;
	private int status = 0;//1:已经加入社团了 0:等待审核
	public U2st() {
	}
	public U2st(int u2stid, int umid, int stid, int status) {
		super();
		U2stid = u2stid;
		this.umid = umid;
		this.stid = stid;
		this.status = status;
	}
	public int getU2stid() {
		return U2stid;
	}
	public void setU2stid(int u2stid) {
		U2stid = u2stid;
	}
	public int getUmid() {
		return umid;
	}
	public void setUmid(int umid) {
		this.umid = umid;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "U2st [U2stid=" + U2stid + ", status=" + status + ", stid="
				+ stid + ", umid=" + umid + "]";
	}

}
