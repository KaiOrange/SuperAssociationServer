package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IActivityTopicReplyDao;
import com.sa.db.DBManager;
import com.sa.entity.ActivityTopicReply;
import com.sa.tools.DateConvert;

public class ActivityTopicReplyDaoImpl implements IActivityTopicReplyDao {

	public boolean delete(int atrid) {
		String sql = "delete from ActivityTopicReply where" +
			" atrid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + atrid}));
	}
	
	public boolean deleteAllByAtid(int atid) {
		String sql = "delete from ActivityTopicReply where" +
		" atid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + atid}));
	}

	public List<ActivityTopicReply> findAll() {
		List<ActivityTopicReply> list = new ArrayList<ActivityTopicReply>();
		String sql = "select * from ActivityTopicReply";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				ActivityTopicReply activityTopicRelpy = new ActivityTopicReply(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				list.add(activityTopicRelpy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<ActivityTopicReply> findAllByAid(int atid) {
		List<ActivityTopicReply> list = new ArrayList<ActivityTopicReply>();
		String sql = "select * from ActivityTopicReply where atid = ? order by atdate desc";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + atid});
		try {
			while (rs.next()) {
				ActivityTopicReply activityTopicRelpy = new ActivityTopicReply(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
				list.add(activityTopicRelpy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public ActivityTopicReply findById(int atrid) {
		ActivityTopicReply activityTopicRelpy = null;
		String sql = "select * from ActivityTopicReply " +
				"where atrid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + atrid});
		try {
			if (rs.next()) {
				activityTopicRelpy = new ActivityTopicReply(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return activityTopicRelpy;
	}

	public boolean save(ActivityTopicReply activityTopicRelpy) {
		String sql = "insert into ActivityTopicReply(atid,atrtext,umidme,nameYou,atdate)"  
			+ "values(?,?,?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(
				sql,new String[]{
				""+activityTopicRelpy.getAtid(),
				activityTopicRelpy.getAtrtext(),
				""+activityTopicRelpy.getUmidme(),		
				DateConvert.getDateStr()}));
	}

}
