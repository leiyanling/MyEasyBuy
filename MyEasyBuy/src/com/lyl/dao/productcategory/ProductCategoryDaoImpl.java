package com.lyl.dao.productcategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lyl.dao.BaseDao;
import com.lyl.entity.Product;
import com.lyl.entity.ProductCategory;
import com.lyl.utils.DBUtils;
import com.lyl.utils.EmptyUtils;
import com.lyl.vo.ProductCategoryVo;

public class ProductCategoryDaoImpl extends BaseDao implements ProductCategoryDao {

	public ProductCategoryDaoImpl(Connection conn) {
		super(conn);
	}

	public ProductCategory tableToClass(ResultSet rs) throws Exception {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(rs.getInt("id"));
		productCategory.setName(rs.getString("name"));
		productCategory.setParentId(rs.getInt("parentId"));
		productCategory.setType(rs.getInt("type"));
		productCategory.setIconClass(rs.getString("iconClass"));
		return productCategory;
	}

	@Override
	public List<ProductCategoryVo> getProductCategory(Map<String, Object> params) {
		List<Object> paramsList = new ArrayList<Object>();
		List<ProductCategoryVo> productList = new ArrayList<ProductCategoryVo>();
		StringBuffer sql = new StringBuffer(
				" SELECT id ,name,parentId,type,iconClass  FROM  easybuy_product_category  WHERE 1=1 ");
		ResultSet resultSet = null;
		try {

			if (EmptyUtils.isNotEmpty(params.get("parentId"))) {
				sql.append(" and parentId = ? ");
				paramsList.add(params.get("parentId"));
			}
			if (EmptyUtils.isNotEmpty(params.get("type"))) {
				sql.append(" and type = ? ");
				paramsList.add(params.get("type"));
			}

			resultSet = this.executeQuery(sql.toString(), paramsList.toArray());
			while (resultSet.next()) {
				ProductCategory productCategory = this.tableToClass(resultSet);
				ProductCategoryVo productCategoryVo= new ProductCategoryVo();
				productCategoryVo.setProductCategory(productCategory);
				productList.add(productCategoryVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, ps, resultSet);
		}
		return productList;
	}
	
	

}
