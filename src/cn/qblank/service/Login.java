package cn.qblank.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cn.qblank.View.LoginView;
import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.dao.Impl.LoginDaoImpl;


/**
 * �ܵĵ�¼ģ��
 * @author ��ĩ��
 *
 */
public class Login {
	
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
	 * ������
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void login() throws Exception{
		//������
		loginView.mainLoginView();
		
		BufferedReader br = Login.scanInfo();
		//ת��Ϊint����
		int role = Integer.parseInt(br.readLine());
		switch(role){
			case 0:
				//������ͨ�û�����
				UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
				
				break; 
			case 1:
				//�������Ա�û�����
				ManagerLogin.manageLogin();
				break;
			
			default:
				System.out.println("���꣬��Ҫ����");
				//����������
				login();
				break;
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
