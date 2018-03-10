package cn.gdufe.bookstore.cart.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.gdufe.bookstore.book.domain.Book;

/**
 * 条目
 * @author 赖程锋
 *
 */
public class CartItem implements Serializable{
	//图书以及数量
	private Book book;
	private int count;
	
	//小计
	public double getSubtotal()
	{
		BigDecimal price = new BigDecimal(book.getPrice()+"");
		return price.multiply(new BigDecimal(count+"")).doubleValue();
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	

}
