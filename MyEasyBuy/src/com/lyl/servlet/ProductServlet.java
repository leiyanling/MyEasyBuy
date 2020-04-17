package com.lyl.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyl.entity.Product;
import com.lyl.service.product.ProductService;
import com.lyl.service.product.ProductServiceImpl;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "queryProductDeatil":
			int pid = Integer.parseInt(request.getParameter("id"));
			Product product = productService.getProductById(pid);
			
			request.setAttribute("product", product);
			
			request.getRequestDispatcher("pre/product/productDeatil.jsp").forward(request, response);
			break;

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
