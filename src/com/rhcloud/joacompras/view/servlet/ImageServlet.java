package com.rhcloud.joacompras.view.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rhcloud.joacompras.core.bean.ItemBean;
import com.rhcloud.joacompras.core.dao.ItemDAO;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/imgitem")
public class ImageServlet extends HttpServlet {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	    public void doGet(HttpServletRequest request,
	                      HttpServletResponse response) throws ServletException,
	                                                           IOException {
	        
	        String id = request.getParameter("id");
	        OutputStream os = response.getOutputStream();
	        ItemBean bean = new ItemBean();
	        bean = new ItemDAO().buscarPorId( Long.parseLong(id), ItemBean.class);
	        os.write(bean.getImagem());
	        os.close();
}
}