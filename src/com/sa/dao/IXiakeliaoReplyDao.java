package com.sa.dao;

import java.util.List;

import com.sa.entity.XiakeliaoReply;

public interface IXiakeliaoReplyDao {
	public boolean save(XiakeliaoReply xiakeliaoReply);
	public boolean delete(int xklrId);
	public boolean update(XiakeliaoReply xiakeliaoReply);
	public List<XiakeliaoReply> findAll();
	public XiakeliaoReply findById(int xklrId);
	public int findReplyCount(int xklid);
	public List<XiakeliaoReply> findAllByXklid(int xklid);
}
