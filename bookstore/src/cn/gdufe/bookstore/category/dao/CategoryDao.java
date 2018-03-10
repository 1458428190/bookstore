package cn.gdufe.bookstore.category.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.gdufe.bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		try
		{
			String sql = "select * from category";
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void add(Category category) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "insert into category values(?,?)";
			qr.update(sql, category.getCid(),category.getCname());
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public boolean isExist(String cname)
	{
		try
		{
			String sql = "select * from category where cname=?";
			Category category = qr.query(sql, new BeanHandler<Category>(Category.class),cname);
			if(category!=null)
			{
				return true;
			}
			else
				return false;
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}

	public Category findByCid(String cid) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "select * from category where cid=?";
			return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String cid)
	{
		try
		{
			String sql = "delete from category where cid=?";
			qr.update(sql, cid);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "update category set cname=? where cid=?";
			qr.update(sql,category.getCname(),category.getCid());
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
