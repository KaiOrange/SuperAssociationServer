package com.sa.action;

import java.util.List;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.sa.dao.IActivityDao;
import com.sa.dao.ISheTuanDao;
import com.sa.dao.impl.ActivityDaoImpl;
import com.sa.dao.impl.SheTuanDaoImpl;
import com.sa.entity.Activity;
import com.sa.entity.SheTuan;
import com.sa.tools.PrintText2Client;

public class ActivityAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Activity activity;
	private int stid;
	private int activityId;


	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public String createActivity(){
		String returnStr = "failure";
		if (activity != null) {
			ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
			SheTuan findSheTuan = sheTuanDao.findById(activity.getStid());
			if (findSheTuan != null) {
				IActivityDao activityDao = new ActivityDaoImpl();
				boolean b = activityDao.save(activity);
				if (b) {
					returnStr = "seccess";
				}
			} else {
				returnStr = "notFound";
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	
	public String deleteThisActivity(){
		String returnStr = "failure";
			//FIXME 删除活动时 还应该删除他的自表中的东西
		IActivityDao activityDao = new ActivityDaoImpl();
		boolean b = activityDao.delete(activityId);
		if (b) {
			returnStr = "seccess";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String updateActivity(){
		String returnStr = "failure";
		if (activity != null) {
			ISheTuanDao sheTuanDao = new SheTuanDaoImpl();
			SheTuan findSheTuan = sheTuanDao.findById(activity.getStid());
			if (findSheTuan != null) {
				IActivityDao activityDao = new ActivityDaoImpl();
				boolean b = activityDao.update(activity);
				if (b) {
					returnStr = "seccess";
				}
			} else {
				returnStr = "notFound";
			}
		}
		PrintText2Client.print(returnStr);
		return null;
	}
	public String getSheTuanActivity(){
		String returnStr = "failure";
		IActivityDao activityDao = new ActivityDaoImpl();
		List<Activity> activitys = activityDao.findByStid(stid);
		if (activitys != null && activitys.size() > 0) {
			Activity[] array = activitys.toArray(new Activity[]{});
			returnStr = new Gson().toJson(array);
		} else {
			returnStr = "notFound";
		}
		PrintText2Client.print(returnStr);
		return null;
	}
}
