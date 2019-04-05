package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IXiakeliaoImgDao;
import com.sa.db.DBManager;
import com.sa.entity.XiakeliaoImg;

public class XiakeliaoImgDaoImpl implements IXiakeliaoImgDao {

	public boolean delete(int xklimgid) {
		String sql = "delete from XiakeliaoImg where" +
			" xklimgid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + xklimgid}));
	}

	public List<XiakeliaoImg> findAll() {
		List<XiakeliaoImg> list = new ArrayList<XiakeliaoImg>();
		String sql = "select * from XiakeliaoImg";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				XiakeliaoImg atImg = new XiakeliaoImg(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(atImg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public XiakeliaoImg findById(int xklimgid) {
		XiakeliaoImg atImg = null;
		String sql = "select * from XiakeliaoImg " +
				"where xklimgid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{xklimgid+""});
		try {
			if (rs.next()) {
				atImg = new XiakeliaoImg(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return atImg;
	}

	public boolean save(XiakeliaoImg xklImg) {
		String sql = "insert into XiakeliaoImg(imgpath,xklid)"  
			+ "values(?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{xklImg.getImgpath(),xklImg.getXklid()+""}));
	}

	public boolean update(XiakeliaoImg xklImg) {
		String sql = "update XiakeliaoImg set imgpath = ?,atid = ? where xklimgid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{xklImg.getImgpath(),xklImg.getXklid()+"","" + xklImg.getXklimgid()}));
	}

}
