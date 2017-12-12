package cn.qblank.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.entity.Car;
import cn.qblank.entity.CarBrand;
import cn.qblank.entity.CarCategory;
import cn.qblank.entity.CarView;
import cn.qblank.entity.ManagerCarView;
import cn.qblank.entity.RentCarView;

/**
 * ���������Ľ���
 * @author Administrator
 *
 */
public class PlayCarView {
	CarImpl carImpl = null;
	PlayCarView playCarView = null;
	private LoginView loginView;
	
	/**
	 * ��������Ľ���
	 */
	public void addCarView(){
		System.out.println("====================");
		System.out.println("��ֱ�����һ����Ϣ:");
		System.out.println("----------------");
		System.out.println("Ʒ������:");
		
		
	}
	
	
	
	/**
	 * ��ʾ�������
	 */
	public void showCarCategoryView(){
		//����һ��CarImpl����
		carImpl = new CarImpl();
		
		List<CarCategory> category =  null;
		
		//����Ʒ�Ƶ���Ϣ
		category = carImpl.queryCarCategory();
		
		//����
		for (CarCategory carCategory : category) {
			System.out.println(carCategory.getCategory_id() +"\t" 
					+carCategory.getCategory_name());
		}
		
	
	}
	
	/**
	 * ������ʾ�������� 
	 */
	public void showCarByAsc(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		
		List<ManagerCarView> orderAscList = null;
		
		orderAscList = carImpl.orderByAsc();
		// ������������Ա��������Ϣ��ʾ����
		for (ManagerCarView managerCarView : orderAscList) {
			System.out.println(managerCarView.getId() + "\t"
					+ managerCarView.getModel() + "\t"
					+ managerCarView.getComments() + "\t"
					+ managerCarView.getBrandName() + "\t"
					+ managerCarView.getTypeName() + "\t"
					+ managerCarView.getPrice() + "\t"
					+ managerCarView.getRent() + "\t"
					+ loginView.getStatus(managerCarView.getStatus()) + "\t"
					+ loginView.getUseable(managerCarView.getUseable()));
			
		}
		
		
	}
	
	
	
	
	/**
	 * ������ʾ��������
	 */
	public void showCarByDesc(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		List<ManagerCarView> orderDescList = null;
		
		orderDescList = carImpl.orderByDesc();
		
		// ������������Ա��������Ϣ��ʾ����
		for (ManagerCarView managerCarView : orderDescList) {
			System.out.println(managerCarView.getId() + "\t"
					+ managerCarView.getModel() + "\t"
					+ managerCarView.getComments() + "\t"
					+ managerCarView.getBrandName() + "\t"
					+ managerCarView.getTypeName() + "\t"
					+ managerCarView.getPrice() + "\t"
					+ managerCarView.getRent() + "\t"
					+ loginView.getStatus(managerCarView.getStatus()) + "\t"
					+ loginView.getUseable(managerCarView.getUseable()));
			
		}
		
	}
	
	
	/**
	 * �û�������ʾ��������
	 */
	public void showUserCarAscView(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		List<CarView> orderAscList = null;
		
		orderAscList = carImpl.orderAsc();
		
		// ������������Ա��������Ϣ��ʾ����
		for (CarView managerCarView : orderAscList) {
			System.out.println(managerCarView.getId() + "\t"
					+ managerCarView.getModel() + "\t"
					+ managerCarView.getComments() + "\t"
					+ managerCarView.getBrandName() + "\t"
					+ managerCarView.getTypeName() + "\t"
					+ managerCarView.getPrice() + "\t"
					+ managerCarView.getRent() + "\t"
					+ loginView.getStatus(managerCarView.getStatus()));
			
		}
		
	}
	
	
	/**
	 * �û�������ʾ��������
	 */
	public void showUserCarDescView(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		List<CarView> orderDescList = null;
		
		orderDescList = carImpl.orderDesc();
		
		// ������������Ա��������Ϣ��ʾ����
		for (CarView carView : orderDescList) {
			System.out.println(carView.getId() + "\t"
					+ carView.getModel() + "\t"
					+ carView.getComments() + "\t"
					+ carView.getBrandName() + "\t"
					+ carView.getTypeName() + "\t"
					+ carView.getPrice() + "\t"
					+ carView.getRent() + "\t"
					+ loginView.getStatus(carView.getStatus()));
			
		}
	}
	
	
	/**
	 * ��ʾ����Ʒ�ƵĽ���
	 */
	public void showCarBrand(){
		
		List<CarBrand> brand = null;
		
		//�������͵���Ϣ
		brand = carImpl.queryCarBrand();
		
		//����
		for (CarBrand carBrand : brand) {
			System.out.println(carBrand.getBrand_id() + "\t"
					+ carBrand.getBrand_name());
		}
		
		
	}
	
	
	
	
	
