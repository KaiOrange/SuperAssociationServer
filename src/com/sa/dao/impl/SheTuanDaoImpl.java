package com.sa.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.sa.dao.ISheTuanDao;
import com.sa.dao.IUserMainDao;
import com.sa.db.DBManager;
import com.sa.entity.SheTuan;

public class SheTuanDaoImpl implements ISheTuanDao {

	public boolean delete(int stid,Connection con) {
		String sql = "delete from SheTuan where" +
			" stid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + stid},con));
	}
	public boolean deleteSheTuan(int stid) {
		DBManager dbManager = new DBManager();
		Connection connection = dbManager.getConnection();
		boolean b = false;
		try {
			connection.setAutoCommit(false);
			new ActivityDaoImpl().deleteAll(stid, connection);
			new U2stDaoImpl().deleteAll(stid, connection);
			new ShetuanAdminDaoImpl().deleteAll(stid, connection);
			this.delete(stid, connection);
			connection.commit();
			connection.setAutoCommit(true);
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbManager.close();
		}
		return b;
	}

	public List<SheTuan> findAll() {
		List<SheTuan> list = new ArrayList<SheTuan>();
		String sql = "select * from SheTuan";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				SheTuan sheTuan = new SheTuan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
				list.add(sheTuan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public SheTuan findById(int stid) {
		SheTuan sheTuan = null;
		String sql = "select * from SheTuan " +
				"where stid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{stid+""});
		try {
			if (rs.next()) {
				sheTuan = new SheTuan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return sheTuan;
	}

	public boolean save(SheTuan sheTuan) {
		String sql = "insert into SheTuan(sname,snotice,sdescribe,cjdate,touxiang,umid)"  
			+ "values(?,?,?,?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{sheTuan.getSname(),sheTuan.getSnotice(),sheTuan.getSdescribe(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),sheTuan.getTouxiang(),""+sheTuan.getUmid()}));
	}

	public boolean update(SheTuan sheTuan) {
		String sql = "update SheTuan set sname = ?,snotice = ?,sdescribe = ?,touxiang = ?,umid = ? where stid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{
				sheTuan.getSname(),
				sheTuan.getSnotice(),
				sheTuan.getSdescribe(),
				sheTuan.getTouxiang(),
				""+sheTuan.getUmid(),
				""+sheTuan.getStid()}));
	}


	public SheTuan findByUid(int umId) {
		SheTuan sheTuan = null;
		String sql = "select * from SheTuan " +
				"where umid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{umId+""});
		try {
			if (rs.next()) {
				sheTuan = new SheTuan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return sheTuan;
	}
	public SheTuan findBySName(String sname) {
		SheTuan sheTuan = null;
		String sql = "select * from SheTuan " +
		"where sname = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{ sname });
		try {
			if (rs.next()) {
				sheTuan = new SheTuan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return sheTuan;
	}

	
	public LinkedList<SheTuan> getPageDate(int nowPage, int pageSize) {
		LinkedList<SheTuan> list = new LinkedList<SheTuan>();
		String sql = "select * from SheTuan order by stid desc limit "+nowPage+","+pageSize+" ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql, null);
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		try {
			while (rs.next()) {
				SheTuan sheTuan = new SheTuan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
				sheTuan.setUmName(userMainDAO.findById(
						sheTuan.getUmid()).getName());
				list.add(sheTuan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public LinkedList<SheTuan> getPageDate(int stid,int nowPage, int pageSize) {
		LinkedList<SheTuan> list = new LinkedList<SheTuan>();
		String sql = "select * from SheTuan where stid < " + stid + " order by stid desc limit "+nowPage+","+pageSize+" ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql, null);
		IUserMainDao userMainDAO = new UserMainDaoImpl();
		try {
			while (rs.next()) {
				SheTuan sheTuan = new SheTuan(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
				sheTuan.setUmName(userMainDAO.findById(
						sheTuan.getUmid()).getName());
				list.add(sheTuan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

}
