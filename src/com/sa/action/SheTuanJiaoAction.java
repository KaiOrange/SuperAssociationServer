package com.sa.action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.ISheTuanDao;
import com.sa.dao.ISheTuanJiaoDao;
import com.sa.dao.IUm2UmDao;
import com.sa.dao.IUserMainDao;
import com.sa.dao.impl.SheTuanDaoImpl;
import com.sa.dao.impl.SheTuanJiaoDaoImpl;
import com.sa.dao.impl.Um2UmDaoImpl;
import com.sa.dao.impl.UserMainDaoImpl;
import com.sa.entity.SheTuan;
import com.sa.entity.SheTuanJiao;
import com.sa.entity.Um2Um;
import com.sa.entity.UserMain;
import com.sa.tools.PrintText2Client;

public class SheTuanJiaoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private SheTuanJiao sheTuanJiao;
	private int umid;
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUmid() {
		return umid;
	}

	public void setUmid(int umid) {
		this.umid = umid;
	}

	public SheTuanJiao getSheTuanJiao() {
		return sheTuanJiao;
	}

	public void setSheTuanJiao(SheTuanJiao sheTuanJiao) {
		this.sheTuanJiao = sheTuanJiao;
	}
	public String createSheTuanJiao(){
		String returnStr = "failure";
		if (sheTuanJiao != null) {
			if (sheTuanJiao.getName() == null || "".equals(sheTuanJiao.getName())) {
				sheTuanJiao.setName("-1"); 
			} 
			IUserMainDao userMainDao = new UserMainDaoImpl();
			UserMain userMain = userMainDao.findUserMainByName(sheTuanJiao.getName());
			IUm2UmDao um2UmDao = new Um2UmDaoImpl();
			Um2Um um2Um = null;
			ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
			SheTuan shetuan1 = sheTuanDao.findByUid(umid);
			SheTuan shetuan2 = null;
			if (userMain != null) {
				um2Um = um2UmDao.findById(umid, userMain.getUmid());
				shetuan2 = sheTuanDao.findByUid(userMain.getUmid());
			}
			if(userMain != null && (um2Um == null || (shetuan1 != null && shetuan2 != null && shetuan1.getStid() != shetuan2.getStid())) ){
				returnStr = "notFriend";
			} else {
				ISheTuanJiaoDao sheTuanJiaoDao = new SheTuanJiaoDaoImpl();
				boolean save = sheTuanJiaoDao.save(sheTuanJiao);
				if (save) {
					returnStr = "seccess";
				}					
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String reloadSheTuanJiao(){
		String returnStr = "failure";
		ISheTuanJiaoDao sheTuanJiaoDao = new SheTuanJiaoDaoImpl();
		SheTuanJiao findRandom = sheTuanJiaoDao.findRandom(name);
		if (findRandom != null) {
			returnStr = new Gson().toJson(findRandom);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
}
