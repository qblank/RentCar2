package cn.qblank.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cn.qblank.View.LoginView;
import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.dao.Impl.LoginDaoImpl;
import cn.qblank.entity.Car;
import cn.qblank.entity.User;
import cn.qblank.main.Cient;

public class UserLogin {
	//�����û��Ľ�ɫΪ0
	private static final int USER = 0;
	
static LoginDaoImpl loginDaoImpl = null;
	
	static CarImpl carImpl = null;
	
	static LoginView loginView = null;
	
	static ManagerPlay managerPlay = null;
	
	static UserPlay userPlay = null;
	
	
	static{
		//��������
		loginDaoImpl = new LoginDaoImpl();
		carImpl = new CarImpl();
		
		//������ͼ�����
		loginView = new LoginView();
	}
	
	/**
	 * �û���½
	 * @throws Exception 
	 */
	public static void userLogin(LoginView loginView,UserPlay userPlay,LoginDaoImpl loginDaoImpl) throws Exception{
		//�û���ҳ��
		loginView.userMainView();
		//����һ����������
		Car car = new Car();
		
		//��׼����
		BufferedReader br = Login.scanInfo();
		int sc = 0;
		sc = Integer.parseInt(br.readLine());
			
		//����һ���û�����
		User users = new User();
			
			switch (sc) {
			case 1:
				//����һ��UserPlay����
				userPlay = new UserPlay();
				
				//�û���½ҳ��
				loginView.userLoginView();
				
				System.out.println("�û���:");
				String user = br.readLine();
				System.out.println("����:");
				String pwd = br.readLine();
				
				//�����˺������ֵ �����û�����
				users.setUserName(user);
				users.setPassword(pwd);
				//��������
				users.setType(USER);
				//�����û�id
				users.setId(loginDaoImpl.getUserId(users));
				//����daoʵ�����е�testLogin����
//				boolean flag = loginDaoImpl.testLogin(users);
				boolean flag = Cient.readMessage(users);
				if (flag) {
					//��½�ɹ�
					System.out.println("�û���½�ɹ�");
					//��ʾ��½�ɹ�ҳ��
					loginView.userLoginSuccessView();
					loginView.showUserCarView();
					//�û������Ĺ��ܽ���
					loginView.userPlayView();
					//
					userPlay.userPlay(car,users);
				}else{
					//��½ʧ��ҳ��
					loginView.loginErrorView();
					// ����ȷ���ص�½ҳ��
					UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
				}
				break;
			
			case 2:
				System.out.println("�����û�ע��ҳ��");
				UserLogin.userRegister(users);
				break;
				
			case 3:
				System.out.println("�˳�");
				Login.login();
				
				break;
			default:
				System.out.println("����������");
				//����
				UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
				
				break;
			}
	}
	
	
	/**
	 * �û�ע��
	 * @throws Exception 
	 */
	public static void userRegister(User users) throws Exception{
		BufferedReader br = scanInfo();
		
		//�û�ע��ҳ��
		loginView.userRegisterView();
		
		System.out.println("�û���:");
		String userName = br.readLine();
		
		System.out.println("����:");
		String password = br.readLine();
		
		System.out.println("ȷ������:");
		String conPsd = br.readLine();
		
		System.out.println("��ѡ���Ա�:0.��\t1.Ů");
		int sex = Integer.parseInt(br.readLine());
		@SuppressWarnings("unused")
		String gender = null;
		switch (sex) {
		case 0:
			gender = "��";
			break;
		case 1:
			gender = "Ů";
			break;
		default:
			System.out.println("����������");
			sex = 0;
			gender = "��";
			break;
		}
		System.out.println("���֤��:");
		String id_number = br.readLine();
		
		System.out.println("�绰:");
		String tel = br.readLine();
		
		System.out.println("��ַ:");
		String addr = br.readLine();
		
		//�ж���������������Ƿ�һ��
		 if (password.equals(conPsd)) {
			 users.setUserName(userName);
			 users.setPassword(password);
			//�ж����ݿ����Ƿ��и��û�
			 if (userName.equals(loginDaoImpl.getUser(users))) {
				//���и��û�
				 System.out.println("���û��Ѿ�����,����������");
				 userRegister(users);
			 }else{
				 //�����û���ֵ
				 users.setSex(sex);
				 users.setId_number(id_number);
				 users.setTel(tel);
				 users.setAddr(addr);
				 users.setType(0);
				 
				 //���û������������
				 loginDaoImpl.registerUser(users);
				 
				 //ע��ɹ�,���ص�½ҳ��
				 System.out.println("ע��ɹ�");
				 //���ص�½ҳ��
				 UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
			 }
			 
		}else{
			//ע��ʧ��ҳ��
			loginView.registerErrorView(users);
			
		}
		
	}
	
	/**
	 * ����
	 * @return
	 */
	public static BufferedReader scanInfo() {
		//��׼����������
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		return br;
	}
	
	
}
