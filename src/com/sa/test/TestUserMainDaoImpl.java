package com.sa.test;

import java.util.List;

import com.sa.dao.IUserMainDao;
import com.sa.dao.impl.UserMainDaoImpl;
import com.sa.entity.UserMain;

public class TestUserMainDaoImpl {
	public static void main(String[] args) {
//		IUserMainDao userMainDao = new UserMainDaoImpl();
//		System.out.println(userMainDao.findUserMainByNameAndPwd("aaa", "aaa"));
//		TestUserMainDaoImpl testUserMainDaoImpl = new TestUserMainDaoImpl();
//		testUserMainDaoImpl.update(userMain);
//		System.out.println(testUserMainDaoImpl.findById(1));
//		UserMain userMain = new UserMain(1, "bbb", "bbb", "ÕÅ¶þÍÞ", "123.jpg", true, 12, DateConvert.convertToString(new Date()), "15555555555", "ruanjian1203");
//		testUserMainDaoImpl.save(userMain);
//		List<UserMain> findAll = testUserMainDaoImpl.findAll();
//		for (UserMain userMain2 : findAll) {
//			System.out.println(userMain2);
//		}
	}
	public boolean delete(int umId) {
		IUserMainDao userMainDao = new UserMainDaoImpl();
		return userMainDao.delete(umId);
	}

	public List<UserMain> findAll() {
		IUserMainDao userMainDao = new UserMainDaoImpl();
		return userMainDao.findAll();
	}

	public UserMain findById(int umId) {
		return new UserMainDaoImpl().findById(umId);
	}

	public boolean save(UserMain userMain) {
		return new UserMainDaoImpl().save(userMain);
	}

	public boolean update(UserMain userMain) {
		return new UserMainDaoImpl().update(userMain);
	}

}
