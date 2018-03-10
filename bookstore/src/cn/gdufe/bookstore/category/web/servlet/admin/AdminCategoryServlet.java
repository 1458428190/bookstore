package cn.gdufe.bookstore.category.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gdufe.bookstore.category.domain.Category;
import cn.gdufe.bookstore.category.service.CategoryException;
import cn.gdufe.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
	}
	
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		try {
			categoryService.add(category);
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/admin/category/add.jsp";
		}
		return findAll(request, response);
	}
	
	//删除
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid = request.getParameter("cid");
		try {
			categoryService.deleteCategory(cid);
		} catch (CategoryException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg", e.getMessage());
		}
		return findAll(request,response);
	}
	
	//修改前
	public String updatePre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cid =  request.getParameter("cid");
		Category category = categoryService.load(cid);
		request.setAttribute("category", category);
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	
	//修改前
	public String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		categoryService.update(category);
		return findAll(request,response);
	}
}
