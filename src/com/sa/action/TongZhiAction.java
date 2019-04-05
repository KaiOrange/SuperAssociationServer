package com.sa.action;

import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.ITongzhiDao;
import com.sa.dao.IU2stDao;
import com.sa.dao.impl.SheTuanDaoImpl;
import com.sa.dao.impl.TongzhiDaoImpl;
import com.sa.dao.impl.U2stDaoImpl;
import com.sa.entity.SheTuan;
import com.sa.entity.Tongzhi;
import com.sa.entity.U2st;
import com.sa.tools.DateConvert;
import com.sa.tools.PrintText2Client;

public class TongZhiAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int umid;
	private Tongzhi tongzhi;
	private int stid;
	private String tztext;
	

	public String getTztext() {
		return tztext;
	}

	public void setTztext(String tztext) {
		this.tztext = tztext;
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	public Tongzhi getTongzhi() {
		return tongzhi;
	}

	public void setTongzhi(Tongzhi tongzhi) {
		this.tongzhi = tongzhi;
	}

	public int getUmid() {
		return umid;
	}

	public void setUmid(int umid) {
		this.umid = umid;
	}
	
	public String getTongZhisByUmid(){
		ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
		List<Tongzhi> tongzhis = tongzhiDao.findByUmid(umid, 1);
		String returnStr = "notFound";
		if (tongzhis != null && tongzhis.size() > 0) {
			Tongzhi[] array = tongzhis.toArray(new Tongzhi[]{});
			returnStr = new Gson().toJson(array);
			for (Tongzhi tongzhi : tongzhis) {
				tongzhi.setStatus(0);
				tongzhiDao.update(tongzhi);
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String sendMessage(){
		tongzhi.setStatus(1);
		ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
		String returnStr = "failure";
		if (tongzhiDao.save(tongzhi)) {
			returnStr = DateConvert.getDateStr();
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String sendMessage2AllUser(){
		SheTuan sheTuan = new SheTuanDaoImpl().findById(stid);
		IU2stDao u2stDao = new U2stDaoImpl();
		String returnStr = "failure";
		if (sheTuan != null) {
			List<U2st> findU2st = u2stDao.findByStid(stid, 1);
			ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
			for (U2st u2st : findU2st) {
				Tongzhi tongzhi = new Tongzhi(0, tztext, "来自社团[" + sheTuan.getSname() + "]的通知", u2st.getUmid(), "", 1, 0, "");
				tongzhiDao.save(tongzhi);
			}
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
}
