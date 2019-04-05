package com.sa.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.ITongzhiDao;
import com.sa.dao.IU2stDao;
import com.sa.dao.IUm2UmDao;
import com.sa.dao.IUserMainDao;
import com.sa.dao.impl.TongzhiDaoImpl;
import com.sa.dao.impl.U2stDaoImpl;
import com.sa.dao.impl.Um2UmDaoImpl;
import com.sa.dao.impl.UserMainDaoImpl;
import com.sa.entity.Tongzhi;
import com.sa.entity.U2st;
import com.sa.entity.Um2Um;
import com.sa.entity.UserMain;
import com.sa.tools.PrintText2Client;

public class FriendsAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String userName;
	private int umid;
	private int umid2;

	public int getUmid2() {
		return umid2;
	}

	public void setUmid2(int umid2) {
		this.umid2 = umid2;
	}

	public int getUmid() {
		return umid;
	}

	public void setUmid(int umid) {
		this.umid = umid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String callFriends(){
		String returnStr = "failure";
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain findUser = userMainDAO.findUserMainByName(userName);
		if (findUser == null) {
			returnStr = "notFound";
		} else {
			ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
			String name = userMainDAO.findById(umid).getName();
			Tongzhi tongzhi = new Tongzhi(0, "账号[" + name + "]请求添加您为好友", "添加好友申请", findUser.getUmid(), null, 1, 1,name);
			if (tongzhiDao.save(tongzhi)) {
				returnStr = "seccess";
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String refuseAddFriend(){
		String returnStr = "failure";
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain findUser = userMainDAO.findUserMainByName(userName);
		UserMain findUserMe = userMainDAO.findById(umid);
		if (findUser == null) {
			returnStr = "notFound";
		} else {
			ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
			Tongzhi tongzhi = new Tongzhi(0, "您申请添加账号[" + findUserMe.getName() + "]为好友的请求被拒绝", "好友申请被拒绝", findUser.getUmid(), null, 1, 0,"");
			if (tongzhiDao.save(tongzhi)) {
				returnStr = "seccess";
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String deleteFriend(){
		String returnStr = "failure";
		IUm2UmDao um2UmDao = new Um2UmDaoImpl();
		boolean delete = um2UmDao.delete2UserMain(umid, umid2);
		if (delete) {
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String confimAddFriend(){//同意申请
		String returnStr = "failure";
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain findUser = userMainDAO.findUserMainByName(userName);
		UserMain findUserMe = userMainDAO.findById(umid);
		IUm2UmDao um2UmDao = new Um2UmDaoImpl();
		if (findUser == null) {
			returnStr = "notFound";
		} else {
			Um2Um findUm2Um = um2UmDao.findById(umid, findUser.getUmid());
			if (findUm2Um != null) {
				returnStr = "confimed";
			} else {
				Um2Um um2Um = new Um2Um(0, findUser.getUmid(), findUserMe.getUmid());
				boolean b = um2UmDao.save2UserMain(um2Um);
				if (b) {
					ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
					Tongzhi tongzhi = new Tongzhi(0, "您已经和账号[" + findUserMe.getName() + "]成为好友了", "好友申请成功", findUser.getUmid(), null, 1, 0,"");
					if (tongzhiDao.save(tongzhi)) {
						returnStr = "seccess";
						/*List<UserMain> userMains = new ArrayList<UserMain>();
						for (Um2Um um2Um : findUm2Um) {
							UserMain userMain = userMainDAO.findById(um2Um.getUmid2());
							if (userMain != null) {
								U2st findU2st = u2stDao.findByUserId(userMain.getUmid());
								if (findU2st != null) {
									userMain.setStid("" + findU2st.getStid());
								}
								userMains.add(userMain);
							}
						}
						returnStr = new Gson().toJson(userMains.toArray(new UserMain[]{}));*/
					}
				}
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String getMyAllFriends(){
		String returnStr = "failure";
		IUm2UmDao um2UmDao = new Um2UmDaoImpl();
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		List<Um2Um> findUm2Um = um2UmDao.findByUserId(umid);
		IU2stDao u2stDao = new U2stDaoImpl();
		if (findUm2Um.size() == 0) {
			returnStr = "notFriend";
		} else {
			List<UserMain> userMains = new ArrayList<UserMain>();
			for (Um2Um um2Um : findUm2Um) {
				UserMain userMain = userMainDAO.findById(um2Um.getUmid2());
				if (userMain != null) {
					U2st findU2st = u2stDao.findByUserId(userMain.getUmid());
					if (findU2st != null) {
						userMain.setStid("" + findU2st.getStid());
					}
					userMains.add(userMain);
				}
			}
			returnStr = new Gson().toJson(userMains.toArray(new UserMain[]{}));
		}
		PrintText2Client.print(returnStr);
		return null;
	}
}
