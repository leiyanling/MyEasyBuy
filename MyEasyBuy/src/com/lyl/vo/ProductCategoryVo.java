package com.lyl.vo;

import java.util.List;

import com.lyl.entity.Product;
import com.lyl.entity.ProductCategory;

public class ProductCategoryVo {
	private ProductCategory productCategory;// 商品类别
	private List<ProductCategoryVo> productCategorys;// 商品类别子级
	private List<Product> products;// 此类别下面的所有商品

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<ProductCategoryVo> getProductCategorys() {
		return productCategorys;
	}

	public void setProductCategorys(List<ProductCategoryVo> productCategorys) {
		this.productCategorys = productCategorys;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
