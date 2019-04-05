package com.sa.entity;

public class Um2Um {
	private int um2umid;
	private int umid1;
	private int umid2;
	public Um2Um() {
	}
	public Um2Um(int um2umid, int umid1, int umid2) {
		super();
		this.um2umid = um2umid;
		this.umid1 = umid1;
		this.umid2 = umid2;
	}
	public int getUm2umid() {
		return um2umid;
	}
	public void setUm2umid(int um2umid) {
		this.um2umid = um2umid;
	}
	public int getUmid1() {
		return umid1;
	}
	public void setUmid1(int umid1) {
		this.umid1 = umid1;
	}
	public int getUmid2() {
		return umid2;
	}
	public void setUmid2(int umid2) {
		this.umid2 = umid2;
	}
	@Override
	public String toString() {
		return "Um2Um [um2umid=" + um2umid + ", umid1=" + umid1 + ", umid2="
				+ umid2 + "]";
	}
	
}
