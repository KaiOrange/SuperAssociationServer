package com.sa.dao;

import com.sa.entity.SheTuanJiao;

public interface ISheTuanJiaoDao {
	public boolean save(SheTuanJiao sheTuanJiao);
	public SheTuanJiao findRandom(String name);
}
