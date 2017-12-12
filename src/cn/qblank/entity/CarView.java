package cn.qblank.entity;

import java.io.Serializable;
/**
 * 封装显示的数据 (用户看到的数据)
 * @author Administrator
 *
 */
public class CarView implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String model;
	private String comments;
	private String brandName;
	private String typeName;
	private double price;
	private double rent;
	private int status;
	
	
	
	
	public CarView() {}
	
	
	
	
	public CarView(int id, String model, String comments, String brandName,
			String typeName, double price, double rent, int status) {
		super();
		this.id = id;
		this.model = model;
		this.comments = comments;
		this.brandName = brandName;
		this.typeName = typeName;
		this.price = price;
		this.rent = rent;
		this.status = status;
	}




	public double getRent() {
		return rent;
	}
	
	public void setRent(double rent) {
		this.rent = rent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	
}
