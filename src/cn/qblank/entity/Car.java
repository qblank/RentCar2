package cn.qblank.entity;

import java.io.Serializable;
/**
 * 封装所有汽车的属性
 * @author Administrator
 *
 */
public class Car implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	
	//汽车车牌
	private String car_number;
	
	//品牌编号
	private int car_brand_id;
	//汽车模子
	private String car_model;
	
	//汽车颜色
	private String car_color;
	
	//汽车种类编号
	private int car_category_id;

	//汽车说明
	private String car_comments;
	
	//价格
	private double car_price;
	
	//租金
	private double car_rent;
	
	//租赁状态
	private int rent_status;
	//是否可用
	private int useable_status;
	
	
	
	
	
	public Car() {}
	
	public Car(String car_number, int car_brand_id, String car_model,
			String car_color, int car_category_id, String car_comments,
			double car_price, double car_rent, int rent_status,
			int useable_status) {
		super();
		this.car_number = car_number;
		this.car_brand_id = car_brand_id;
		this.car_model = car_model;
		this.car_color = car_color;
		this.car_category_id = car_category_id;
		this.car_comments = car_comments;
		this.car_price = car_price;
		this.car_rent = car_rent;
		this.rent_status = rent_status;
		this.useable_status = useable_status;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCar_number() {
		return car_number;
	}
	public void setCar_number(String car_number) {
		this.car_number = car_number;
	}
	public int getCar_brand_id() {
		return car_brand_id;
	}
	public void setCar_brand_id(int car_brand_id) {
		this.car_brand_id = car_brand_id;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getCar_color() {
		return car_color;
	}
	public void setCar_color(String car_color) {
		this.car_color = car_color;
	}
	public int getCar_category_id() {
		return car_category_id;
	}
	public void setCar_category_id(int car_category_id) {
		this.car_category_id = car_category_id;
	}
	public String getCar_comments() {
		return car_comments;
	}
	public void setCar_comments(String car_comments) {
		this.car_comments = car_comments;
	}
	public double getCar_price() {
		return car_price;
	}
	public void setCar_price(double car_price) {
		this.car_price = car_price;
	}
	public double getCar_rent() {
		return car_rent;
	}
	public void setCar_rent(double car_rent) {
		this.car_rent = car_rent;
	}
	public int getRent_status() {
		return rent_status;
	}
	public void setRent_status(int rent_status) {
		this.rent_status = rent_status;
	}
	public int getUseable_status() {
		return useable_status;
	}
	public void setUseable_status(int useable_status) {
		this.useable_status = useable_status;
	}
	@Override
	public String toString() {
		return "Car [car_number=" + car_number + ", car_brand_id="
				+ car_brand_id + ", car_model=" + car_model + ", car_color="
				+ car_color + ", car_category_id=" + car_category_id
				+ ", car_comments=" + car_comments + ", car_price=" + car_price
				+ ", car_rent=" + car_rent + ", rent_status=" + rent_status
				+ ", useable_status=" + useable_status + "]";
	}
	
	
	
	
}
