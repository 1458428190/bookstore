package cn.gdufe.bookstore.user.demo;

import org.junit.Test;

import cn.itcast.commons.CommonUtils;

//编写BaseDao
public class Demo1 {
	private UserDao userDao = new UserDao();
	//测试添加
	@Test
	public void fun() throws IllegalArgumentException, IllegalAccessException
	{
		User user = new User();
		user.setUid(CommonUtils.uuid());
		user.setUsername("wangwu");
		user.setPassword("222");
		userDao.addUser(user);
	}
	
	//测试修改
	@Test
	public void fun1() throws IllegalArgumentException, IllegalAccessException
	{
		User user = new User();
		user.setUid("ADDD0893DB464608B13C4CA885FECDB5");
		user.setUsername("张三");
		user.setPassword("222");
		userDao.updateUser(user);
	}
	
	//测试删除
	@Test
	public void fun2()
	{
		String uid = "ADDD0893DB464608B13C4CA885FECDB5";
		userDao.deleteUser(uid);
	}
	
	//测试载入一个记录
	@Test
	public void fun3()
	{
		String uid = "0FFCE4C6ABAA4407A14C5F268DEAD499";
		User user = userDao.loadUser(uid);
		System.out.println(user);
	}
	
	//测试载入所有记录
	@Test
	public void fun4()
	{
		System.out.println(userDao.findAll());
	}
}

