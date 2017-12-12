package cn.qblank.service;

import java.io.BufferedReader;
import java.io.IOException;

import cn.qblank.View.LoginView;
import cn.qblank.View.PlayCarView;
import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.entity.Car;
import cn.qblank.entity.CarBrand;
import cn.qblank.entity.CarCategory;
import cn.qblank.entity.ManagerCarView;

/**
 * �û�������
 * @author Administrator
 *
 */
public class ManagerPlay {
	private static LoginView loginView = null;
	private PlayCarView playCarView = null;
	private CarImpl carImpl = null;
	private ManagerPlay managerPlay = null;
	
	//Ĭ��Ϊ��������
//	private boolean sortStatus = true;
	
	/**
	 * ����Աѡ����
	 * @throws IOException 
	 */
	public void managerPlay(Car car) throws Exception{
		//�½�һ����½��ͼ����
		loginView = new LoginView();
		//�½�һ��Car��ʵ�������
		carImpl = new CarImpl();
		//�½�һ��������ͼ����
		playCarView = new PlayCarView();
		//������ǰ�����
		managerPlay = new ManagerPlay();
		
		//��ȡ�û�����
		BufferedReader br = Login.scanInfo();
		String managerSc = br.readLine();
		
		//���ڻ�ȡ+�����id
		int id = 0;
		if (managerSc.length() > 2) {
			//��ȡ+�ź���ı��
			id = Integer.parseInt(managerSc.substring(2));
		}
		//����
//		System.out.println(id);
		
		if (managerSc.equals("0")) {
			//�˳�
			Login.login();
			
		}else if (managerSc.equals("1+"+id+"")) {
			//�鿴�������
			//����ManagerCarView����
			ManagerCarView managerCarView = new ManagerCarView();
			//����id
			managerCarView.setId(id);
			//ͨ��������Ų���������sql���
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.id = ?";
			
			//��½�Ľ���
			loginView.managerLoginSuccessView();
			
			//��ʾ����
			playCarView.showMessageById(id, sql);
			
			//����������
			returnMain(car,id);
			
			
			
		}else if(managerSc.equals("5")){
			/**
			 * �鿴ȫ������
			 */
			loginView.managerLoginSuccessView();
			//��ʾ����
			loginView.showManagerCarView();
			//����Ա�����Ľ���
			loginView.managerPlayView();
			//����Ա�������
			managerPlay.managerPlay(car);
			
			
		}else if (managerSc.equals("6")) {
			//�������
			
			//������������Ľ���
			playCarView.addCarView();
			
			//����Ա�����û���Ϣ
			playCarView.scanfCarInfo(car, br);
			
			
			//�������
			carImpl.addCar(car);
			
			/**
			 * ����������
			 */
			//��½�Ľ���
			loginView.managerLoginSuccessView();
			loginView.showManagerCarView();
			//����Ա�����Ľ���
			loginView.managerPlayView();
			//����Ա�������
			managerPlay.managerPlay(car);
			
		}else if (managerSc.equals("2+"+id+"")) {
			//����
			
			if (id == 1) {
				//��������
				carImpl.orderByAsc();
				/**
				 * ����������
				 */
				//��½�Ľ���
				loginView.managerLoginSuccessView();
				//����
				playCarView.showCarByAsc();
				
				//����Ա�����Ľ���
				loginView.managerPlayView();
				//����Ա�������
				managerPlay.managerPlay(car);
				
			}else if (id == 2) {
				//��������
				carImpl.orderByDesc();
				
				/**
				 * ����������
				 */
				returnMain(car,2);
			}
			
			
			
			
		}else if (managerSc.equals("3+"+id+"")) {
			//����������
			//����һ��CarCategory����
			CarCategory cate = new CarCategory();
			
			//�������ͺ�
			cate.setCategory_id(id);
			
			//ͨ�����ͱ�Ų��ҵ�sql���
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.category_id = ?";
			
			
			//��½�Ľ���
			loginView.managerLoginSuccessView();
			
			//��ʾ����
			playCarView.showMessageById(id, sql);
			
			//����������
			returnMain(car,id);
			
			
		}else if (managerSc.equals("4+"+id+"")) {
			//��Ʒ������
			//����һ��CarBrand����
			CarBrand carBrand = new CarBrand();
			
			//����Ʒ�ƺ�
			carBrand.setBrand_id(id);
			
			//ͨ�����ͱ�Ų��ҵ�sql���
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.brand_id = ?";
			
			//��½�Ľ���
			loginView.managerLoginSuccessView();
			
			//��ʾ����
			playCarView.showMessageById(id, sql);
			
			//����������
			returnMain(car,id);
			
			
		}else if (managerSc.equals("7+"+id+"")) {
			
			//׼����ѯ���
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.id = ?";
			
			//��½�Ľ���
			loginView.managerLoginSuccessView();
			
			//��ʾ����
			playCarView.showMessageById(id, sql);
			
			//�޸���������ͼ
			int updateSel = playCarView.updateCar(br, car);
			
			//�޸Ķ�Ӧ��ŵ�����
			carImpl.updateCar(id, car,updateSel);
			
			
			//����������
			returnMain(car,id);
			
			
			
			
		}else if (managerSc.equals("8")) {
			//��ѯ�⳵��¼
			
			playCarView = new PlayCarView();
			
			//��ȡ����
			playCarView.printRentCarViewHeader();
			
			//��ȡ����
			playCarView.printRentCarView();
			
			//�������˵�
			returnMain(car, id);
			
		}
		
		
	}

	/**
	 * ����������
	 * @param car
	 * @throws Exception
	 */
	private void returnMain(Car car,int id) throws Exception {
		//��½�Ľ���
		loginView.managerLoginSuccessView();
		//����
		playCarView.showCarByDesc();
		//����Ա�����Ľ���
		loginView.managerPlayView();
		//����Ա�������
		managerPlay.managerPlay(car);
	}

	
	
	
	
	
	
	
	
	
}
