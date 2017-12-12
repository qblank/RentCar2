package cn.qblank.entity;

import java.io.Serializable;

/**
 * 封装租车属性
 * @author Administrator
 *
 */
public class RentCarView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private int car_id;
	private String car_name;
	private double day_rent;		//租金
	private String car_comments;	//备注
	private String car_brand;		//车的品牌
	private String car_category;	//车的类型
	private String rent_time;		//租车时间
	private String return_time;		//还车时间
	private double rent;
	
	
	
	public double getRent() {
		return rent;
	}


	public void setRent(double rent) {
		this.rent = rent;
	}


	public RentCarView() {}
	
	
	public RentCarView(int id, String car_name, double day_rent,
			String car_comments, String car_brand, String car_category,
			String rent_time,String return_time) {
		super();
		this.id = id;
		this.car_name = car_name;
		this.day_rent = day_rent;
		this.car_comments = car_comments;
		this.car_brand = car_brand;
		this.car_category = car_category;
		this.rent_time = rent_time;
		this.return_time = return_time;
	}
	
	
	
	
	public int getCar_id() {
		return car_id;
	}


	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}


	public String getReturn_time() {
		return return_time;
	}


	public void setReturn_time(String return_time) {
		this.return_time = return_time;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public double getDay_rent() {
		return day_rent;
	}
	public void setDay_rent(double day_rent) {
		this.day_rent = day_rent;
	}
	public String getCar_comments() {
		return car_comments;
	}
	public void setCar_comments(String car_comments) {
		this.car_comments = car_comments;
	}
	public String getCar_brand() {
		return car_brand;
	}
	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}
	public String getCar_category() {
		return car_category;
	}
	public void setCar_category(String car_category) {
		this.car_category = car_category;
	}
	public String getRent_time() {
		return rent_time;
	}
	public void setRent_time(String rent_time) {
		this.rent_time = rent_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
