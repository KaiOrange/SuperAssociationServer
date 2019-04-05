package com.sa.action;

import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.ISheTuanAdminDao;
import com.sa.dao.impl.SheTuanDaoImpl;
import com.sa.dao.impl.ShetuanAdminDaoImpl;
import com.sa.entity.SheTuan;
import com.sa.entity.ShetuanAdmin;
import com.sa.tools.PrintText2Client;

public class SheTuanAdminAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int stid;
	private List<Integer> ids;


	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}
	public String getSheTuanAdmin(){
		ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
		List<ShetuanAdmin> sheTuanAdmins = adminDao.findByStid(stid);
		String returnStr = "failure";
		if (sheTuanAdmins != null && sheTuanAdmins.size() > 0) {
			ShetuanAdmin[] adminsArray = sheTuanAdmins.toArray(new ShetuanAdmin[]{}); 
			returnStr = new Gson().toJson(adminsArray);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String createSheTuanAdmin(){
		ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
		String returnStr = "failure";
		System.out.println(ids);
		SheTuan findSheTuan = new SheTuanDaoImpl().findById(stid);
		if (findSheTuan != null) {
			adminDao.deleteAll(stid);		
			adminDao.save(new ShetuanAdmin(0, stid, findSheTuan.getUmid()));
			for (Integer id : ids) {
				adminDao.save(new ShetuanAdmin(0, stid, id));
			}
			List<ShetuanAdmin> sheTuanAdmins = adminDao.findByStid(stid);
			ShetuanAdmin[] adminsArray = sheTuanAdmins
					.toArray(new ShetuanAdmin[] {});
			returnStr = new Gson().toJson(adminsArray);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
}