	/**
	 * ����Ա�����û���Ϣ
	 * @param car
	 * @param br
	 * @throws IOException
	 */
	public void scanfCarInfo(Car car, BufferedReader br) throws IOException {
		playCarView = new PlayCarView();
		
		//ѡ��Ʒ�Ʊ��
		System.out.println("Ʒ�Ʊ��\tƷ����");
		//��ѯƷ�Ʊ�
		playCarView.showCarCategoryView();
		
		System.out.println("��ѡ��Ʒ�Ʊ��:");
		int cate_id = Integer.parseInt(br.readLine());
		car.setCar_category_id(cate_id);
		
		
		//����
		System.out.println("��������:");
		System.out.println("���ͱ��\t������");
		//��ѯ���ͱ�
		playCarView.showCarBrand();
		System.out.println("��ѡ�����ͱ��:");
		int brand_id = Integer.parseInt(br.readLine());
		car.setCar_brand_id(brand_id);
		
		
		System.out.println("----------------");
		System.out.println("�ͺ�:");
		String model = br.readLine();
		car.setCar_model(model);
		
		System.out.println("----------------");
		System.out.println("���ƺ�:");
		String car_number = br.readLine();
		car.setCar_number(car_number);
		
		System.out.println("----------------");
		System.out.println("��Ҫ:");
		String comments = br.readLine();
		car.setCar_comments(comments);
		
		System.out.println("----------------");
		System.out.println("��ɫ:");
		String color = br.readLine();
		car.setCar_color(color);
		
		System.out.println("----------------");
		System.out.println("�����۸�:");
		double price = Double.parseDouble(br.readLine());
		car.setCar_price(price);
		
		System.out.println("----------------");
		System.out.println("ÿ�����:");
		double rent = Double.parseDouble(br.readLine());
		car.setCar_rent(rent);
		
		System.out.println("----------------");
		System.out.println("�Ƿ�ɽ�(0:�ɽ�   1:���ɽ�):");
		int status = Integer.parseInt(br.readLine());
		car.setRent_status(status);
		
		System.out.println("----------------");
		System.out.println("�Ƿ��ϼ�(0:�ϼ�    1:�¼�):");
		int useable = Integer.parseInt(br.readLine());
		car.setUseable_status(useable);
	}
	
	
	/**
	 * ��ͨ��id������Ϣ��������ʾ����
	 */
	public void showMessageById(int id,String sql){
		//����һ��CarImpl����
		carImpl = new CarImpl();
		//����LoginView����
		loginView = new LoginView();
		
		
		List<ManagerCarView> list = carImpl.findById(id, sql);
		
		for (ManagerCarView managerCarView : list) {
			
			//��ӡ����
			System.out.println(managerCarView.getId() + "\t"
					+ managerCarView.getModel() + "\t"
					+ managerCarView.getComments() + "\t"
					+ managerCarView.getBrandName() + "\t"
					+ managerCarView.getTypeName() + "\t"
					+ managerCarView.getPrice() + "\t"
					+ managerCarView.getRent() + "\t"
					+ loginView.getStatus(managerCarView.getStatus()) + "\t"
					+ loginView.getUseable(managerCarView.getUseable()));
		}
		
	}
	
	
	/**
	 * ��ͨ��id������Ϣ��������ʾ����
	 */
	public void showUserMessageById(int id,String sql){
		//����һ��CarImpl����
		carImpl = new CarImpl();
		//����LoginView����
		loginView = new LoginView();
		
		
		List<CarView> list = carImpl.userFindById(id, sql);
		
		for (CarView carView : list) {
			
			//��ӡ����
			System.out.println(carView.getId() + "\t"
					+ carView.getModel() + "\t"
					+ carView.getComments() + "\t"
					+ carView.getBrandName() + "\t"
					+ carView.getTypeName() + "\t"
					+ carView.getPrice() + "\t"
					+ carView.getRent() + "\t"
					+ loginView.getStatus(carView.getStatus()));
		}
		
	}
	
	
	/**
	 * ����Ա�޸�
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public int updateCar(BufferedReader br,Car car) throws IOException{
		System.out.println("������Ҫ�޸ĵ����ݱ��:");
		System.out.println("1.���޼۸�    2.�ϼ��¼�");
		
		int managerSc = Integer.parseInt(br.readLine());
		
		//���޼۸�
		if (managerSc == 1) {
			//�������µ����޼۸�
			System.out.println("�������µ����޼۸�:");
			double price = Double.parseDouble(br.readLine());
			car.setCar_price(price);
			
			
		}else if (managerSc == 2) {
			System.out.println("1.�ϼ�   2.�¼�");
			int sel = Integer.parseInt(br.readLine());
			if (sel == 1) {
				//�ϼ�
				car.setUseable_status(0);
				
			}else if (sel == 2) {
				//�¼�
				car.setUseable_status(1);
			}
			
		}
		
		return managerSc;
		
	}
	
	
	/**
	 * �û������޲������޵ĳ�������·����Ĵ���
	 */
	public void rentErrorView(){
		System.out.println("�ó��Ѿ���������...");
	}
	
	
	/**
	 * �⳵��Ϣͷ
	 */
	public void showRentCarHeaderView(){
		System.out.println("���\t��������\tÿ�����\t��ע\tƷ��\t����\t�賵ʱ��");
		
	}
	
