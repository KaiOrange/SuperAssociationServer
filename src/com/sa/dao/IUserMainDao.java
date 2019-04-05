package com.sa.dao;

import java.util.List;

import com.sa.entity.UserMain;

public interface IUserMainDao {
	public boolean save(UserMain userMain);
	public boolean delete(int umId);
	public boolean update(UserMain userMain);
	public List<UserMain> findAll();
	public UserMain findById(int umId);
	public UserMain findUserMainByNameAndPwd(String userName,String pwd);
	public UserMain findUserMainByName(String userName);
}
