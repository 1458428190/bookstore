package cn.gdufe.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.gdufe.bookstore.order.dao.OrderDao;
import cn.gdufe.bookstore.order.domain.Order;
import cn.gdufe.bookstore.user.domain.User;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public void add(Order order)
	{
		try
		{
			//开启事务
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);
			orderDao.addOrderItems(order.getList());
			JdbcUtils.commitTransaction();
		}
		catch (Exception e) {
			// TODO: handle exception
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	public List<Order> loadOrders(User user) {
		// TODO Auto-generated method stub
		return orderDao.loadOrders(user);
	}

	public Order load(String oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderByOid(oid);
	}
	
	//确认收货
	public void confirm(String oid) throws OrderException
	{
		Order order = orderDao.findOrderByOid(oid);
		if(order.getState()!=3)
		{
			throw new OrderException("您还没付款呢,瞎搞啥");
		}
		orderDao.updateState(oid, 4);
	}

	//支付业务
	public void pay(String r6_Order) {
		// TODO Auto-generated method stub
		Order order = load(r6_Order);
		if(order.getState()==1)
		{
			//是从未付款状态转为付款
			System.out.println("可以加积分业务");
			orderDao.updateState(r6_Order, 2);
		}
		
	}

}
