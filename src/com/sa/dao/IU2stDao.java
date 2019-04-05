package com.sa.dao;

import java.sql.Connection;
import java.util.List;

import com.sa.entity.U2st;

public interface IU2stDao {
	public boolean save(U2st u2st);
	public boolean delete(int u2stId);
	public boolean update(U2st u2st);
	public List<U2st> findAll();
	public U2st findById(int u2stId);
	public U2st findByUserId(int uId);
	public U2st findByStidAndUid(int stid,int umid,int status);
	public List<U2st> findByStid(int stid,int status);
	public boolean delete(int umid,int stid,int status);
	public boolean deleteAll(int stid,Connection con) ;
}
