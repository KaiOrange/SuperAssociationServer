package com.sa.entity;

public class ActivityTopicReply {
	private int atrid;
	private int atid;
	private String atrtext;
	private int umidme;
	private String nameYou;
	private String atdate;
	public ActivityTopicReply() {
	}
	public ActivityTopicReply(int atrid, int atid, String atrtext, int umidme,
			String nameYou, String atdate) {
		super();
		this.atrid = atrid;
		this.atid = atid;
		this.atrtext = atrtext;
		this.umidme = umidme;
		this.nameYou = nameYou;
		this.atdate = atdate;
	}
	public int getAtrid() {
		return atrid;
	}
	public void setAtrid(int atrid) {
		this.atrid = atrid;
	}
	public int getAtid() {
		return atid;
	}
	public void setAtid(int atid) {
		this.atid = atid;
	}
	public String getAtrtext() {
		return atrtext;
	}
	public void setAtrtext(String atrtext) {
		this.atrtext = atrtext;
	}
	public int getUmidme() {
		return umidme;
	}
	public void setUmidme(int umidme) {
		this.umidme = umidme;
	}
	public String getNameYou() {
		return nameYou;
	}
	public void setNameYou(String nameYou) {
		this.nameYou = nameYou;
	}
	public String getAtdate() {
		return atdate;
	}
	public void setAtdate(String atdate) {
		this.atdate = atdate;
	}

	
}
