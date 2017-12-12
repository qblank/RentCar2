package cn.qblank.dao;

import java.sql.Date;

import cn.qblank.entity.Car;


public interface IRecord {
	/**
	 * 获取用户的租赁状态
	 */
	public int rentStatus(int id);
	
	/**
	 * 修改记录表中的数据
	 */
	public void returnCar(int id,Date returnTime,double money);
	
	/**
	 * 查询汽车的每日租金
	 * @return
	 */
	public double queryCarMoney(int id);
	
	/**
	 * 修改租赁状态
	 */
	public void updateStatus(Car car,int id);
	
}