	/**
	 * ��ӡ�⳵����Ϣ
	 */
	public void showRentCarView(int id){
		System.out.println("�⳵�ɹ����⳵��Ϣ����:");
		carImpl = new CarImpl();
		
		//�����������ڽ�������
		RentCarView rentCarView = new RentCarView();
		
		rentCarView = carImpl.rentCar(id);
		
		//��ӡ��Ϣ
		System.out.println(rentCarView.getId()+"\t"+rentCarView.getCar_name()
				+"\t"+ rentCarView.getDay_rent()+"\t"+rentCarView.getCar_comments()
				+"\t"+ rentCarView.getCar_brand()+"\t"+rentCarView.getCar_category()
				+"\t"+ rentCarView.getRent_time());
		
		
	}
	
	/**
	 * ���⳵��¼������ӡ����
	 */
	public void printRentCarViewHeader(){
		for (int i = 0; i < 20; i++) {
			System.out.print("=");
		}
		System.out.println();
		
		System.out.println("���\t�������\t��������\t����ܶ�\t��ע\tƷ��\t����\t�賵ʱ��\t\t\t����ʱ��");
	}
	
	/**
	 * ���⳵��¼��ӡ����
	 */
	public void printRentCarView(){
		carImpl = new CarImpl();
		
		List<RentCarView> list = carImpl.findAllRecord();
		
		//����
		for (RentCarView rentCarView : list) {
			
			System.out.println(rentCarView.getId()+"\t"+rentCarView.getCar_id()
					+"\t"+rentCarView.getCar_name()+"\t"+rentCarView.getDay_rent()
					+"\t"+rentCarView.getCar_comments()+"\t"+rentCarView.getCar_brand()
					+"\t"+rentCarView.getCar_category()+"\t"+rentCarView.getRent_time()
					+"\t"+rentCarView.getReturn_time());
		}
		
	}
	
	
	/**
	 * û�⳵ȴҪ�����Ĵ���
	 */
	public void returnErrorView(){
		System.out.println("���꣬��û������������");
	}
	
}
