package com.sa.dao;

import java.util.List;

import com.sa.entity.ActivityTopic;

public interface IActivityTopicDao {
	public boolean save(ActivityTopic activityTopic);
	public boolean delete(int atId);
	public List<ActivityTopic> findAll();
	public ActivityTopic findById(int acId);
	public List<ActivityTopic> findAllByAid(int aid);
}
