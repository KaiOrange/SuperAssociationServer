package com.sa.entity;

import java.io.File;
public class UserMain {
	private int umid;
	private String name;
	private String password;
	private String nichen ;
	private String touxiang;
	private boolean sex;
	private int age;	
	private String brithday;
	private String telphone;
	private String myclass;
	private String stid;
	//保存图片的三个属性	
	private File pic;
	private String picContentType;
	private String picFileName;
	public UserMain() {
	}
	
	public UserMain(int umid, String name, String password, String nichen,
			String touxiang, boolean sex, int age, String brithday,
			String telphone, String myclass) {
		super();
		this.umid = umid;
		this.name = name;
		this.password = password;
		this.nichen = nichen;
		this.touxiang = touxiang;
		this.sex = sex;
		this.age = age;
		this.brithday = brithday;
		this.telphone = telphone;
		this.myclass = myclass;
	}

	public int getUmid() {
		return umid;
	}
	public void setUmid(int umid) {
		this.umid = umid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNichen() {
		return nichen;
	}
	public void setNichen(String nichen) {
		this.nichen = nichen;
	}
	public String getTouxiang() {
		return touxiang;
	}
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getMyclass() {
		return myclass;
	}
	public void setMyclass(String myclass) {
		this.myclass = myclass;
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

	public String getStid() {
		return stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	@Override
	public String toString() {
		return "UserMain [age=" + age + ", brithday=" + brithday + ", myclass="
				+ myclass + ", name=" + name + ", nichen=" + nichen
				+ ", password=" + password + ", pic=" + pic
				+ ", picContentType=" + picContentType + ", picFileName="
				+ picFileName + ", sex=" + sex + ", telphone=" + telphone
				+ ", touxiang=" + touxiang + ", umid=" + umid + "]";
	}

	
	
}
