package cn.gdufe.bookstore.user.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.gdufe.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	
	//按用户名查询
	public User findUserByUsername(String username)
	{
		try
		{
			String sql = "select * from tb_user where username=?";
			return qr.query(sql, new BeanHandler<User>(User.class),username);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	//按邮箱查询
	public User findUserByEmail(String email)
	{
		try
		{
			String sql = "select * from tb_user where email=?";
			return qr.query(sql, new BeanHandler<User>(User.class),email);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//添加用户
	public void add(User user)
	{
		try
		{
			String sql = "insert into tb_user values(?,?,?,?,?,?)";
			Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),
					user.getEmail(),user.getCode(),user.isState()
			};
			qr.update(sql,params);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public User findUserByCode(String code)
	{
		try
		{
			String sql = "select * from tb_user where code=?";
			return qr.query(sql, new BeanHandler<User>(User.class),code);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//激活用户
	public void active(String uid, boolean state)
	{
		try
		{
			String sql = "update tb_user set state=? where uid=?";
			qr.update(sql,state,uid);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
