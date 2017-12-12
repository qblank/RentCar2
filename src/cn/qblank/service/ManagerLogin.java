package cn.qblank.service;

import java.io.BufferedReader;

import cn.qblank.View.LoginView;
import cn.qblank.entity.Car;
import cn.qblank.entity.User;
import cn.qblank.main.Cient;

/**
 * 管理员登陆
 * @author Administrator
 *
 */
public class ManagerLogin {
	
	static ManagerPlay managerPlay = null;
	//设置管理员权限为1
	private  static final int MANAGER = 1;
	static LoginView loginView = null;
	
	
	static{
		//创建视图类对象
		loginView = new LoginView();
		
	}
	/**
	 * 管理员登陆
	 * @throws Exception
	 */
	public static void manageLogin() throws Exception{
		//创建一个汽车对象
		Car car = new Car();
		//创建一个ManagerPlay对象
		managerPlay = new ManagerPlay();
		
		//管理员登陆页面
		loginView.managerLoginView();
		
		BufferedReader br = Login.scanInfo();
		
		int m_sel = Integer.parseInt(br.readLine());
		if (m_sel == 1) {
			//创建一个管理员用户
			User manager = new User();
			//设置类型
			manager.setType(MANAGER);
			
			System.out.println("用户名:");
			String user = br.readLine();
			System.out.println("密码:");
			String pwd = br.readLine();
			
			//设置账号密码的值 还有用户类型
			manager.setUserName(user);
			manager.setPassword(pwd);
			manager.setType(MANAGER);
			
			/**
			 * 访问服务器，判断账号密码是否正确
			 * 如果正确，则flag = true 
			 */
			//判断是否成功
			boolean flag = Cient.readMessage(manager);
			
			if (flag) {
				//登陆成功
				System.out.println("管理员登陆成功");
				System.out.println();
				//登陆的界面
				loginView.managerLoginSuccessView();
				
				//查询所有汽车的数据
				loginView.showManagerCarView();
				
				//管理员操作的界面
				loginView.managerPlayView();
				
				//管理员对其操作
				managerPlay.managerPlay(car);
				
			}else{
				//管理员登陆失败页面
				loginView.managerLoginErrorView();
			}
		}else if(m_sel == 2){
			//返回上一个界面
			Login.login();
		}
		
	}
}
