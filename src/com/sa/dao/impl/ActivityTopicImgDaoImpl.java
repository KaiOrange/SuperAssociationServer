package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IActivityTopicImgDao;
import com.sa.db.DBManager;
import com.sa.entity.ActivityTopicImg;

public class ActivityTopicImgDaoImpl implements IActivityTopicImgDao {

	public boolean delete(int imgid) {
		String sql = "delete from ActivityTopicImg where" +
			" imgid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + imgid}));
	}

	public List<ActivityTopicImg> findAll() {
		List<ActivityTopicImg> list = new ArrayList<ActivityTopicImg>();
		String sql = "select * from ActivityTopicImg";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				ActivityTopicImg atImg = new ActivityTopicImg(rs.getInt(1), rs.getString(2), rs.getInt(3));
				list.add(atImg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public ActivityTopicImg findById(int imgid) {
		ActivityTopicImg atImg = null;
		String sql = "select * from ActivityTopicImg " +
				"where imgid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{imgid+""});
		try {
			if (rs.next()) {
				atImg = new ActivityTopicImg(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return atImg;
	}

	public boolean save(ActivityTopicImg atImg) {
		String sql = "insert into ActivityTopicImg(imgpath,atid)"  
			+ "values(?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{atImg.getImgpath(),atImg.getAtid()+""}));
	}

	public boolean update(ActivityTopicImg atImg) {
		String sql = "update ActivityTopicImg set imgpath = ?,atid = ? where imgid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{atImg.getImgpath(),atImg.getAtid()+"",atImg.getImgid()+""}));
	}

}
