package com.sa.entity;

public class ActivityTopicImg {
	private int imgid;
	private String imgpath;
	private int atid;
	public ActivityTopicImg() {
	}
	public ActivityTopicImg(int imgid, String imgpath, int atid) {
		super();
		this.imgid = imgid;
		this.imgpath = imgpath;
		this.atid = atid;
	}
	public int getImgid() {
		return imgid;
	}
	public void setImgid(int imgid) {
		this.imgid = imgid;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public int getAtid() {
		return atid;
	}
	public void setAtid(int atid) {
		this.atid = atid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + atid;
		result = prime * result + imgid;
		result = prime * result + ((imgpath == null) ? 0 : imgpath.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityTopicImg other = (ActivityTopicImg) obj;
		if (atid != other.atid)
			return false;
		if (imgid != other.imgid)
			return false;
		if (imgpath == null) {
			if (other.imgpath != null)
				return false;
		} else if (!imgpath.equals(other.imgpath))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ActivityTopicImg [atid=" + atid + ", imgid=" + imgid
				+ ", imgpath=" + imgpath + "]";
	}
	
}
