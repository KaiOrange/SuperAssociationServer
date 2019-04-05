package com.sa.dao;

import java.util.List;

import com.sa.entity.ActivityTopicReply;

public interface IActivityTopicReplyDao {
	public boolean save(ActivityTopicReply activityTopicReply);
	public boolean delete(int atrId);
	public List<ActivityTopicReply> findAll();
	public ActivityTopicReply findById(int atrid);
	public boolean deleteAllByAtid(int atid);
	public List<ActivityTopicReply> findAllByAid(int atid);
}
