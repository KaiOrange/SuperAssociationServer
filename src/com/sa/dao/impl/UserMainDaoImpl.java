package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IUserMainDao;
import com.sa.db.DBManager;
import com.sa.entity.UserMain;
import com.sa.tools.DateConvert;

public class UserMainDaoImpl implements IUserMainDao {

	public boolean delete(int umId) {
		String sql = "delete from UserMain where" +
			" umid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + umId}));
	}

	public List<UserMain> findAll() {
		List<UserMain> list = new ArrayList<UserMain>();
		String sql = "select * from UserMain";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				UserMain userMain = new UserMain(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
				list.add(userMain);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public UserMain findById(int umid) {
		UserMain userMain = null;
		String sql = "select * from UserMain " +
				"where umid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{umid+""});
		try {
			if (rs.next()) {
				userMain = new UserMain(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return userMain;
	}

	public boolean save(UserMain userMain) {
		String sql = "insert into UserMain(name,password,nichen,Touxiang,sex,age,brithday,telphone,myclass)"  
			+ "values(?,?,?,?,?,?,?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{userMain.getName(),userMain.getPassword(),
				userMain.getNichen(),userMain.getTouxiang(),DateConvert.convertToBit(userMain.isSex()),""+userMain.getAge(),
				userMain.getBrithday(),
				userMain.getTelphone(),userMain.getMyclass()}));
	}

	public boolean update(UserMain userMain) {
		String sql = "update UserMain set name = ?,password = ?,nichen = ?,Touxiang = ?,sex = ?,age = ?,brithday = ?,telphone = ?,myclass = ? where umid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{userMain.getName(),userMain.getPassword(),
				userMain.getNichen(),userMain.getTouxiang(),DateConvert.convertToBit(userMain.isSex()),""+userMain.getAge(),
				userMain.getBrithday(),
				userMain.getTelphone(),userMain.getMyclass(),userMain.getUmid()+""}));
	}

	public UserMain findUserMainByNameAndPwd(String userName, String pwd) {
		UserMain userMain = null;
		String sql = "select * from UserMain " +
				"where name = ? and password = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{userName,pwd});
		try {
			if (rs.next()) {
				userMain = new UserMain(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return userMain;
	}
	public UserMain findUserMainByName(String userName) {
		UserMain userMain = null;
		String sql = "select * from UserMain " +
		"where name = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{userName});
		try {
			if (rs.next()) {
				userMain = new UserMain(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return userMain;
	}

}
