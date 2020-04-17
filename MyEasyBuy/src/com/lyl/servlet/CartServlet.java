package com.lyl.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lyl.entity.Product;
import com.lyl.service.product.ProductService;
import com.lyl.service.product.ProductServiceImpl;
import com.lyl.vo.CartItemVo;
import com.lyl.vo.CartVo;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
		super();
	}

	private ProductService proudctService = new ProductServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 添加购物车
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		CartVo cartVo = null;// 购物车
		switch (action) {
		case "add":
			cartVo = new CartVo();
			if (session.getAttribute("cartVo") != null) {
				cartVo = (CartVo) session.getAttribute("cartVo");// 拿到原来的购物车
			}

			int count = 1;
			if (Integer.parseInt(request.getParameter("quantity")) > 0) {
				count = Integer.parseInt(request.getParameter("quantity"));
			}

			int pid = Integer.parseInt(request.getParameter("entityId"));
			Product proudct = proudctService.getProductById(pid);
			if (count >= proudct.getStock()) {
				out.print("{ \"message\":\"库存不足,添加失败\", \"status\":0 }");

			} else {

				boolean isRepeat = false;

				// 查看是否有重复商品
				for (CartItemVo item : cartVo.getItems()) {
					if (pid == item.getProduct().getId()) {// 说明有重复 商品
						isRepeat = true;
						item.setCount(item.getCount() + count);
						item.setCost(item.getCount() * item.getProduct().getPrice());
						cartVo.setSum(cartVo.getSum() + count * item.getProduct().getPrice());// 计算总价
						break;

					}
				}

				if (!isRepeat) {
					CartItemVo cartItemVo = new CartItemVo();

					cartItemVo.setProduct(proudct);
					cartItemVo.setCount(count);
					cartItemVo.setCost(count * proudct.getPrice());

					cartVo.getItems().add(cartItemVo);
					cartVo.setSum(cartVo.getSum() + cartItemVo.getCost());// 计算总价
				}

				// 重新存入session中
				session.setAttribute("cartVo", cartVo);

				out.print("{ \"message\":\"添加成功11\", \"status\":1 }");
			}
			break;

		}

		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
