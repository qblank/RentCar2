package cn.qblank.entity;

import java.io.Serializable;

public class CarBrand implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Æ·ÅÆ±àºÅ
	private int brand_id;
	
	//Æ·ÅÆÃû×Ö
	private String brand_name;
	

	public int getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	
	
	
}
