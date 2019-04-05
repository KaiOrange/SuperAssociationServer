package com.sa.dao;

import java.sql.Connection;
import java.util.List;

import com.sa.entity.Activity;

public interface IActivityDao {
	public boolean save(Activity activity);
	public boolean delete(int aId);
	public boolean update(Activity activity);
	public List<Activity> findAll();
	public Activity findById(int aId);
	public List<Activity> findByStid(int stId);
	public boolean deleteAll(int stid,Connection con);
}
