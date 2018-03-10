package cn.gdufe.bookstore.category.service;

import java.util.List;

import cn.gdufe.bookstore.book.dao.BookDao;
import cn.gdufe.bookstore.category.dao.CategoryDao;
import cn.gdufe.bookstore.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	public void add(Category category) throws CategoryException {
		// TODO Auto-generated method stub
		if(categoryDao.isExist(category.getCname()))
		{
			throw new CategoryException("已存在该分类,请勿添加重复的分类");
		}
		categoryDao.add(category);
	}

	public Category load(String cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	
	public void deleteCategory(String cid) throws CategoryException
	{
		//只能删除空分类
		if(bookDao.getCountForCid(cid)>0)
		{
			//分类中有图书,不能删除
			throw new CategoryException("该分类中有图书,不能删除该分类");
		}
		categoryDao.delete(cid);
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}
}
