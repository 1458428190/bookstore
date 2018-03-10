package cn.gdufe.bookstore.user.demo;

import java.util.List;

public class UserDao extends BaseDao<User>{
	public void addUser(User user) throws IllegalArgumentException, IllegalAccessException
	{
		super.add(user);
	}
	
	public void updateUser(User user) throws IllegalArgumentException, IllegalAccessException
	{
		super.update(user);
	}

	public void deleteUser(String uid) {
		// TODO Auto-generated method stub
		super.delete(uid);
	}

	public User loadUser(String uid) {
		// TODO Auto-generated method stub
		return super.load(uid);
	}
	
	public List<User> findAll()
	{
		return super.findAll();
	}

}
