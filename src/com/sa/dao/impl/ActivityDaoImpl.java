package com.sa.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IActivityDao;
import com.sa.db.DBManager;
import com.sa.entity.Activity;

public class ActivityDaoImpl implements IActivityDao {

	public boolean delete(int aId) {
		String sql = "delete from Activity where" +
			" aid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + aId}));
	}
	public boolean deleteAll(int stid,Connection con) {
		String sql = "delete from Activity where" +
		" stid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + stid},con));
	}

	public List<Activity> findAll() {
		List<Activity> list = new ArrayList<Activity>();
		String sql = "select aid,stid,adescribe,atitle,sdate,edate from Activity";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				Activity activity = new Activity(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				list.add(activity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public Activity findById(int aId) {
		Activity activity = null;
		String sql = "select aid,stid,adescribe,atitle,sdate,edate from Activity " +
				"where aid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + aId});
		try {
			if (rs.next()) {
				activity = new Activity(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return activity;
	}
	public List<Activity> findByStid(int stid) {
		String sql = "select aid,stid,adescribe,atitle,sdate,edate from Activity " +
		"where stid = ? order by sdate desc";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + stid});
		List<Activity> list = new ArrayList<Activity>();
		try {
			while (rs.next()) {
				Activity activity = new Activity(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				list.add(activity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public boolean save(Activity activity) {
		String sql = "insert into Activity(stid,adescribe,atitle,sdate,edate)"  
			+ "values(?,?,?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + activity.getStid(),activity.getAdescribe(),
				activity.getAtitle(),activity.getSdate(),activity.getEdate()}));
	}

	public boolean update(Activity activity) {
		String sql = "update Activity set stid = ? , adescribe = ?,sdate = ?,edate = ? where aid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{"" + activity.getStid(),activity.getAdescribe(),
				activity.getSdate(),activity.getEdate(),
				"" + activity.getAid()}));
	}

}
