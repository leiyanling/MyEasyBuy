package com.lyl.dao.productcategory;

import java.util.List;
import java.util.Map;

import com.lyl.vo.ProductCategoryVo;

public interface ProductCategoryDao {

	List<ProductCategoryVo> getProductCategory(Map<String, Object> params);

}
