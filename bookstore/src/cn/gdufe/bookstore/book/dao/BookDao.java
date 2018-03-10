package cn.gdufe.bookstore.book.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.gdufe.bookstore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Book> findAll()
	{
		try
		{
			String sql = "select * from book where del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public List<Book> findByCategory(String cid) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "select * from book where cid=? and del=false";
			return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public Book findByBid(String bid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try
		{
			String sql = "select * from book where bid=?";
			return qr.query(sql, new BeanHandler<Book>(Book.class), bid);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public int getCountForCid(String cid) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "select count(*) from book where cid=? and del=false";
			Number number = (Number)qr.query(sql, new ScalarHandler(),cid);
			return number.intValue();
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void add(Book book) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "insert into book values(?,?,?,?,?,?,?)";
			Object[] params = {book.getBid(),book.getBname(),book.getPrice(),
					book.getAuthor(),book.getImage(),book.getCid(),false};
			qr.update(sql, params);
			System.out.println("不是过了吗");
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void delete(String bid) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "update book set del=true where bid=?";
			qr.update(sql, bid);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void update(Book book) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "update book set bname=?,price=?,author=?,cid=? where bid=?";
			Object[] param = {book.getBname(),book.getPrice(),book.getAuthor(),book.getCid(),book.getBid()};
			qr.update(sql,param);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
