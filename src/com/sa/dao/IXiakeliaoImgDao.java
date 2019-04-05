package com.sa.dao;

import java.util.List;

import com.sa.entity.XiakeliaoImg;

public interface IXiakeliaoImgDao {
	public boolean save(XiakeliaoImg xiakeliaoImg);
	public boolean delete(int xkliId);
	public boolean update(XiakeliaoImg xiakeliaoImg);
	public List<XiakeliaoImg> findAll();
	public XiakeliaoImg findById(int xkliId);
}
