package cn.gdufe.bookstore.user.service;

import cn.gdufe.bookstore.user.dao.UserDao;
import cn.gdufe.bookstore.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	//注册
	public void regist(User user) throws UserException
	{
		if(userDao.findUserByUsername(user.getUsername())!=null)
		{
			throw new UserException("用户名已经被注册过");
		}
		if(userDao.findUserByEmail(user.getEmail())!=null)
		{
			throw new UserException("邮箱已经被注册过");
		}
		userDao.add(user);
	}
	//激活
	public void active(String code) throws UserException 
	{
		User user = userDao.findUserByCode(code);
		if(user==null)
			throw new UserException("激活码无效");
		if(user.isState())
			throw new UserException("您已经激活过了,就不要再激活");
		userDao.active(user.getUid(), true);
	}
	
	//登录
	public User login(User form) throws UserException
	{
		User user = userDao.findUserByUsername(form.getUsername());
		if(user==null)
			throw new UserException("用户名不存在");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码不正确");
		if(!user.isState())
			throw new UserException("您未激活");
		return user;
	}
}
