package com.sa.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.ISheTuanAdminDao;
import com.sa.db.DBManager;
import com.sa.entity.ShetuanAdmin;

public class ShetuanAdminDaoImpl implements ISheTuanAdminDao {

	public boolean delete(int staid) {
		String sql = "delete from ShetuanAdmin where" +
			" staid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + staid}));
	}
	public boolean deleteByUmid(int stid,int umid) {
		String sql = "delete from ShetuanAdmin where" +
		" stid = ? and umid = ?";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + stid,"" + umid}));
	}
	public boolean deleteAll(int stid,Connection con) {
		String sql = "delete from ShetuanAdmin where" +
		" stid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + stid},con));
	}
	public boolean deleteAll(int stid) {
		String sql = "delete from ShetuanAdmin where" +
		" stid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + stid}));
	}

	public List<ShetuanAdmin> findAll() {
		List<ShetuanAdmin> list = new ArrayList<ShetuanAdmin>();
		String sql = "select * from ShetuanAdmin";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				ShetuanAdmin admin = new ShetuanAdmin(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				list.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<ShetuanAdmin> findByStid(int stid) {
		List<ShetuanAdmin> list = new ArrayList<ShetuanAdmin>();
		String sql = "select * from ShetuanAdmin where stid = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + stid});
		try {
			while (rs.next()) {
				ShetuanAdmin admin = new ShetuanAdmin(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				list.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public ShetuanAdmin findById(int staid) {
		ShetuanAdmin admin = null;
		String sql = "select * from ShetuanAdmin " +
				"where staid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{staid+""});
		try {
			if (rs.next()) {
				admin = new ShetuanAdmin(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return admin;
	}
	public ShetuanAdmin findById(int stid,int umid) {
		ShetuanAdmin admin = null;
		String sql = "select * from ShetuanAdmin " +
		"where stid = ? and umid = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{stid+"","" + umid});
		try {
			if (rs.next()) {
				admin = new ShetuanAdmin(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return admin;
	}

	public boolean save(ShetuanAdmin admin) {
		String sql = "insert into ShetuanAdmin(stid,umid)"  
			+ " values(?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{""+admin.getStid(),""+admin.getUmid()}));
	}

	public boolean update(ShetuanAdmin admin) {
		String sql = "update ShetuanAdmin set stid = ?,umid = ? where staid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{""+admin.getStaid(),""+admin.getUmid(),""+admin.getStaid()}));
	}

}
