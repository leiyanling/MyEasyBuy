package com.lyl.dao.product;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.lyl.entity.Product;

public interface ProductDao {

	public List<Product> getProductList(Map<String, Object> params) throws Exception;
	public Product tableToClass(ResultSet rs) throws Exception;

	public Product getProductById(Integer id) throws Exception;

}
