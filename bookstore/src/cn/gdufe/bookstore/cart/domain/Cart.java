package cn.gdufe.bookstore.cart.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车
 * @author 赖程锋
 *
 */
public class Cart implements Serializable{
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	
	//总计
	public double getTotal()
	{
		BigDecimal total = new BigDecimal("0");
		for(CartItem ci:map.values())
		{
			BigDecimal subTotal = new BigDecimal(ci.getSubtotal()+"");
			total = total.add(subTotal);
		}
		return total.doubleValue();
	}
	
	
	//增加条目
	public void addCartItem(CartItem ci)
	{
		//已经存在一个相同书的条目
		if(map.containsKey(ci.getBook().getBid()))
		{
			CartItem ci1 = map.get(ci.getBook().getBid());
			ci1.setCount(ci1.getCount()+ci.getCount());
			map.put(ci.getBook().getBid(), ci1);
		}
		else
		{
			map.put(ci.getBook().getBid(), ci);
		}
	}

	//删除条目
	public void delete(String bid)
	{
		map.remove(bid);
	}
	
	//清空条目
	public void clear()
	{
		map.clear();
	}
	
	//获得所有条目
	public Collection<CartItem> getCartItems()
	{
		return map.values();
	}
}
