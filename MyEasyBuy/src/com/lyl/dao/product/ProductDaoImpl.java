package com.lyl.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lyl.dao.BaseDao;
import com.lyl.entity.Product;
import com.lyl.utils.DBUtils;
import com.lyl.utils.EmptyUtils;

public class ProductDaoImpl extends BaseDao implements ProductDao {

	public ProductDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public Product tableToClass(ResultSet rs) throws Exception {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getFloat("price"));
		product.setStock(rs.getInt("stock"));
		product.setCategoryLevel1Id(rs.getInt("categoryLevel1Id"));
		product.setCategoryLevel2Id(rs.getInt("categoryLevel2Id"));
		product.setCategoryLevel3Id(rs.getInt("categoryLevel3Id"));
		product.setFileName(rs.getString("fileName"));
		return product;
	}

	@Override
	public Product getProductById(Integer id) throws Exception {
		String sql = " select id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete from easybuy_product where id = ? ";
		ResultSet resultSet = null;
		Product product = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				product = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(null, ps, resultSet);
			return product;
		}
	}

	@Override
	public List<Product> getProductList(Map<String, Object> params) {
		List<Object> paramsList = new ArrayList<Object>();
		List<Product> productList = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer(
				"  select id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete from easybuy_product  where 1=1 ");
		ResultSet resultSet = null;
		try {
			if (EmptyUtils.isNotEmpty(params.get("proName"))) {
				sql.append(" and name like ? ");
				paramsList.add("%" + params.get("proName") + "%");
			}

			if (EmptyUtils.isNotEmpty(params.get("categoryId"))) {
				sql.append(" and (categoryLevel1Id = ? or categoryLevel2Id=? or categoryLevel3Id=? ) ");
				paramsList.add(params.get("categoryId"));
				paramsList.add(params.get("categoryId"));
				paramsList.add(params.get("categoryId"));
			}
			resultSet = this.executeQuery(sql.toString(), paramsList.toArray());
			while (resultSet.next()) {
				Product product = this.tableToClass(resultSet);
				productList.add(product);
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
