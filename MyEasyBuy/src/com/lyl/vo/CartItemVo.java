package com.lyl.vo;

import com.lyl.entity.Product;

public class CartItemVo {// 购物车里面的每一个商品
	private Product product; // 商品
	private int count;// 数量
	private double cost;// 单个商品的 小计

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
