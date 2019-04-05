package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.sa.dao.ISheTuanJiaoDao;
import com.sa.db.DBManager;
import com.sa.entity.SheTuanJiao;

public class SheTuanJiaoDaoImpl implements ISheTuanJiaoDao {

	public boolean save(SheTuanJiao sheTuanJiao) {
		String sql = "insert into SheTuanJiao(stjtext,stjsign,name)"  
			+ "values(?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{sheTuanJiao.getStjtext(),sheTuanJiao.getStjsign(),sheTuanJiao.getName()}));
	}
	public SheTuanJiao findByName(String name) {
		List<SheTuanJiao> list = new ArrayList<SheTuanJiao>();
		String sql = "select * from SheTuanJiao " +
				"where name = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{name});
		try {
			while (rs.next()) {
				SheTuanJiao sheTuanJiao = new SheTuanJiao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				list.add(sheTuanJiao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		Random random = new Random();
		return list.get(random.nextInt(list.size()));
	}
	public SheTuanJiao findRandom(String name) {
		int count = findAllCount();
		Random random = new Random();
		int userCount = findCount(name);
		int publicCount = findCount("-1");
		if (count == 0) {
			return new SheTuanJiao(0, "没有用户发送过社团角语录", "社团开发者", "-1");
		} else {
			SheTuanJiao sheTuanJiao = null;
			if(userCount > 0 && publicCount > 0) {
				int r = random.nextInt(10);
				if (r < 3) {
					sheTuanJiao = findByName(name);
				} else {
					while (sheTuanJiao == null) {
						int id = random.nextInt(count);
						sheTuanJiao = findRandomById(id + 1);
					}
				}
			} else if(publicCount > 0){
				while (sheTuanJiao == null) {
					int id = random.nextInt(count);
					sheTuanJiao = findRandomById(id + 1);
				}				
			} else if (userCount > 0){
				sheTuanJiao = findRandom(name);
			} else {
				sheTuanJiao = new SheTuanJiao(0, "没有用户发送过社团角语录", "社团开发者", "-1");
			}
			return sheTuanJiao;
		}
	}
	private SheTuanJiao findRandomById(int stjid) {
		String sql = "select * from SheTuanJiao " +
		"where name = '-1' and stjid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + stjid});
		SheTuanJiao sheTuanJiao = null;
		try {
			while (rs.next()) {
				sheTuanJiao = new SheTuanJiao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return sheTuanJiao;
	}
	public int findCount(String name) {
		String sql = "select count(*) from SheTuanJiao where name = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{name});
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return count;
	}
	public int findAllCount() {
		String sql = "select count(*) from SheTuanJiao";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{});
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return count;
	}
	
}
