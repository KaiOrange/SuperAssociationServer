package com.sa.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.ISheTuanAdminDao;
import com.sa.dao.ISheTuanDao;
import com.sa.dao.ITongzhiDao;
import com.sa.dao.IU2stDao;
import com.sa.dao.IUserMainDao;
import com.sa.dao.impl.SheTuanDaoImpl;
import com.sa.dao.impl.ShetuanAdminDaoImpl;
import com.sa.dao.impl.TongzhiDaoImpl;
import com.sa.dao.impl.U2stDaoImpl;
import com.sa.dao.impl.UserMainDaoImpl;
import com.sa.entity.SheTuan;
import com.sa.entity.ShetuanAdmin;
import com.sa.entity.Tongzhi;
import com.sa.entity.U2st;
import com.sa.entity.UserMain;
import com.sa.tools.GloblePath;
import com.sa.tools.PrintText2Client;

public class SheTuanAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private int umid;
	private SheTuan sheTuan;
	private int nowPage;
	private int pageSize;
	private String name;
	private int stid;
	

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getUmid() {
		return umid;
	}

	public SheTuan getSheTuan() {
		return sheTuan;
	}


	public void setSheTuan(SheTuan sheTuan) {
		this.sheTuan = sheTuan;
	}


	public void setUmid(int umid) {
		this.umid = umid;
	}


	public String jiesan(){	
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		IU2stDao u2stDao = new U2stDaoImpl();
		ITongzhiDao tongzhiDao = new TongzhiDaoImpl();
		SheTuan findSheTuan = sheTuanDao.findById(stid);
		if (findSheTuan == null || findSheTuan.getUmid() != umid) {
			returnStr = "notCreater";
		} else {
			List<U2st> list = u2stDao.findByStid(stid, 1);
			boolean b = sheTuanDao.deleteSheTuan(stid);
			if (b) {
				for (U2st u2st : list) {
					Tongzhi tongzhi = new Tongzhi(0, "您的社团[" + findSheTuan.getSname() + "]已经解散,希望这段短暂的社团生涯能给你留下美好的回忆.", "社团解散通知", u2st.getUmid(), "", 1, 0, "");
					tongzhiDao.save(tongzhi);
				}
				returnStr = "seccess";
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String zhuanrang(){	
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		IU2stDao u2stDao = new U2stDaoImpl();
		ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
		IUserMainDao userMainDao = new UserMainDaoImpl();
		SheTuan findSheTuan = sheTuanDao.findById(stid);
		if (findSheTuan == null || findSheTuan.getUmid() != umid) {
			returnStr = "notCreater";
		} else {
			UserMain findUserMain = userMainDao.findUserMainByName(name);
			if (findUserMain == null) {
				returnStr = "userNotFount";				
			} else if(findUserMain.getUmid() == umid){
				returnStr = "cannotbeyou";
			}else {
				U2st findU2st = u2stDao.findByStidAndUid(stid, findUserMain.getUmid(), 1);
				if (findU2st == null) {
					returnStr = "notInSheTuan";				
				} else {
					findSheTuan.setUmid(findUserMain.getUmid());
					sheTuanDao.update(findSheTuan);
					adminDao.deleteByUmid(stid, umid);
					adminDao.save(new ShetuanAdmin(0, stid, findUserMain.getUmid()));
					List<ShetuanAdmin> list = adminDao.findByStid(stid);
					ShetuanAdmin[] array = list.toArray(new ShetuanAdmin[]{});
					returnStr = new Gson().toJson(array);
				}
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String isJoinSheTuan(){	
		IU2stDao u2stDao = new U2stDaoImpl();
		U2st u2st = u2stDao.findByUserId(umid);
		String returnStr = "false";
		if (u2st != null) {
			returnStr = "true";
		} 
		PrintText2Client.print(returnStr);
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public String createSheTuan(){
		String returnStr = "failure";
		if (sheTuan != null) {
			ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
			String sname = sheTuan.getSname();
			SheTuan findSheTuan = sheTuanDao.findBySName(sname);
			IU2stDao u2stDao = new U2stDaoImpl();
			U2st findU2st = u2stDao.findByUserId(sheTuan.getUmid());
			if (findSheTuan != null) {
				returnStr = "created";
			} else if(findU2st != null){
				returnStr = "joined";
			} else {
				String rpath=ServletActionContext.getRequest().getRealPath("/");
	//			String rpath = GloblePath.getTouXiangImgPath();
				//处理文件上传
				if(sheTuan.getPic()!=null&&sheTuan.getPic().length()>0){
					//获取文件后缀
					String fname=sheTuan.getPicFileName();
					if(fname.lastIndexOf(".")!=-1){
						String ext=fname.substring(fname.lastIndexOf("."));
						//改名
						String nfname=new Date().getTime()+ext;
						//创建文件对象,制定上传文件存放的路径
						File destFile=new File(rpath + "/sheTuanTX/" +nfname);
						try {
							FileUtils.copyFile(sheTuan.getPic(), destFile);
							FileUtils.copyFile(destFile, new File(GloblePath.BACKUP_SHETUAN_TX + nfname));
							sheTuan.setTouxiang(nfname);
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}
				boolean b = sheTuanDao.save(sheTuan);
				SheTuan returnSheTuan = sheTuanDao.findByUid(sheTuan
						.getUmid());
				U2st u2st = new U2st(0, sheTuan.getUmid(), returnSheTuan.getStid(),1);
				u2stDao.save(u2st);
				returnSheTuan.setUmName(new UserMainDaoImpl().findById(sheTuan.getUmid()).getName());
				if (b) {
					returnStr = new Gson().toJson(returnSheTuan);
					ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
					ShetuanAdmin shetuanAdmin = new ShetuanAdmin(0, returnSheTuan.getStid(), returnSheTuan.getUmid());
					adminDao.save(shetuanAdmin);
				}
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String updateSheTuanTX(){
		String returnStr = "failure";
		if (sheTuan != null) {
			ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
			SheTuan findSheTuan = sheTuanDao.findById(sheTuan.getStid());
			String oldURL = findSheTuan.getTouxiang(); 
			String rpath=ServletActionContext.getServletContext().getRealPath("/");
			//String rpath = GloblePath.getTouXiangImgPath();
			//处理文件上传
			if(sheTuan.getPic()!=null&&sheTuan.getPic().length()>0){
				//获取文件后缀
				String fname=sheTuan.getPicFileName();
				if(fname.lastIndexOf(".")!=-1){
					String ext=fname.substring(fname.lastIndexOf("."));
					//改名
					String nfname=new Date().getTime()+ext;
					//创建文件对象,制定上传文件存放的路径
					File destFile=new File(rpath + "/sheTuanTX/" +nfname);
					try {
						FileUtils.copyFile(sheTuan.getPic(), destFile);
						FileUtils.copyFile(destFile, new File(GloblePath.BACKUP_SHETUAN_TX + nfname));
						findSheTuan.setTouxiang(nfname);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (oldURL != null && !"".equals(oldURL.trim())) {
						File delfile = new File(rpath + "/sheTuanTX/"
								+ oldURL);
						if (delfile.exists()) {
							delfile.delete();//删除原来的文件
						}
					}
					
				}
				boolean b = sheTuanDao.update(findSheTuan);
				SheTuan returnSheTuan = sheTuanDao.findById(sheTuan.getStid());
				returnSheTuan.setUmName(new UserMainDaoImpl().findById(returnSheTuan.getUmid()).getName());
				if (b) {
					returnStr = new Gson().toJson(returnSheTuan);
				}				
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String updateSnotice(){
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		SheTuan findSheTuan = sheTuanDao.findById(sheTuan.getStid());
		if (findSheTuan != null) {
			findSheTuan.setSnotice(sheTuan.getSnotice());
			boolean update = sheTuanDao.update(findSheTuan);
			String name = new UserMainDaoImpl().findById(findSheTuan.getUmid()).getName();
			findSheTuan.setUmName(name);
			if (update) {
				returnStr = new Gson().toJson(findSheTuan);
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String updateSdescribe(){
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		SheTuan findSheTuan = sheTuanDao.findById(sheTuan.getStid());
		if (findSheTuan != null) {
			findSheTuan.setSdescribe(sheTuan.getSdescribe());
			boolean update = sheTuanDao.update(findSheTuan);
			if (update) {
				String name = new UserMainDaoImpl().findById(findSheTuan.getUmid()).getName();
				findSheTuan.setUmName(name);
				returnStr = new Gson().toJson(findSheTuan);
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String getAllSheTuanUsers(){
		String returnStr = "failure";
		IU2stDao u2stDao = new U2stDaoImpl();
		List<U2st> findU2st = u2stDao.findByStid(sheTuan.getStid(), 1);
		if (findU2st != null && findU2st.size() > 0) {
			IUserMainDao userMainDao = new UserMainDaoImpl();
			List<UserMain> list = new ArrayList<UserMain>();
			ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
			for (U2st u2st : findU2st) {
				UserMain userMain = userMainDao.findById(u2st.getUmid());
				SheTuan findSheTuan = sheTuanDao.findById(u2st.getStid());
				if (findSheTuan != null) {
					userMain.setStid("" + findSheTuan.getStid());
				}
				list.add(userMain);
			}
			UserMain[] array = list.toArray(new UserMain[]{});
			returnStr = new Gson().toJson(array);
		} else {
			returnStr = "notFound";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String findSheTuanByUid(){
		String returnStr = "failure";
		IU2stDao u2stDao = new U2stDaoImpl();
		U2st u2st = u2stDao.findByUserId(umid);
		if (u2st != null) {
			ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
			SheTuan sheTuan = sheTuanDao.findById(u2st.getStid());
			if (sheTuan != null) {
				sheTuan.setUmName(new UserMainDaoImpl().findById(
						sheTuan.getUmid()).getName());
				returnStr = new Gson().toJson(sheTuan);
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String findSheTuanUsePageDate(){
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		LinkedList<SheTuan> sheTuans = sheTuanDao.getPageDate(nowPage, pageSize);
		if (sheTuans != null) {
			SheTuan[] sheTuanArray = sheTuans.toArray(new SheTuan[]{});
			returnStr = new Gson().toJson(sheTuanArray);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String findSheTuanUsePageDateAndStid(){
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		LinkedList<SheTuan> sheTuans = sheTuanDao.getPageDate(stid,nowPage, pageSize);
		if (sheTuans != null) {
			SheTuan[] sheTuanArray = sheTuans.toArray(new SheTuan[]{});
			returnStr = new Gson().toJson(sheTuanArray);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String tuichuSheTuan(){
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		int stid = sheTuan.getStid();
		SheTuan findsheTuan = sheTuanDao.findById(stid);
		if (findsheTuan.getUmid() == umid) {
			returnStr = "createdMan";
		} else {
			IU2stDao u2stDao = new U2stDaoImpl();
			boolean delete = u2stDao.delete(umid, stid, 1);
			if (delete) {
				returnStr = "seccess";
			}
			ISheTuanAdminDao adminDao = new ShetuanAdminDaoImpl();
			List<ShetuanAdmin> adminList = adminDao.findByStid(stid);
			for (ShetuanAdmin shetuanAdmin : adminList) {
				if (shetuanAdmin.getUmid() == umid) {
					adminDao.delete(shetuanAdmin.getStaid());
				}
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String findSheTuanBySid(){
		String returnStr = "failure";
		ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
		SheTuan sheTuan = sheTuanDao.findById(stid);
		if (sheTuan != null) {
			sheTuan.setUmName(new UserMainDaoImpl().findById(
					sheTuan.getUmid()).getName());
			returnStr = new Gson().toJson(sheTuan);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
}
