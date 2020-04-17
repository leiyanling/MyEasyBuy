package com.lyl.service.product;

import java.sql.Connection;

import com.lyl.dao.product.ProductDao;
import com.lyl.dao.product.ProductDaoImpl;
import com.lyl.entity.Product;
import com.lyl.utils.DBUtils;

public class ProductServiceImpl implements ProductService {
	private ProductDao productDao;

	@Override
	public Product getProductById(Integer id) {
		Connection conn = DBUtils.getConn();
		productDao = new ProductDaoImpl(conn);
		Product product = null;
		try {
			product = productDao.getProductById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DBUtils.closeAll(conn, null, null);

		return product;
	}

}
