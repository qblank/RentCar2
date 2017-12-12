package cn.qblank.dao;

import java.sql.Date;

import cn.qblank.entity.Car;


public interface IRecord {
	/**
	 * ��ȡ�û�������״̬
	 */
	public int rentStatus(int id);
	
	/**
	 * �޸ļ�¼���е�����
	 */
	public void returnCar(int id,Date returnTime,double money);
	
	/**
	 * ��ѯ������ÿ�����
	 * @return
	 */
	public double queryCarMoney(int id);
	
	/**
	 * �޸�����״̬
	 */
	public void updateStatus(Car car,int id);
	
}
