package cn.gdufe.bookstore.user.web.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gdufe.bookstore.cart.domain.Cart;
import cn.gdufe.bookstore.user.domain.User;
import cn.gdufe.bookstore.user.service.UserException;
import cn.gdufe.bookstore.user.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		System.out.println("Email:  "+user.getEmail());
		Map<String, String> message = new HashMap<String, String>();
		
		if(user.getUsername()==null || user.getUsername().trim().isEmpty())
		{
			message.put("username", "用户名不能为空");
		}
		else if(!(user.getUsername().length()>=2&&user.getUsername().length()<=10))
		{
			message.put("username", "用户名长度必须为2~10");
		}
		
		if(user.getEmail()==null || user.getEmail().trim().isEmpty())
		{
			message.put("email", "邮箱不能为空");
		}
		else if(!user.getEmail().matches("\\w+@\\w+\\.\\w+"))
		{
			message.put("email", "邮箱格式不对");
		}
		
		if(user.getPassword()==null || user.getPassword().trim().isEmpty())
		{
			message.put("password", "密码不能为空");
		}
		else if(!(user.getPassword().length()>=2&&user.getPassword().length()<=10))
		{
			message.put("password", "密码长度必须为2~10");
		}
		
		if(!message.isEmpty())
		{
			request.setAttribute("errors", message);
			request.setAttribute("user", user);
			return "f:/jsps/user/regist.jsp";
		}
		try
		{
			user.setUid(CommonUtils.uuid());
			user.setCode(CommonUtils.uuid()+CommonUtils.uuid());
			user.setState(false);   //初始为未激活
			userService.regist(user);
		}
		catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", user);
			return "f:/jsps/user/regist.jsp";
		}
		
		
		/*
		 * 发邮箱
		 */
		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("/email_templte.properties"));
		String host = prop.getProperty("host");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String from = prop.getProperty("from");
		String subject = prop.getProperty("subject");
		String content = prop.getProperty("content");
		//将{0}替换为user.getCode();
		content = MessageFormat.format(content, user.getCode());
		String to = user.getEmail();
		
		Session session = MailUtils.createSession(host,username,password);
		Mail mail = new Mail(from, to, subject, content);
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("msg", "恭喜你注册成功,赶快登录邮箱激活吧");
		return "f:/jsps/msg.jsp";
	}
	
	//激活
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		try {
			userService.active(request.getParameter("code"));
			request.setAttribute("msg", "激活成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	//登录
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		//一键封装
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		Map<String,String> errors = new HashMap<String, String>();
		
		//输入校验
		if(form.getUsername()==null||form.getUsername().trim().isEmpty())
		{
			errors.put("username", "用户名不能为空");
		}
		else if(form.getUsername().length()<2||form.getUsername().length()>10)
		{
			errors.put("username", "用户名长度必须为2~10");
		}
		
		if(form.getPassword()==null||form.getPassword().trim().isEmpty())
		{
			errors.put("password", "密码不能为空");
		}
		else if(form.getPassword().length()<2||form.getPassword().length()>10)
		{
			errors.put("password", "密码长度必须为2~10");
		}
		
		if(!errors.isEmpty())
		{
			request.setAttribute("errors", errors);
			request.setAttribute("user", form);
			return "f:/jsps/user/login.jsp";
		}
		
		try {
			User user = userService.login(form);
			request.getSession().setAttribute("session_user", user);
			//加一个购物车进去
			request.getSession().setAttribute("session_cart", new Cart());
			
			return "r:/index.jsp";
		} catch (UserException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);
			return "f:/jsps/user/login.jsp";
		}
	}
	
	//退出
	public String quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().invalidate();
		return "r:/index.jsp";
	}
}
