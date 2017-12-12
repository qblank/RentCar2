package cn.qblank.dao;

import java.sql.Date;
import java.util.List;

import cn.qblank.entity.Car;
import cn.qblank.entity.CarBrand;
import cn.qblank.entity.CarCategory;
import cn.qblank.entity.CarView;
import cn.qblank.entity.ManagerCarView;
import cn.qblank.entity.RentCarView;
import cn.qblank.entity.RentRecord;
import cn.qblank.entity.User;

public interface ICar {
	
	/**
	 * ��ѯ�����ϼܵĳ���
	 */
	public List<CarView> findAllPost();
	
	/**
	 * ��ѯ���г���(����δ�ϼܵ�)
	 */
	public List<ManagerCarView> findAllCar();
	
	/**
	 * ͨ����Ų��ҳ���
	 */
	public List<ManagerCarView> findById(int id,String sql);
	
	/**
	 * �������
	 */
	public void addCar(Car car);
	
	/**
	 * ��ѯ�������
	 * @return
	 */
	public List<CarCategory> queryCarCategory();
	
	/**
	 * ��ѯ����Ʒ��
	 * @return
	 */
	public List<CarBrand> queryCarBrand();
	
	/**
	 * �������� ����Ա
	 * id���ڷֱ����û���˵����Ա
	 */
	public List<ManagerCarView> orderByAsc();
	
	/**
	 * �������� ����Ա
	 * id���ڷֱ����û���˵����Ա
	 */
	public List<ManagerCarView> orderByDesc();
	
	/**
	 * �޸�����
	 * updateSel ѡ���޸�  1.�۸�   2.�ϼ��¼� 
	 */
	public void updateCar(int id,Car car,int updateSel);
	
	/**
	 * ��������  �û�
	 * @return
	 */
	public List<CarView> orderAsc();
	/**
	 * ��������  �û�
	 * @return
	 */
	public List<CarView> orderDesc();
	
	/**
	 * ͨ��id����ֵ
	 * @param id
	 * @param sql
	 * @return
	 */
	public List<CarView> userFindById(int id,String sql);
	
	/**
	 * �賵��¼
	 */
	public void rentCar(int id,User user,RentRecord rentRecord);
	
	/**
	 * �����賵����Ϣ��ӡ����
	 * @return
	 */
	public RentCarView rentCar(int id);
	
	/**
	 * ��ѯ�⳵��¼
	 * @return
	 */
	public List<RentCarView> findAllRecord();
	
	/**
	 * �޸�״̬
	 */
//	public void updateStatus(Car car,int id);
	
	/**
	 * ��ѯ����������ʱ��
	 */
	public Date queryRentTime(int id);
	
}
