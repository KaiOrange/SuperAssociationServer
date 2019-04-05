package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IActivityTopicDao;
import com.sa.db.DBManager;
import com.sa.entity.ActivityTopic;
import com.sa.tools.DateConvert;

public class ActivityTopicDaoImpl implements IActivityTopicDao {

	public boolean delete(int atId) {
		String sql = "delete from ActivityTopic where" +
			" atid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + atId}));
	}

	public List<ActivityTopic> findAll() {
		List<ActivityTopic> list = new ArrayList<ActivityTopic>();
		String sql = "select * from ActivityTopic";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				ActivityTopic activityTopic = new ActivityTopic(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				list.add(activityTopic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<ActivityTopic> findAllByAid(int aid) {
		List<ActivityTopic> list = new ArrayList<ActivityTopic>();
		String sql = "select * from ActivityTopic where aid = ? order by atdate desc";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + aid});
		try {
			while (rs.next()) {
				ActivityTopic activityTopic = new ActivityTopic(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				list.add(activityTopic);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public ActivityTopic findById(int aId) {
		ActivityTopic activityTopic = null;
		String sql = "select * from ActivityTopic " +
				"where atid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + aId});
		try {
			if (rs.next()) {
				activityTopic = new ActivityTopic(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return activityTopic;
	}

	public boolean save(ActivityTopic activityTopic) {
		String sql = "insert into ActivityTopic(aid,attext,umid,atdate)"  
			+ "values(?,?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(
				sql,new String[]{
				""+activityTopic.getAid(),
				activityTopic.getAttext(),
				""+activityTopic.getUmid(),				
				DateConvert.getDateStr()}));
	}



}
