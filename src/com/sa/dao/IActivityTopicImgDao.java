package com.sa.dao;

import java.util.List;

import com.sa.entity.ActivityTopicImg;

public interface IActivityTopicImgDao {
	public boolean save(ActivityTopicImg activityTopicImg);
	public boolean delete(int atiId);
	public boolean update(ActivityTopicImg activityTopicImg);
	public List<ActivityTopicImg> findAll();
	public ActivityTopicImg findById(int atiId);
}
