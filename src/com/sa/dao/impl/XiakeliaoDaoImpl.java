package com.sa.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sa.dao.IXiakeliaoDao;
import com.sa.db.DBManager;
import com.sa.entity.Xiakeliao;
import com.sa.tools.DateConvert;

public class XiakeliaoDaoImpl implements IXiakeliaoDao {

	public boolean delete(int xklid) {
		String sql = "delete from Xiakeliao where" +
			" xklid = " + xklid;
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{}));
	}
	public boolean delete(int xklid,Connection connection) {
		String sql = "delete from Xiakeliao where" +
		" xklid = " + xklid;
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{},connection));
	}
	public boolean deleteWithTransaction(int xklid){
		DBManager dbManager = new DBManager();
		Connection connection = dbManager.getConnection();
		boolean b = false;
		try {
			connection.setAutoCommit(false);
			new XiakeliaoReplyDaoImpl().deleteByxklid(xklid, connection);
			this.delete(xklid, connection);
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

	public List<Xiakeliao> findAll() {
		List<Xiakeliao> list = new ArrayList<Xiakeliao>();
		String sql = "select * from Xiakeliao";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,null);
		try {
			while (rs.next()) {
				Xiakeliao xiakeliao = new Xiakeliao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				list.add(xiakeliao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

	public Xiakeliao findById(int xklid) {
		Xiakeliao xiakeliao = null;
		String sql = "select * from Xiakeliao " +
				"where xklid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{xklid+""});
		try {
			if (rs.next()) {
				xiakeliao = new Xiakeliao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return xiakeliao;
	}
	public Xiakeliao findByXKL(Xiakeliao xkl) {
		Xiakeliao xiakeliao = null;
		String sql = "select * from Xiakeliao " +
		"where xkltitle = ? and xkltext = ? and cjdate = ? and umid = ? ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql,new String[]{xkl.getXkltitle(),xkl.getXkltext(),xkl.getCjdate(),xkl.getUmid() + ""});
		try {
			if (rs.next()) {
				xiakeliao = new Xiakeliao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return xiakeliao;
	}

	public boolean save(Xiakeliao xiakeliao) {
		String sql = "insert into Xiakeliao(xkltitle,xkltext,type,umid,cjdate)"  
			+ "values(?,?,?,?,?)";
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String[]{xiakeliao.getXkltitle() ,xiakeliao.getXkltext(),""+xiakeliao.getType(),""+xiakeliao.getUmid(),DateConvert.getDateStr()}));
	}

	public boolean update(Xiakeliao xiakeliao) {
		String sql = "update Xiakeliao set xkltitle = ?,xkltext = ?,type = ?,umid = ?,cjdate = ? where xklid = ?"; 
		DBManager dbManager = new DBManager();
		return (dbManager.update(sql,new String []{
				xiakeliao.getXkltitle(),
				xiakeliao.getXkltext(),""+xiakeliao.getType(),
				""+xiakeliao.getUmid(),
				xiakeliao.getCjdate(),
				""+xiakeliao.getXklid()}));
	}
	public List<Xiakeliao> getPageDate(int nowPage, int pageSize) {
		List<Xiakeliao> list = new ArrayList<Xiakeliao>();
		String sql = "select * from Xiakeliao order by xklid desc limit "+nowPage+","+pageSize+" ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql, null);
		try {
			while (rs.next()) {
				Xiakeliao xiakeliao = new Xiakeliao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				list.add(xiakeliao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<Xiakeliao> getPageDate(int xklid,int nowPage, int pageSize) {
		List<Xiakeliao> list = new ArrayList<Xiakeliao>();
		String sql = "select * from Xiakeliao  where xklid < " + xklid + " order by xklid desc limit "+nowPage+","+pageSize+" ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql, null);
		try {
			while (rs.next()) {
				Xiakeliao xiakeliao = new Xiakeliao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				list.add(xiakeliao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<Xiakeliao> getPageDateByUmid(int umid,int nowPage, int pageSize) {
		List<Xiakeliao> list = new ArrayList<Xiakeliao>();
		String sql = "select * from Xiakeliao where umid = " + umid + " order by xklid desc limit "+nowPage+","+pageSize+" ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql, null);
		try {
			while (rs.next()) {
				Xiakeliao xiakeliao = new Xiakeliao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				list.add(xiakeliao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}
	public List<Xiakeliao> getPageDateByUmid(int umid,int xklid,int nowPage, int pageSize) {
		List<Xiakeliao> list = new ArrayList<Xiakeliao>();
		String sql = "select * from Xiakeliao  where xklid < " + xklid + " and umid = " + umid + " order by xklid desc limit "+nowPage+","+pageSize+" ";
		DBManager dbManager = new DBManager();
		ResultSet rs = dbManager.query(sql, null);
		try {
			while (rs.next()) {
				Xiakeliao xiakeliao = new Xiakeliao(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
				list.add(xiakeliao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.close();
		}
		return list;
	}

}
