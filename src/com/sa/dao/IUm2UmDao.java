package com.sa.dao;

import java.util.List;

import com.sa.entity.Um2Um;

public interface IUm2UmDao {
	public boolean save(Um2Um um2Um);
	public boolean save2UserMain(Um2Um um2Um);
	public boolean delete(int um2UmId);
	public boolean delete2UserMain(int umid1,int umid2);
	public Um2Um findById(int um2UmId);
	public List<Um2Um> findByUserId(int uId);
	public Um2Um findById(int umid1,int umid2);
}
