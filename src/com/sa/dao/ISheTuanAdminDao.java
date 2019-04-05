package com.sa.dao;

import java.sql.Connection;
import java.util.List;

import com.sa.entity.ShetuanAdmin;

public interface ISheTuanAdminDao {
	public boolean save(ShetuanAdmin shetuanAdmin);
	public boolean delete(int staId);
	public boolean deleteAll(int stid,Connection con);
	public boolean deleteAll(int stid);
	public boolean update(ShetuanAdmin shetuanAdmin);
	public List<ShetuanAdmin> findAll();
	public ShetuanAdmin findById(int staId);
	public List<ShetuanAdmin> findByStid(int stid);
	public ShetuanAdmin findById(int stid,int umid);
	public boolean deleteByUmid(int stid,int umid);
}
