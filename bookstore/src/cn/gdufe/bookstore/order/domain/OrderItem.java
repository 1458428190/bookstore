package cn.gdufe.bookstore.order.domain;

import java.io.Serializable;

import cn.gdufe.bookstore.book.domain.Book;

/**
 * 订单条目
 * @author 赖程锋
 *
 */
public class OrderItem implements Serializable{
	private String iid;
	private int count;
	private double subtotal;
	private Book book;   //书籍
	private Order order;  //所属订单
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal=" + subtotal + ", book=" + book + ", order="
				+ order + "]";
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
