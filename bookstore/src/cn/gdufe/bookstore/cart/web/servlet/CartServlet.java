package cn.gdufe.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gdufe.bookstore.book.domain.Book;
import cn.gdufe.bookstore.book.service.BookService;
import cn.gdufe.bookstore.cart.domain.Cart;
import cn.gdufe.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	
	//增加条目
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("什么鬼");
		//得到购物车
		Cart cart = (Cart) request.getSession().getAttribute("session_cart");
		//得到条目
		String bid = request.getParameter("bid");
		int count = Integer.parseInt(request.getParameter("count"));
		
		System.out.println("bid=="+bid+"============count=="+count);
		
		
		Book book = (new BookService()).findByBid(bid);
		
		CartItem cItem = new CartItem();
		cItem.setBook(book);
		cItem.setCount(count);
		cart.addCartItem(cItem);
		return "f:/jsps/cart/list.jsp";
	}
	
	//清空购物车
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cart cart = (Cart) request.getSession().getAttribute("session_cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}
	
	//删除指定条目
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//得到购物车
		Cart cart = (Cart) request.getSession().getAttribute("session_cart");
		//得到条目
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}

}
