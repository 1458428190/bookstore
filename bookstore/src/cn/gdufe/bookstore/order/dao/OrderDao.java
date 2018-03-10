package cn.gdufe.bookstore.order.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.gdufe.bookstore.book.domain.Book;
import cn.gdufe.bookstore.order.domain.Order;
import cn.gdufe.bookstore.order.domain.OrderItem;
import cn.gdufe.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	
	//添加订单
	public void addOrder(Order order)
	{
		try
		{
			String sql = "insert into orders values(?,?,?,?,?,?)";
			//util.time跟sql.time的转换
			Timestamp time = new Timestamp(order.getOrdertime().getTime());
			Object[] params = {order.getOid(),time,order.getTotal(),order.getState(),
					order.getOwner().getUid(),order.getAddress()};
			qr.update(sql, params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	//添加订单条目
	public void addOrderItems(List<OrderItem> list)
	{
		try
		{
			String sql = "insert into orderitem values(?,?,?,?,?)";
			Object[][] params = new Object[list.size()][];
			for(int i=0; i<list.size(); i++)
			{
				OrderItem item = list.get(i);
				params[i] = new Object[]{item.getIid(),item.getCount(),item.getSubtotal(),
						item.getOrder().getOid(),item.getBook().getBid()};
			}
			qr.batch(sql, params);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public List<Order> loadOrders(User user) {
		// TODO Auto-generated method stub
		
		try
		{
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid());
			for(Order order: orderList)
			{
				//加载所缺的orderItems和User
				loadOrderItems(order);
				order.setOwner(user);
			}
			return orderList;
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	
	}

	private void loadOrderItems(Order order) {
		// TODO Auto-generated method stub
		try
		{
			String sql = "select * from orderitem i, book b where i.bid=b.bid and i.oid=?";
			List<Map<String, Object>> list = qr.query(sql, new MapListHandler(),order.getOid());
			List<OrderItem> orderItems = new ArrayList<OrderItem>();
			for(Map<String, Object>map: list)
			{
				OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
				Book book = CommonUtils.toBean(map, Book.class);
				orderItem.setBook(book);
				orderItem.setOrder(order);
				orderItems.add(orderItem);
			}
			order.setList(orderItems);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public Order findOrderByOid(String oid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try
		{
			String sql = "select * from orders where oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
			loadOrderItems(order);
			return order;
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	//更新订单状态
	public void updateState(String oid, int state)
	{
		try
		{
			String sql = "update orders set state=? where oid=?";
			qr.update(sql,state,oid);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
