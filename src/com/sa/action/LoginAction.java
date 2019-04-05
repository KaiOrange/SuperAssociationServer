package com.sa.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.IU2stDao;
import com.sa.dao.IUserMainDao;
import com.sa.dao.impl.U2stDaoImpl;
import com.sa.dao.impl.UserMainDaoImpl;
import com.sa.entity.U2st;
import com.sa.entity.UserMain;
import com.sa.tools.GloblePath;
import com.sa.tools.PrintText2Client;

public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String userName = null;
	private String password = null;
	private UserMain userMain = null;;

	public UserMain getUserMain() {
		return userMain;
	}
	public void setUserMain(UserMain userMain) {
		this.userMain = userMain;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String login(){
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain userMain = userMainDAO.findUserMainByNameAndPwd(userName, password);
		IU2stDao u2stDao = new U2stDaoImpl();
		String returnStr = "null";
		if (userMain != null) {
			U2st findU2st = u2stDao.findByUserId(userMain.getUmid());
			if (findU2st != null) {
				userMain.setStid("" + findU2st.getStid());
			}
			returnStr = new Gson().toJson(userMain);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String register(){
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain findUserMain = userMainDAO.findUserMainByName(userName);
		String returnStr = null;
		if (findUserMain == null) {
			UserMain userMain = new UserMain();
			userMain.setName(userName);
			userMain.setPassword(password);
			userMainDAO.save(userMain);
			returnStr = new Gson().toJson(userMainDAO.findUserMainByName(userName));
		} else {
			returnStr = "null";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String updateTouXiang(){
		String returnStr = "failure";
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain myUserMain = null;
		if (userMain == null) {
		} else {
			myUserMain = userMainDAO.findUserMainByName(userMain.getName());
			//保存图片
			//获取服务器物理路径
			String rpath=ServletActionContext.getServletContext().getRealPath("/");
//			String rpath = GloblePath.getTouXiangImgPath();
			//处理文件上传
			if(userMain.getPic()!=null&&userMain.getPic().length()>0){
				//获取文件后缀
				String fname=userMain.getPicFileName();
				if(fname.lastIndexOf(".")!=-1){
					String ext=fname.substring(fname.lastIndexOf("."));
					//改名
					String nfname=new Date().getTime()+ext;
					//创建文件对象,制定上传文件存放的路径
					File destFile=new File(rpath + "/touxiang/" +nfname);
					String oldURL = myUserMain.getTouxiang(); 
					try {
						FileUtils.copyFile(userMain.getPic(), destFile);
						FileUtils.copyFile(destFile, new File(GloblePath.BACKUP_USER_TX + nfname));
						userMain = myUserMain;
						userMain.setTouxiang(nfname);
						boolean update = userMainDAO.update(userMain);
						if (update) {
							IU2stDao u2stDao = new U2stDaoImpl();
							U2st findU2st = u2stDao.findByUserId(userMain.getUmid());
							if (findU2st != null) {
								userMain.setStid("" + findU2st.getStid());
							}
							returnStr = new Gson().toJson(userMain);
						}
						//删除原来的头像
						if (oldURL != null && !"".equals(oldURL.trim())) {
							File delfile = new File(rpath + "/touxiang/"
									+ oldURL);
							if (delfile.exists()) {
								delfile.delete();//删除原来的文件
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		PrintText2Client.print(returnStr);
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public String updateUserMain(){
		String returnStr = null;
		if (userMain == null) {
			returnStr = "failure";			
		} else {
			//保存图片
			//获取服务器物理路径
			String rpath=ServletActionContext.getRequest().getRealPath("/");
//			String rpath = GloblePath.getTouXiangImgPath();
			//处理文件上传
			if(userMain.getPic()!=null&&userMain.getPic().length()>0){
				//获取文件后缀
				String fname=userMain.getPicFileName();
				if(fname.lastIndexOf(".")!=-1){
					String ext=fname.substring(fname.lastIndexOf("."));
					//改名
					String nfname=new Date().getTime()+ext;
					//创建文件对象,制定上传文件存放的路径
					File destFile=new File(rpath + "/touxiang/" +nfname);
					try {
						FileUtils.copyFile(userMain.getPic(), destFile);
						FileUtils.copyFile(destFile, new File(GloblePath.BACKUP_USER_TX + nfname));
						userMain.setTouxiang(nfname);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			//向数据库中保存数据
			IUserMainDao userMainDAO = new UserMainDaoImpl();
			boolean update = userMainDAO.update(userMain);
			if (update) {
				returnStr = "seccess";
			} else {
				returnStr = "failure";
			}
		}
		
		PrintText2Client.print(returnStr);
		return null;
	}
	public String repassword(){
		String returnStr = "failure";
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain olduser = userMainDAO.findUserMainByNameAndPwd(userMain.getName(), password);
		if (olduser == null) {
			PrintText2Client.print("passwor can't pass");
			return null;
		}
		boolean update = userMainDAO.update(userMain);
		if (update) {
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String updateUserMainButTXAndMM(){
		//向数据库中保存数据
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		UserMain myUserMain = userMainDAO.findUserMainByName(userMain.getName());
		userMain.setPassword(myUserMain.getPassword());
		userMain.setTouxiang(myUserMain.getTouxiang());
		boolean update = userMainDAO.update(userMain);
		String returnStr = "failure";
		if (update) {
			IU2stDao u2stDao = new U2stDaoImpl();
			U2st findU2st = u2stDao.findByUserId(userMain.getUmid());
			if (findU2st != null) {
				userMain.setStid("" + findU2st.getStid());
			}
			returnStr = new Gson().toJson(userMain);
		}
		PrintText2Client.print(returnStr);
		return null;
	} 
}