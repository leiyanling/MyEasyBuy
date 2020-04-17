package com.lyl.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车类
 * 
 * @author 小仙女
 *
 */
public class CartVo {// 购物车
	private List<CartItemVo> items = new ArrayList<CartItemVo>();
	private double sum;// 整个购物车的总计

	public List<CartItemVo> getItems() {
		return items;
	}

	public void setItems(List<CartItemVo> items) {
		this.items = items;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

}
