package cn.qblank.entity;

import java.sql.Date;

public class RentRecord {
	private int id;
	private int userId;			//�û����
	private int car_id;			//�������
	private Date startDate;		//�⳵��ʱ��
	private Date returnDate;	//�黹��ʱ��
	private double payment;		//�ܽ��
	
	
	
	
	public RentRecord() {}
	
	public RentRecord(int id, int userId, int car_id, Date startDate,
			Date returnDate, double payment) {
		super();
		this.id = id;
		this.userId = userId;
		this.car_id = car_id;
		this.startDate = startDate;
		this.returnDate = returnDate;
		this.payment = payment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	
	
	
}
