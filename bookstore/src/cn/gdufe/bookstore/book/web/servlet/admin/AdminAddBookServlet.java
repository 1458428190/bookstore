package cn.gdufe.bookstore.book.web.servlet.admin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.gdufe.bookstore.book.domain.Book;
import cn.gdufe.bookstore.book.service.BookService;
import cn.gdufe.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;



public class AdminAddBookServlet extends HttpServlet {

	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 三步，创建工厂，创建解析器，创建FileItem
		 */
		//设置缓存大小以及缓存目录
		DiskFileItemFactory dfif = new DiskFileItemFactory(100*1024, new File("E:/f/temp"));
		//创建解析器
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		//限制单个文件的大小
		sfu.setFileSizeMax(50*1024);
		Map<String, String> map = new HashMap<String, String>();

		System.out.println("????????????????");
		try {
			List<FileItem> list = sfu.parseRequest(request);
			String fileName = "";
			for(FileItem fileItem:list)
			{
				if(fileItem.isFormField())
				{
					map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				}
			}
			Book book = CommonUtils.toBean(map, Book.class);
			book.setBid(CommonUtils.uuid());
			
			FileItem fileItem = list.get(1);
			fileName = CommonUtils.uuid()+"_"+fileItem.getName();
			String realPath = request.getServletContext().getRealPath("/book_img");
			File file = new File(realPath, fileName);
			fileItem.write(file);
			if(!fileName.toLowerCase().endsWith("jpg"))
			{
				//不是jpg文件
				System.out.println("不是jPG");
				request.setAttribute("msg", "您上传的文件不是jpg");
				request.setAttribute("categoryList", categoryService.findAll());
				request.setAttribute("book", book);
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			Image image = new ImageIcon(file.getAbsolutePath()).getImage();
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			if(width>220 || height>220)
			{
				System.out.println(" 我曹 图片大小");
				request.setAttribute("msg", "您上传的图片不是220*220");
				request.setAttribute("book", book);
				request.setAttribute("categoryList", categoryService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			System.out.println("??????");
			book.setImage("book_img/"+fileName);
			System.out.println("我去");
			//添加图书
			bookService.add(book);
			System.out.println("成功通过");
			request.getRequestDispatcher("/admin/AdminBookServlet?method=findAll").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(e instanceof FileSizeLimitExceededException)
			{
				System.out.println("不是30k以内");
				request.setAttribute("msg", "您上传的文件大于30k");
				request.setAttribute("categoryList", categoryService.findAll());
				
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
			}
		}
	}

}
