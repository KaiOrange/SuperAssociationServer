package com.sa.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IU2stDao;
import com.sa.db.DBManager;
import com.sa.entity.U2st;

public class U2stDaoImpl implements IU2stDao {

	public boolean delete(int u2stid) {
		String sql = "delete from U2st where" +
			" U2stid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + u2stid}));
	}
	public boolean deleteAll(int stid,Connection con) {
		String sql = "delete from U2st where" +
		" stid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + stid},con));
	}
	public boolean delete(int umid,int stid,int status) {
		String sql = "delete from U2st where" +
		" umid = ? and stid = ? and status = ?";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + umid,"" + stid,"" + status}));
	}

	public List<U2st> findAll() {
		List<U2st> list = new ArrayList<U2st>();
		String sql = "select * from U2st";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				U2st u2st = new U2st(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(3));
				list.add(u2st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<U2st> findByStid(int stid,int status) {
		List<U2st> list = new ArrayList<U2st>();
		String sql = "select * from U2st where stid = ? and status = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + stid,"" + status});
		try {
			while (rs.next()) {
				U2st u2st = new U2st(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(3));
				list.add(u2st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public U2st findById(int u2stid) {
		U2st u2st = null;
		String sql = "select * from U2st " +
				"where U2stid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{u2stid+""});
		try {
			if (rs.next()) {
				u2st = new U2st(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return u2st;
	}
	public U2st findByStidAndUid(int stid,int umid,int status) {
		U2st u2st = null;
		String sql = "select * from U2st " +
		"where stid = ? and umid = ? and status = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{stid+"",""+umid,""+status});
		try {
			if (rs.next()) {
				u2st = new U2st(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return u2st;
	}

	public boolean save(U2st u2st) {
		String sql = "insert into U2st(umid,stid,status)"  
			+ "values(?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{""+u2st.getUmid(),"" + u2st.getStid(),"" + u2st.getStatus()}));
	}

	public boolean update(U2st u2st) {
		String sql = "update U2st set umid = ?,stid = ?,status = ? where U2stid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{
				""+u2st.getUmid(),"" + u2st.getStid(),"" + u2st.getStatus(),""+u2st.getU2stid()}));
	}

	public U2st findByUserId(int umid) {
		U2st u2st = null;
		String sql = "select * from U2st " +
				"where umid = ? and status = 1";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{umid+""});
		try {
			if (rs.next()) {
				u2st = new U2st(rs.getInt(1), rs.getInt(2), rs.getInt(3),rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return u2st;
	}
}
