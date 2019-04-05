package com.sa.dao;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import com.sa.entity.SheTuan;

public interface ISheTuanDao {
	public boolean save(SheTuan sheTuan);
	public boolean delete(int stId,Connection con);
	public boolean update(SheTuan sheTuan);
	public List<SheTuan> findAll();
	public SheTuan findById(int stId);
	public SheTuan findByUid(int umId);
	public SheTuan findBySName(String sname);
	public boolean deleteSheTuan(int stid);
	public LinkedList<SheTuan> getPageDate(int nowPage,int pageSize);
	public LinkedList<SheTuan> getPageDate(int stid,int nowPage, int pageSize);
}
