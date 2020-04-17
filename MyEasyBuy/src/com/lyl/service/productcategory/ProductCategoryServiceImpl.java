package com.lyl.service.productcategory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyl.dao.product.ProductDao;
import com.lyl.dao.product.ProductDaoImpl;
import com.lyl.dao.productcategory.ProductCategoryDao;
import com.lyl.dao.productcategory.ProductCategoryDaoImpl;
import com.lyl.entity.ProductCategory;
import com.lyl.utils.DBUtils;
import com.lyl.vo.ProductCategoryVo;
import com.sun.swing.internal.plaf.metal.resources.metal_zh_TW;

public class ProductCategoryServiceImpl implements ProductCategoryService {

	private ProductCategoryDao productCategoryDao;
	private ProductDao productDao;

	@Override
	public List<ProductCategoryVo> getAll() {

		Connection conn = DBUtils.getConn();
		productCategoryDao = new ProductCategoryDaoImpl(conn);
		productDao = new ProductDaoImpl(conn);
		// 所有商品
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", 1);
		params.put("parentId", 0);
		// 得到所有一级菜单
		List<ProductCategoryVo> pcCategories1 = productCategoryDao.getProductCategory(params);
		for (ProductCategoryVo vo1 : pcCategories1) {

			// 通过一级菜单获取二级菜单
			params.put("type", 2);
			params.put("parentId", vo1.getProductCategory().getId());
			List<ProductCategoryVo> pcCategories2 = productCategoryDao.getProductCategory(params);

			// 通过二级菜单获取三级菜单
			for (ProductCategoryVo vo2 : pcCategories2) {
				params.put("type", 3);
				params.put("parentId", vo2.getProductCategory().getId());
				vo2.setProductCategorys(productCategoryDao.getProductCategory(params));
			}

			params.put("categoryId", vo1.getProductCategory().getId());

			vo1.setProductCategorys(pcCategories2);
			try {
				vo1.setProducts(productDao.getProductList(params));// 查询所有商品
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		DBUtils.closeAll(conn, null, null);
		return pcCategories1;
	}

}
