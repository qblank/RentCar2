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
	//设置用户的角色为0
	private static final int USER = 0;
	
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
	 * 用户登陆
	 * @throws Exception 
	 */
	public static void userLogin(LoginView loginView,UserPlay userPlay,LoginDaoImpl loginDaoImpl) throws Exception{
		//用户主页面
		loginView.userMainView();
		//创建一个汽车对象
		Car car = new Car();
		
		//标准输入
		BufferedReader br = Login.scanInfo();
		int sc = 0;
		sc = Integer.parseInt(br.readLine());
			
		//创建一个用户对象
		User users = new User();
			
			switch (sc) {
			case 1:
				//创建一个UserPlay对象
				userPlay = new UserPlay();
				
				//用户登陆页面
				loginView.userLoginView();
				
				System.out.println("用户名:");
				String user = br.readLine();
				System.out.println("密码:");
				String pwd = br.readLine();
				
				//设置账号密码的值 还有用户类型
				users.setUserName(user);
				users.setPassword(pwd);
				//设置类型
				users.setType(USER);
				//设置用户id
				users.setId(loginDaoImpl.getUserId(users));
				//调用dao实现类中的testLogin方法
//				boolean flag = loginDaoImpl.testLogin(users);
				boolean flag = Cient.readMessage(users);
				if (flag) {
					//登陆成功
					System.out.println("用户登陆成功");
					//显示登陆成功页面
					loginView.userLoginSuccessView();
					loginView.showUserCarView();
					//用户操作的功能界面
					loginView.userPlayView();
					//
					userPlay.userPlay(car,users);
				}else{
					//登陆失败页面
					loginView.loginErrorView();
					// 不正确返回登陆页面
					UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
				}
				break;
			
			case 2:
				System.out.println("进入用户注册页面");
				UserLogin.userRegister(users);
				break;
				
			case 3:
				System.out.println("退出");
				Login.login();
				
				break;
			default:
				System.out.println("请重新输入");
				//返回
				UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
				
				break;
			}
	}
	
	
	/**
	 * 用户注册
	 * @throws Exception 
	 */
	public static void userRegister(User users) throws Exception{
		BufferedReader br = scanInfo();
		
		//用户注册页面
		loginView.userRegisterView();
		
		System.out.println("用户名:");
		String userName = br.readLine();
		
		System.out.println("密码:");
		String password = br.readLine();
		
		System.out.println("确认密码:");
		String conPsd = br.readLine();
		
		System.out.println("请选择性别:0.男\t1.女");
		int sex = Integer.parseInt(br.readLine());
		@SuppressWarnings("unused")
		String gender = null;
		switch (sex) {
		case 0:
			gender = "男";
			break;
		case 1:
			gender = "女";
			break;
		default:
			System.out.println("你是人妖吗？");
			sex = 0;
			gender = "男";
			break;
		}
		System.out.println("身份证号:");
		String id_number = br.readLine();
		
		System.out.println("电话:");
		String tel = br.readLine();
		
		System.out.println("地址:");
		String addr = br.readLine();
		
		//判断两次输入的密码是否一致
		 if (password.equals(conPsd)) {
			 users.setUserName(userName);
			 users.setPassword(password);
			//判断数据库中是否含有该用户
			 if (userName.equals(loginDaoImpl.getUser(users))) {
				//含有该用户
				 System.out.println("该用户已经存在,请重新输入");
				 userRegister(users);
			 }else{
				 //设置用户的值
				 users.setSex(sex);
				 users.setId_number(id_number);
				 users.setTel(tel);
				 users.setAddr(addr);
				 users.setType(0);
				 
				 //在用户表中添加数据
				 loginDaoImpl.registerUser(users);
				 
				 //注册成功,返回登陆页面
				 System.out.println("注册成功");
				 //返回登陆页面
				 UserLogin.userLogin(loginView, userPlay, loginDaoImpl);
			 }
			 
		}else{
			//注册失败页面
			loginView.registerErrorView(users);
			
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
