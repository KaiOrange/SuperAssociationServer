package com.sa.dao;

import java.util.List;

import com.sa.entity.Tongzhi;

public interface ITongzhiDao {
	public boolean save(Tongzhi tongzhi);
	public boolean delete(int tzId);
	public boolean update(Tongzhi tongzhi);
	public List<Tongzhi> findAll();
	public Tongzhi findById(int tzId);
	public List<Tongzhi> findByUmid(int umid,int status);
	public boolean updateStatus(Tongzhi tongzhi);//要确保有主键
}
