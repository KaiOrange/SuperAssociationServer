package com.sa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.ITongzhiDao;
import com.sa.db.DBManager;
import com.sa.entity.Tongzhi;
import com.sa.tools.DateConvert;

public class TongzhiDaoImpl implements ITongzhiDao {

	public boolean delete(int tzid) {
		String sql = "delete from Tongzhi where" +
			" tzid = ? ";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{"" + tzid}));
	}

	public List<Tongzhi> findAll() {
		List<Tongzhi> list = new ArrayList<Tongzhi>();
		String sql = "select * from Tongzhi";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				Tongzhi tongzhi = new Tongzhi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				list.add(tongzhi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public Tongzhi findById(int tzid) {
		Tongzhi tongzhi = null;
		String sql = "select * from Tongzhi " +
				"where tzid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{tzid+""});
		try {
			if (rs.next()) {
				tongzhi = new Tongzhi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return tongzhi;
	}
	public List<Tongzhi> findByUmid(int umid,int status) {
		String sql = "select * from Tongzhi " +
		"where umid = ? and status = ?";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{umid+"",""+status});
		List<Tongzhi> list = new ArrayList<Tongzhi>();
		try {
			while (rs.next()) {
				Tongzhi tongzhi = new Tongzhi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8));
				list.add(tongzhi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public boolean save(Tongzhi tongzhi) {
		String sql = "insert into Tongzhi(tztext,tztitle,umid,tzdate,status,tzType,additional)"  
			+ " values(?,?,?,?,1,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{tongzhi.getTztext(),tongzhi.getTztitle(),""+tongzhi.getUmid(),DateConvert.getDateStr(),"" + tongzhi.getTzType(),tongzhi.getAdditional()}));
	}

	public boolean update(Tongzhi tongzhi) {
		String sql = "update Tongzhi set tztext = ?,tztitle = ?,umid = ?,status = ?,tzType = ?,additional = ? where tzid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{
				tongzhi.getTztext(),tongzhi.getTztitle(),
				""+tongzhi.getUmid(),
				""+tongzhi.getStatus(),
				""+tongzhi.getTzType(),
				tongzhi.getAdditional(),
				""+tongzhi.getTzid()
		}));
	}
	public boolean updateStatus(Tongzhi tongzhi) {
		String sql = "update Tongzhi set status = ? where tzid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{
				""+tongzhi.getStatus(),
				""+tongzhi.getTzid()}));
	}
}
