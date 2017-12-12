package cn.qblank.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cn.qblank.View.LoginView;
import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.dao.Impl.LoginDaoImpl;


/**
 * 总的登录模块
 * @author 周末班
 *
 */
public class Login {
	
	static LoginDaoImpl loginDaoImpl = null;
	
	static CarImpl carImpl = null;
	
	static LoginView loginView = null;
	
	static ManagerPlay managerPlay = null;
	
	static UserPlay userPlay = null;
	
	
	static{
		//创建对象
		loginDaoImpl = new LoginDaoImpl();
		carImpl = new CarImpl();
		
		//创建视图类对象
		loginView = new LoginView();
	}
	
	
	/**
	 * 主界面
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void login() throws Exception{
		//主界面
		loginView.mainLoginView();
		
		BufferedReader br = Login.scanInfo();
		//转换为int类型
		int role = Integer.parseInt(br.readLine());
		switch(role){
			case 0:
				//进入普通用户界面
				UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
				
				break; 
			case 1:
				//进入管理员用户界面
				ManagerLogin.manageLogin();
				break;
			
			default:
				System.out.println("少年，不要乱来");
				//返回主界面
				login();
				break;
			}
	}

	/**
	 * 输入
	 * @return
	 */
	public static BufferedReader scanInfo() {
		//标准输入流输入
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		return br;
	}
	
	
	
	
	
	
	
}
