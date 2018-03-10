package cn.gdufe.bookstore.book.service;

import java.util.List;

import cn.gdufe.bookstore.book.dao.BookDao;
import cn.gdufe.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	
	public List<Book> findAll()
	{
		return bookDao.findAll();
	}

	public List<Book> findByCategory(String cid) {
		// TODO Auto-generated method stub
		return bookDao.findByCategory(cid);
	}

	public Book findByBid(String bid) {
		// TODO Auto-generated method stub
		return bookDao.findByBid(bid);
	}

	public void add(Book book) {
		// TODO Auto-generated method stub
		bookDao.add(book);
	}

	public void delete(String bid) {
		// TODO Auto-generated method stub
		bookDao.delete(bid);
	}

	public void update(Book book) {
		// TODO Auto-generated method stub
		bookDao.update(book);
	}

}
