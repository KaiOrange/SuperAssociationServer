package com.sa.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.ISheTuanAdminDao;
import com.sa.dao.ITongzhiDao;
import com.sa.dao.IU2stDao;
import com.sa.dao.IUserMainDao;
import com.sa.dao.impl.SheTuanDaoImpl;
import com.sa.dao.impl.ShetuanAdminDaoImpl;
import com.sa.dao.impl.TongzhiDaoImpl;
import com.sa.dao.impl.U2stDaoImpl;
import com.sa.dao.impl.UserMainDaoImpl;
import com.sa.entity.ShetuanAdmin;
import com.sa.entity.Tongzhi;
import com.sa.entity.U2st;
import com.sa.entity.UserMain;
import com.sa.tools.DateConvert;
import com.sa.tools.PrintText2Client;

public class U2stAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private U2st u2st;
	private String checkedIds;
	private List<U2st> u2sts;
	

	public List<U2st> getU2sts() {
		return u2sts;
	}

	public void setU2sts(List<U2st> u2sts) {
		this.u2sts = u2sts;
	}

	public String getCheckedIds() {
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds) {
		this.checkedIds = checkedIds;
	}

	public U2st getU2st() {
		return u2st;
	}

	public void setU2st(U2st u2st) {
		this.u2st = u2st;
	}
	public String removeUsers(){
		IU2stDao u2stDao = new U2stDaoImpl();
		String returnStr = "failure";
		ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
		ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
		if (u2sts.size() > 0) {
			int stIdGet = u2sts.get(0).getStid();
			for (U2st u : u2sts) {
				ShetuanAdmin stadmin = adminDao.findById(stIdGet, u.getUmid());
				if (stadmin != null) {
					adminDao.delete(stadmin.getStaid());
				}
				boolean delete = u2stDao.delete(u.getUmid(), u.getStid(), 1);
				if (delete) {
					Tongzhi tongzhi = new Tongzhi(0, "你已被ID为[" + u.getStid() + "]的社团踢出了", "社团踢出通知", u.getUmid(), "", 1, 0, "");
					tongzhiDao.save(tongzhi);
				}
			}
			returnStr = "seccess";
			/*List<U2st> findU2sts = u2stDao.findByStid(stIdGet, 1);
			IUserMainDao userMainDao = new UserMainDaoImpl();
			List<UserMain> list = new ArrayList<UserMain>();
			for (U2st u2st : findU2sts) {
				UserMain user = userMainDao.findById(u2st.getUmid());
				user.setStid("" + stIdGet);
				list.add(user);
			}
			UserMain[] array = list.toArray(new UserMain[]{});
			returnStr = new Gson().toJson(array)*/;
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String joinSheTuan(){
		IU2stDao u2stDao = new U2stDaoImpl();
		U2st findU2st = u2stDao.findByStidAndUid(u2st.getStid(), u2st.getUmid(), 0);
		String returnStr = "failure";
		if (findU2st != null) {
			returnStr = "hava";
		} else {
			boolean b = u2stDao.save(u2st);
			if (b) {
				ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
				List<ShetuanAdmin> findAdmin = adminDao.findByStid(u2st.getStid());
				ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
				IUserMainDao userMainDao = new UserMainDaoImpl();
				UserMain findUserMain = userMainDao.findById(u2st.getUmid());
				String name = "";
				if (findUserMain != null) {
					name = findUserMain.getName();
				}
				for (ShetuanAdmin shetuanAdmin : findAdmin) {
					Tongzhi tongzhi = new Tongzhi(0, "加入社团申请", "账号为[" + name + "]的小伙伴想要加入你们社团,亲爱的管理员快去审核吧", shetuanAdmin.getUmid(), null, 1, 2,""+u2st.getUmid());
					tongzhiDao.save(tongzhi);
				}
				returnStr = "seccess";
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String refuseJoin(){
		String returnStr = "failure";
		Integer[] ids = new Gson().fromJson(checkedIds, Integer[].class);
		int stid = u2st.getStid();
		IU2stDao u2stDao = new U2stDaoImpl();
		IUserMainDao userMainDao = new UserMainDaoImpl();
		List<UserMain> list = new ArrayList<UserMain>();
		ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
		String sname = new SheTuanDaoImpl().findById(stid).getSname();
		for (Integer integer : ids) {
			boolean delete = u2stDao.delete(integer, stid, 0);
			if (delete) {
				UserMain findUser = userMainDao.findById(integer);
				list.add(findUser);
				Tongzhi tongzhi = new Tongzhi(0, "对不起,我们很抱歉地告诉你我们拒绝了你的加入社团申请", "来自社团[" + sname + "]的拒绝通知", integer, DateConvert.getDateStr(), 1,0,"");
				tongzhiDao.save(tongzhi);
			}
		}
		if (list.size() > 0) {
			returnStr = new Gson().toJson(list.toArray(new UserMain[]{}));
		}
		
		PrintText2Client.print(returnStr);
		return null;
	}
	public String confirmJoin(){
		String returnStr = "failure";
		Integer[] ids = new Gson().fromJson(checkedIds, Integer[].class);
		int stid = u2st.getStid();
		IU2stDao u2stDao = new U2stDaoImpl();
		IUserMainDao userMainDao = new UserMainDaoImpl();
		List<UserMain> list = new ArrayList<UserMain>();
		ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
		String sname = new SheTuanDaoImpl().findById(stid).getSname();
		for (Integer integer : ids) {
			U2st findU2st = u2stDao.findByStidAndUid(stid, integer, 0);
			findU2st.setStatus(1);
			boolean b = u2stDao.update(findU2st);
			if (b) {
				UserMain findUser = userMainDao.findById(integer);
				list.add(findUser);
				Tongzhi tongzhi = new Tongzhi(0, "恭喜您,成功加入社团[" + sname + "]从此我们永远是一家人了..", "来自社团[" + sname + "]的加入通知", integer, DateConvert.getDateStr(), 1,0,"");
				tongzhiDao.save(tongzhi);
			}
		}
		if (list.size() > 0) {
			returnStr = new Gson().toJson(list.toArray(new UserMain[]{}));
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String getWantJoinedUser(){
		String returnStr = "failure";
		IU2stDao u2stDao = new U2stDaoImpl();
		List<U2st> u2sts = u2stDao.findByStid(u2st.getStid(),0);
		List<UserMain> userMains = new ArrayList<UserMain>();
		IUserMainDao userMainDao = new UserMainDaoImpl();
		ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
		List<ShetuanAdmin> shetuanAdmins = adminDao.findByStid(u2st.getStid());
		boolean isAdmin = false;
		for (ShetuanAdmin shetuanAdmin : shetuanAdmins) {
			if (shetuanAdmin.getUmid() == u2st.getUmid()) {
				isAdmin = true;
			}
		}
		if (isAdmin) {			
			for (U2st u2st : u2sts) {
				UserMain userMain = userMainDao.findById(u2st.getUmid());
				userMains.add(userMain);
			}
			if (userMains.size() > 0) {
				UserMain[] array = userMains.toArray(new UserMain[]{});
				returnStr = new Gson().toJson(array);
			}
		} else {
			returnStr = "notAdmin";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
}
