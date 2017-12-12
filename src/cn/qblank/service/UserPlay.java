package cn.qblank.service;

import java.io.BufferedReader;
import java.sql.Date;

import cn.qblank.View.LoginView;
import cn.qblank.View.PlayCarView;
import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.dao.Impl.LoginDaoImpl;
import cn.qblank.dao.Impl.RecordImpl;
import cn.qblank.entity.Car;
import cn.qblank.entity.CarBrand;
import cn.qblank.entity.CarCategory;
import cn.qblank.entity.RentRecord;
import cn.qblank.entity.User;
//import cn.qblank.util.Utils;
import cn.qblank.util.Utils;

public class UserPlay {
	private LoginView loginView = null;
	private PlayCarView playCarView = null;
	private CarImpl carImpl = null;
	private UserPlay userPlay = null;
	//����һ��״̬ ��Ϊtrueʱ���������У�����������
	static boolean sortStatus;
	private LoginDaoImpl loginDaoImpl;
	private RecordImpl recordImpl;
	
	
	static{
		//��ֵΪtrue ֻ����һ��
		sortStatus = true;
		
	}
	
	/**
	 * ����Աѡ����
	 * @param car
	 * @throws Exception
	 */
	public void userPlay(Car car,User user) throws Exception{
		
		//�½�һ����½��ͼ����
		loginView = new LoginView();
		//�½�һ��Car��ʵ�������
		carImpl = new CarImpl();
		//�½�һ��������ͼ����
		playCarView = new PlayCarView();
		//������ǰ�����
		userPlay = new UserPlay();
		
		loginDaoImpl = new LoginDaoImpl();
		
		//����һ�����޼�¼����
		RentRecord rentRecord = null;
		
		//��ȡ�û�����
		BufferedReader br = Login.scanInfo();
		String userSc = br.readLine();
		
		//���ڻ�ȡ+�����id
		int id = 0;
		if (userSc.length() > 2) {
			//��ȡ+�ź���ı��
			id = Integer.parseInt(userSc.substring(2));
		}
		
		if (userSc.equals("0")) {
			//�˳�
			Login.login();
		}else if (userSc.equals("1+"+id+"")) {
			//����һ��������
			recordImpl = new RecordImpl();
			
			//���������ı��
			car.setId(id);
			
			//����һ�����޼�¼����
			rentRecord = new RentRecord();
			if (car.getRent_status() == 0) {
				//��ʾ������
				
				//���ü�¼�е�ֵ
				rentRecord.setUserId(user.getId());
				rentRecord.setCar_id(car.getId());
				rentRecord.setStartDate(Utils.getRentTimes());
				rentRecord.setPayment(loginDaoImpl.getRentMoney(id));
				//���һ���⳵��¼
				carImpl.rentCar(id, user,rentRecord);
				System.out.println("�⳵�ɹ�");
				//��״̬���ò�����
				car.setRent_status(1);
				//�޸�״̬
				recordImpl.updateStatus(car, id);
				
				//��ӡ��Ϣͷ
				playCarView.showRentCarHeaderView();
				//������ĳ�����Ϣ��ӡ����
				playCarView.showRentCarView(id);
				
				//��ѯ����
			}else{
				//��ʾ������Ϣ
				playCarView.rentErrorView();
			}
			
		}else if (userSc.equals("2+"+id+"")) {
			//2+1 ���� 2+2����
			if (id == 1) {
				
				//����
				//����״ֵ̬Ϊtrue
				sortStatus = true;
			}else if (id == 2) {
				//����
				//����״ֵ̬Ϊtrue
				sortStatus = false;
			}
			
			
			
			
			
		}else if (userSc.equals("3+"+id+"")) {
			//�����������
			//����һ��CarCategory����
			CarCategory cate = new CarCategory();
			
			//�������ͺ�
			cate.setCategory_id(id);
			
			//ͨ�����ͱ�Ų��ҵ�sql���
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and"
					+ " useable <> 1 and c.category_id = ?";
			
			 
			//��½�Ľ���
			loginView.userLoginSuccessView();
			
			//��ʾ����
			playCarView.showUserMessageById(id, sql);
			
		}else if (userSc.equals("4+"+id+"")) {
			//��Ʒ�Ʊ������
			CarBrand carB = new CarBrand();
			
			//�������ͺ�
			carB.setBrand_id(id);
			
			//ͨ�����ͱ�Ų��ҵ�sql���
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and"
					+ " useable <> 1 and c.brand_id  = ?";
			 
			
			//��ʾ����
			playCarView.showMessageById(id, sql);
			
		}else if (userSc.equals("5")) {
			//�൱�ڷ������˵�
			
			//�鿴ȫ������
			/*loginView.userLoginSuccessView();
			loginView.showUserCarView();*/
			
		}else if (userSc.equals("6")) {
			//�鿴�ҵ��⳵��¼
			playCarView = new PlayCarView();
			
			//��ȡ����
			playCarView.printRentCarViewHeader();
			
			//��ȡ����
			playCarView.printRentCarView();
			
			
		}else if (userSc.equals("7+"+id+"")) {
			//����
			
			//���������
			
			//�½�һ������ʵ�������
			carImpl = new CarImpl();
			
			//��������
			recordImpl = new RecordImpl();
			
			//��ȡ����״̬ ������
			car.setRent_status(recordImpl.rentStatus(id));
			
			
			//����
//			System.out.println(car.getRent_status());
			
			//���˵Ĳ��ܻ�
			if (car.getRent_status() == 1) {
				//�ڼ�¼������ӹ黹��ʱ�����Ҫ�����ܽ��
				
				
				//��ȡ����ʱ��
				
				Date returnTimes = Utils.getReturnTimes();
				
				//��ȡ��� 1.�Ȳ�ѯ֮ǰ�Ľ賵ʱ��   2.������ʱ���ȥ�賵ʱ���ٳ������
				Date rentTimes = carImpl.queryRentTime(id);
				
				//��Źȥ�ܽ��
				double totalMoney = Utils.getTotalMoney(id, returnTimes, rentTimes,recordImpl);
				
				//����¼�������ݿ���
				recordImpl.returnCar(id, returnTimes, totalMoney);
				
				//����
//				System.out.println("�⳵ʱ��:"+rentTimes+"\n"+"����ʱ��:"+returnTimes);
//				System.out.println(returnTimes.getTime() - rentTimes.getTime());
//				System.out.println(dayRentMoney + "\t"+day);
				//�����������ݲ��뵽��¼����
				
				
				//��״̬���ÿ���
				car.setRent_status(0);
				
				//�����ݿ����޸�״̬
				recordImpl.updateStatus(car, id);
				
				
			}else if (car.getRent_status() == 0) {
				//û�⳵
				playCarView.returnErrorView();
			}
			
		}
		
		/**
		 * ����������
		 */
		
		//����Ա�����Ľ���
		loginView.userLoginSuccessView();
		
		//�ж�Ŀǰ��״̬
		if (sortStatus) {
			/*//����
			playCarView.showUserCarAscView();*/
			
			//����
			carImpl.orderByAsc();
			//����
			playCarView.showUserCarAscView();
		}else{
			/*//����
			playCarView.showUserCarDescView();*/
			
			//����
			carImpl.orderByDesc();
			//����
			playCarView.showUserCarDescView();
		}
		//�û������Ľ���
		loginView.userPlayView();
		
		//����Ա�������
		userPlay.userPlay(car,user);
		
	}

}
