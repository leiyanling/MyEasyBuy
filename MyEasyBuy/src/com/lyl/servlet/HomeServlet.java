package com.lyl.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyl.service.product.ProductService;
import com.lyl.service.product.ProductServiceImpl;
import com.lyl.service.productcategory.ProductCategoryService;
import com.lyl.service.productcategory.ProductCategoryServiceImpl;
import com.lyl.vo.ProductCategoryVo;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductCategoryService productCategoryService = new ProductCategoryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "index":// 首页展示的数据

			// 左边
			List<ProductCategoryVo> categoryList = productCategoryService.getAll();
			request.setAttribute("productCategoryVoList", categoryList);
			request.getRequestDispatcher("pre/index.jsp").forward(request, response);
			break;

		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
