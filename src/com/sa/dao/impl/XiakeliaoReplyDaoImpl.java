package com.sa.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IXiakeliaoReplyDao;
import com.sa.db.DBManager;
import com.sa.entity.XiakeliaoReply;
import com.sa.tools.DateConvert;

public class XiakeliaoReplyDaoImpl implements IXiakeliaoReplyDao {

	public boolean delete(int xklrid) {
		String sql = "delete from XiakeliaoReply where" +
			" xklrid = " + xklrid;
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{}));
	}
	public boolean deleteByxklid(int xklid,Connection connection) {
		String sql = "delete from XiakeliaoReply where" +
		" xklid = " + xklid;
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{},connection));
	}

	public List<XiakeliaoReply> findAll() {
		List<XiakeliaoReply> list = new ArrayList<XiakeliaoReply>();
		String sql = "select * from XiakeliaoReply";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				XiakeliaoReply xklReply = new XiakeliaoReply(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				list.add(xklReply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<XiakeliaoReply> findAllByXklid(int xklid) {
		List<XiakeliaoReply> list = new ArrayList<XiakeliaoReply>();
		String sql = "select * from XiakeliaoReply where xklid = ? order by xklrid desc";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String []{"" + xklid});
		try {
			while (rs.next()) {
				XiakeliaoReply xklReply = new XiakeliaoReply(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				list.add(xklReply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public XiakeliaoReply findById(int xklrid) {
		XiakeliaoReply xklReply = null;
		String sql = "select * from XiakeliaoReply " +
				"where xklrid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{xklrid+""});
		try {
			if (rs.next()) {
				xklReply = new XiakeliaoReply(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return xklReply;
	}
	public int findReplyCount(int xklid) {
		String sql = "select count(*) from XiakeliaoReply " +
		"where xklid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{xklid+""});
		int count = 0;
		try {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return count;
	}

	public boolean save(XiakeliaoReply xklReply) {
		String sql = "insert into XiakeliaoReply(xklid,umidme,umidyou,xklrtext,cjdate)"  
			+ "values(?,?,?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{""+xklReply.getXklid(),""+xklReply.getUmidme(),""+xklReply.getUmidyou(),xklReply.getXklrtext(),DateConvert.getDateStr()}));
	}

	public boolean update(XiakeliaoReply xklReply) {
		String sql = "update XiakeliaoReply set xklid = ?,umidme = ?,umidyou = ?,xklrtext = ?,cjdate = ? where xklrid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{
				""+xklReply.getXklid(),""+xklReply.getUmidme(),
				""+xklReply.getUmidyou(),xklReply.getXklrtext(),
				xklReply.getCjdate(),
				""+xklReply.getXklrid()}));
	}

}
