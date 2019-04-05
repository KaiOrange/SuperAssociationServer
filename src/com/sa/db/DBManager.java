package com.sa.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBManager {
	Connection conn = null;
	Statement sta = null;
	ResultSet rs = null;
	private PreparedStatement prepareStatement;
	private final String DB_USER_NAME = "root";
	private final String DB_USER_PWD = "sa";
	private final boolean isShowLog = true;
	private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/SuperAssociation?&user=["
		+ DB_USER_NAME + "]&password=[" + DB_USER_PWD + 
		"]&useUnicode=true&characterEncoding=UTF-8";
	
	public Connection getConnection(){
		String url = this.DB_URL;
//		String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SuperAssociation";
			Connection myConn = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				myConn= DriverManager.getConnection(url, this.DB_USER_NAME, this.DB_USER_PWD);
//				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//				myConn = DriverManager.getConnection(url,"sa","sa");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return myConn;
	}
	
	public boolean update(String sql,String[] columnNames){
		if (this.isShowLog) {			
			System.out.println(sql);
		}
		String url = this.DB_URL;
		int execute = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, this.DB_USER_NAME, this.DB_USER_PWD);
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			conn = DriverManager.getConnection(url,"sa","sa");
			prepareStatement = conn.prepareStatement(sql);
			for (int i = 0; i < columnNames.length; i++) {
				prepareStatement.setString(i + 1, columnNames[i]);
			}
			execute = prepareStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return execute > 0;
	}
	public boolean update(String sql,String[] columnNames,Connection myCon){
		if (this.isShowLog) {			
			System.out.println(sql);
		}
		int execute = 0;
		try {
			prepareStatement = myCon.prepareStatement(sql);
			for (int i = 0; i < columnNames.length; i++) {
				prepareStatement.setString(i + 1, columnNames[i]);
			}
			execute = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return execute > 0;
	}
	
	public int update(String sql,Connection myCon){
		if (this.isShowLog) {			
			System.out.println(sql);
		}
		String url = this.DB_URL;
//		String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SuperAssociation";
		int row = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, this.DB_USER_NAME, this.DB_USER_PWD);
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			myCon = DriverManager.getConnection(url,"sa","sa");
			sta = myCon.createStatement();
			row = sta.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	
	
	public ResultSet query(String sql,String[] columnNames){
		if (this.isShowLog) {			
			System.out.println(sql);
		}
		
		String url = this.DB_URL;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, this.DB_USER_NAME, this.DB_USER_PWD);
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			conn = DriverManager.getConnection(url, "sa", "sa");
			prepareStatement = conn.prepareStatement(sql);
			for (int i = 0; columnNames!= null && i < columnNames.length; i++) {
				prepareStatement.setString(i+1, columnNames[i]);
			}
			rs= prepareStatement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void close(){
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (prepareStatement != null) {
				prepareStatement.close();
				prepareStatement = null;
			}
			if (sta != null) {
				sta.close();
				sta = null;
			}
			if (conn!= null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
		}
	}
}
