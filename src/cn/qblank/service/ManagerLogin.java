package cn.qblank.service;

import java.io.BufferedReader;

import cn.qblank.View.LoginView;
import cn.qblank.entity.Car;
import cn.qblank.entity.User;
import cn.qblank.main.Cient;

/**
 * ����Ա��½
 * @author Administrator
 *
 */
public class ManagerLogin {
	
	static ManagerPlay managerPlay = null;
	//���ù���ԱȨ��Ϊ1
	private  static final int MANAGER = 1;
	static LoginView loginView = null;
	
	
	static{
		//������ͼ�����
		loginView = new LoginView();
		
	}
	/**
	 * ����Ա��½
	 * @throws Exception
	 */
	public static void manageLogin() throws Exception{
		//����һ����������
		Car car = new Car();
		//����һ��ManagerPlay����
		managerPlay = new ManagerPlay();
		
		//����Ա��½ҳ��
		loginView.managerLoginView();
		
		BufferedReader br = Login.scanInfo();
		
		int m_sel = Integer.parseInt(br.readLine());
		if (m_sel == 1) {
			//����һ������Ա�û�
			User manager = new User();
			//��������
			manager.setType(MANAGER);
			
			System.out.println("�û���:");
			String user = br.readLine();
			System.out.println("����:");
			String pwd = br.readLine();
			
			//�����˺������ֵ �����û�����
			manager.setUserName(user);
			manager.setPassword(pwd);
			manager.setType(MANAGER);
			
			/**
			 * ���ʷ��������ж��˺������Ƿ���ȷ
			 * �����ȷ����flag = true 
			 */
			//�ж��Ƿ�ɹ�
			boolean flag = Cient.readMessage(manager);
			
			if (flag) {
				//��½�ɹ�
				System.out.println("����Ա��½�ɹ�");
				System.out.println();
				//��½�Ľ���
				loginView.managerLoginSuccessView();
				
				//��ѯ��������������
				loginView.showManagerCarView();
				
				//����Ա�����Ľ���
				loginView.managerPlayView();
				
				//����Ա�������
				managerPlay.managerPlay(car);
				
			}else{
				//����Ա��½ʧ��ҳ��
				loginView.managerLoginErrorView();
			}
		}else if(m_sel == 2){
			//������һ������
			Login.login();
		}
		
	}
}
