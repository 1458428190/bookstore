package cn.gdufe.bookstore.order.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.gdufe.bookstore.user.domain.User;

public class Order implements Serializable{
	private String oid;
	private Date ordertime;
	private Double total;
	private int state;    //订单状态,未付款,已付款,未收货,已收货
	private String address;
	private List<OrderItem> list;    //订单条目
	private User owner;
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<OrderItem> getList() {
		return list;
	}
	public void setList(List<OrderItem> list) {
		this.list = list;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", address="
				+ address + ", list=" + list + ", owner=" + owner + "]";
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
