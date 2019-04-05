package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IUm2UmDao;
import com.sa.db.DBManager;
import com.sa.entity.Um2Um;

public class Um2UmDaoImpl implements IUm2UmDao {

	public boolean delete(int um2UmId) {
		String sql = "delete from UM2UM where" +
			" um2UmId = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + um2UmId}));
	}
	public boolean delete(int umid1,int umid2) {
		String sql = "delete from UM2UM where" +
		" umid1 = ? and umid2 = ?";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + umid1,""+umid2}));
	}
	public boolean delete2UserMain(int umid1,int umid2) {
		boolean b1 = delete(umid1, umid2);
		boolean b2 = delete(umid2, umid1);
		return b1 || b2;		
	}
	

	
	

	public Um2Um findById(int um2UmId) {
		Um2Um u2st = null;
		String sql = "select * from UM2UM " +
				"where Um2umid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{um2UmId+""});
		try {
			if (rs.next()) {
				u2st = new Um2Um(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return u2st;
	}
	
	public Um2Um findById(int umid1,int umid2) {
		Um2Um u2st = null;
		String sql = "select * from UM2UM " +
		"where umid1 = ? and umid2 = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{"" + umid1,"" + umid2});
		try {
			if (rs.next()) {
				u2st = new Um2Um(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return u2st;
	}
	

	public boolean save(Um2Um um2Um) {
		String sql = "insert into UM2UM(umid1,umid2)"  
			+ "values(?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{""+um2Um.getUmid1(),"" + um2Um.getUmid2()}));
	}
	public boolean save2UserMain(Um2Um um2Um) {
		boolean b1 = save(um2Um);
		int temp = um2Um.getUmid1();
		um2Um.setUmid1(um2Um.getUmid2());
		um2Um.setUmid2(temp);
		boolean b2 = save(um2Um);
		return b1 || b2;
	}

	public List<Um2Um> findByUserId(int umid) {
		String sql = "select * from UM2UM " +
				"where umid1 = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{umid+""});
		List<Um2Um> list = new ArrayList<Um2Um>();
		try {
			while (rs.next()) {
				Um2Um um2Um = new Um2Um(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				list.add(um2Um);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
}
