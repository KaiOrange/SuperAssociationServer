package com.sa.dao;

import java.util.List;

import com.sa.entity.Xiakeliao;

public interface IXiakeliaoDao {
	public boolean save(Xiakeliao xiakeliao);
	public boolean delete(int xklId);
	public boolean update(Xiakeliao xiakeliao);
	public List<Xiakeliao> findAll();
	public Xiakeliao findById(int xklId);
	public Xiakeliao findByXKL(Xiakeliao xkl);
	public List<Xiakeliao> getPageDate(int nowPage,int pageSize);
	public List<Xiakeliao> getPageDate(int xklid,int nowPage, int pageSize);
	public List<Xiakeliao> getPageDateByUmid(int umid,int nowPage, int pageSize);
	public List<Xiakeliao> getPageDateByUmid(int umid,int xklid,int nowPage, int pageSize);
	public boolean deleteWithTransaction(int xklid);
}
