package com.sa.action;

import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.IUserMainDao;
import com.sa.dao.IXiakeliaoDao;
import com.sa.dao.IXiakeliaoReplyDao;
import com.sa.dao.impl.UserMainDaoImpl;
import com.sa.dao.impl.XiakeliaoDaoImpl;
import com.sa.dao.impl.XiakeliaoReplyDaoImpl;
import com.sa.entity.UserMain;
import com.sa.entity.Xiakeliao;
import com.sa.entity.XiakeliaoReply;
import com.sa.tools.PrintText2Client;

public class XiaKeLiaoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private XiakeliaoReply reply;
	private Xiakeliao xiakeliao;
	private int nowPage;
	private int pageSize;
	private int xklid;
	private int xklrid;
	private int umid;
	

	public int getUmid() {
		return umid;
	}
	public void setUmid(int umid) {
		this.umid = umid;
	}
	public int getXklrid() {
		return xklrid;
	}
	public void setXklrid(int xklrid) {
		this.xklrid = xklrid;
	}
	public XiakeliaoReply getReply() {
		return reply;
	}
	public void setReply(XiakeliaoReply reply) {
		this.reply = reply;
	}
	public int getXklid() {
		return xklid;
	}
	public void setXklid(int xklid) {
		this.xklid = xklid;
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
	public Xiakeliao getXiakeliao() {
		return xiakeliao;
	}
	public void setXiakeliao(Xiakeliao xiakeliao) {
		this.xiakeliao = xiakeliao;
	}
	public String createXiaKeLiao(){
		String returnStr = "failure";
		IXiakeliaoDao xiakeliaoDao = new XiakeliaoDaoImpl();
		boolean save = xiakeliaoDao.save(xiakeliao);
		if (save) {
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String createXiaKeLiaoReply(){
		String returnStr = "failure";
		IXiakeliaoReplyDao xiakeliaoReplyDao = new XiakeliaoReplyDaoImpl();
		boolean save = xiakeliaoReplyDao.save(reply);
		if (save) {
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String deleteXiaKeLiaoReply(){
		String returnStr = "failure";
		IXiakeliaoReplyDao xiakeliaoReplyDao = new XiakeliaoReplyDaoImpl();
		boolean delete = xiakeliaoReplyDao.delete(xklrid);
		if (delete) {
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	
	public String findXiaKeLiaoUserPageData(){
		String returnStr = "failure";
		IXiakeliaoDao xiakeliaoDao = new XiakeliaoDaoImpl();
		List<Xiakeliao> xiakeliaos = xiakeliaoDao.getPageDate(nowPage, pageSize);
		IUserMainDao userMainDao = new UserMainDaoImpl();
		IXiakeliaoReplyDao replyDao = new XiakeliaoReplyDaoImpl();
		if (xiakeliaos != null) {
			for (Xiakeliao xiakeliao : xiakeliaos) {
				UserMain userMain = userMainDao.findById(xiakeliao.getUmid());
				xiakeliao.setUserMain(userMain);
				int count = replyDao.findReplyCount(xiakeliao.getXklid());
				xiakeliao.setReplyCount(count);
			}
			Xiakeliao[] array = xiakeliaos.toArray(new Xiakeliao[]{});
			returnStr = new Gson().toJson(array);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String findXiaKeLiaoUserPageDataByXklidAndUmid(){
		String returnStr = "failure";
		IXiakeliaoDao xiakeliaoDao = new XiakeliaoDaoImpl();
		List<Xiakeliao> xiakeliaos = xiakeliaoDao.getPageDateByUmid(umid,xklid,nowPage, pageSize);
		IUserMainDao userMainDao = new UserMainDaoImpl();
		IXiakeliaoReplyDao replyDao = new XiakeliaoReplyDaoImpl();
		if (xiakeliaos != null) {
			for (Xiakeliao xiakeliao : xiakeliaos) {
				UserMain userMain = userMainDao.findById(xiakeliao.getUmid());
				xiakeliao.setUserMain(userMain);
				int count = replyDao.findReplyCount(xiakeliao.getXklid());
				xiakeliao.setReplyCount(count);
			}
			Xiakeliao[] array = xiakeliaos.toArray(new Xiakeliao[]{});
			returnStr = new Gson().toJson(array);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String findXiaKeLiaoUserPageDataAndUmid(){
		String returnStr = "failure";
		IXiakeliaoDao xiakeliaoDao = new XiakeliaoDaoImpl();
		List<Xiakeliao> xiakeliaos = xiakeliaoDao.getPageDateByUmid(umid,nowPage, pageSize);
		IUserMainDao userMainDao = new UserMainDaoImpl();
		IXiakeliaoReplyDao replyDao = new XiakeliaoReplyDaoImpl();
		if (xiakeliaos != null) {
			for (Xiakeliao xiakeliao : xiakeliaos) {
				UserMain userMain = userMainDao.findById(xiakeliao.getUmid());
				xiakeliao.setUserMain(userMain);
				int count = replyDao.findReplyCount(xiakeliao.getXklid());
				xiakeliao.setReplyCount(count);
			}
			Xiakeliao[] array = xiakeliaos.toArray(new Xiakeliao[]{});
			returnStr = new Gson().toJson(array);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String findXiaKeLiaoUserPageDataByXklid(){
		String returnStr = "failure";
		IXiakeliaoDao xiakeliaoDao = new XiakeliaoDaoImpl();
		List<Xiakeliao> xiakeliaos = xiakeliaoDao.getPageDate(xklid,nowPage, pageSize);
		IUserMainDao userMainDao = new UserMainDaoImpl();
		IXiakeliaoReplyDao replyDao = new XiakeliaoReplyDaoImpl();
		if (xiakeliaos != null) {
			for (Xiakeliao xiakeliao : xiakeliaos) {
				UserMain userMain = userMainDao.findById(xiakeliao.getUmid());
				xiakeliao.setUserMain(userMain);
				int count = replyDao.findReplyCount(xiakeliao.getXklid());
				xiakeliao.setReplyCount(count);
			}
			Xiakeliao[] array = xiakeliaos.toArray(new Xiakeliao[]{});
			returnStr = new Gson().toJson(array);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String findXiaKeLiaoReply(){
		String returnStr = "failure";
		IXiakeliaoReplyDao replyDao = new XiakeliaoReplyDaoImpl();
		List<XiakeliaoReply> xiakeliaoReplies = replyDao.findAllByXklid(xklid);
		IUserMainDao userMainDao = new UserMainDaoImpl();
		if (xiakeliaoReplies != null) {
			for (XiakeliaoReply xiakeliaoReply : xiakeliaoReplies) {
				UserMain userMainMe = userMainDao.findById(xiakeliaoReply.getUmidme());
				xiakeliaoReply.setUserMainMe(userMainMe);
				if (xiakeliaoReply.getUmidyou() != 0) {
					UserMain userMainYou = userMainDao.findById(xiakeliaoReply
							.getUmidyou());
					xiakeliaoReply.setUserMainYou(userMainYou);
				}
			}
			XiakeliaoReply[] array = xiakeliaoReplies.toArray(new XiakeliaoReply[]{});
			returnStr = new Gson().toJson(array);
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String deleteXiaKeLiao(){
		String returnStr = "failure";
		IXiakeliaoDao xiakeliaoDao = new XiakeliaoDaoImpl();
		boolean delete = xiakeliaoDao.deleteWithTransaction(xklid);
		if (delete) {
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
}
