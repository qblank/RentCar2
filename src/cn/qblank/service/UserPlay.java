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
	//定义一个状态 当为true时，升序排列，否则降序排列
	static boolean sortStatus;
	private LoginDaoImpl loginDaoImpl;
	private RecordImpl recordImpl;
	
	
	static{
		//赋值为true 只加载一次
		sortStatus = true;
		
	}
	
	/**
	 * 管理员选择功能
	 * @param car
	 * @throws Exception
	 */
	public void userPlay(Car car,User user) throws Exception{
		
		//新建一个登陆视图对象
		loginView = new LoginView();
		//新建一个Car的实现类对象
		carImpl = new CarImpl();
		//新建一个操作视图对象
		playCarView = new PlayCarView();
		//创建当前类对象
		userPlay = new UserPlay();
		
		loginDaoImpl = new LoginDaoImpl();
		
		//创建一条租赁记录对象
		RentRecord rentRecord = null;
		
		//获取用户输入
		BufferedReader br = Login.scanInfo();
		String userSc = br.readLine();
		
		//用于获取+后面的id
		int id = 0;
		if (userSc.length() > 2) {
			//获取+号后面的编号
			id = Integer.parseInt(userSc.substring(2));
		}
		
		if (userSc.equals("0")) {
			//退出
			Login.login();
		}else if (userSc.equals("1+"+id+"")) {
			//创建一个租赁类
			recordImpl = new RecordImpl();
			
			//设置汽车的编号
			car.setId(id);
			
			//创建一条租赁记录对象
			rentRecord = new RentRecord();
			if (car.getRent_status() == 0) {
				//表示可租赁
				
				//设置记录中的值
				rentRecord.setUserId(user.getId());
				rentRecord.setCar_id(car.getId());
				rentRecord.setStartDate(Utils.getRentTimes());
				rentRecord.setPayment(loginDaoImpl.getRentMoney(id));
				//添加一条租车记录
				carImpl.rentCar(id, user,rentRecord);
				System.out.println("租车成功");
				//将状态设置不可租
				car.setRent_status(1);
				//修改状态
				recordImpl.updateStatus(car, id);
				
				//打印信息头
				playCarView.showRentCarHeaderView();
				//将所租的车的信息打印出来
				playCarView.showRentCarView(id);
				
				//查询订单
			}else{
				//提示错误信息
				playCarView.rentErrorView();
			}
			
		}else if (userSc.equals("2+"+id+"")) {
			//2+1 升序 2+2降序
			if (id == 1) {
				
				//升序
				//设置状态值为true
				sortStatus = true;
			}else if (id == 2) {
				//降序
				//设置状态值为true
				sortStatus = false;
			}
			
			
			
			
			
		}else if (userSc.equals("3+"+id+"")) {
			//按类别编号搜索
			//创建一个CarCategory对象
			CarCategory cate = new CarCategory();
			
			//设置类型号
			cate.setCategory_id(id);
			
			//通过类型编号查找的sql语句
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and"
					+ " useable <> 1 and c.category_id = ?";
			
			 
			//登陆的界面
			loginView.userLoginSuccessView();
			
			//显示数据
			playCarView.showUserMessageById(id, sql);
			
		}else if (userSc.equals("4+"+id+"")) {
			//按品牌编号搜索
			CarBrand carB = new CarBrand();
			
			//设置类型号
			carB.setBrand_id(id);
			
			//通过类型编号查找的sql语句
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and"
					+ " useable <> 1 and c.brand_id  = ?";
			 
			
			//显示数据
			playCarView.showMessageById(id, sql);
			
		}else if (userSc.equals("5")) {
			//相当于返回主菜单
			
			//查看全部汽车
			/*loginView.userLoginSuccessView();
			loginView.showUserCarView();*/
			
		}else if (userSc.equals("6")) {
			//查看我的租车记录
			playCarView = new PlayCarView();
			
			//获取列名
			playCarView.printRentCarViewHeader();
			
			//获取数据
			playCarView.printRentCarView();
			
			
		}else if (userSc.equals("7+"+id+"")) {
			//还车
			
			//明天完成啦
			
			//新建一个汽车实现类对象
			carImpl = new CarImpl();
			
			//创建对象
			recordImpl = new RecordImpl();
			
			//获取租赁状态 并设置
			car.setRent_status(recordImpl.rentStatus(id));
			
			
			//测试
//			System.out.println(car.getRent_status());
			
			//租了的才能还
			if (car.getRent_status() == 1) {
				//在记录表中添加归还的时间和需要付款总金额
				
				
				//获取还车时间
				
				Date returnTimes = Utils.getReturnTimes();
				
				//获取租金 1.先查询之前的借车时间   2.将还车时间减去借车时间再乘以租金
				Date rentTimes = carImpl.queryRentTime(id);
				
				//互殴去总金额
				double totalMoney = Utils.getTotalMoney(id, returnTimes, rentTimes,recordImpl);
				
				//将记录存入数据库中
				recordImpl.returnCar(id, returnTimes, totalMoney);
				
				//测试
//				System.out.println("租车时间:"+rentTimes+"\n"+"还车时间:"+returnTimes);
//				System.out.println(returnTimes.getTime() - rentTimes.getTime());
//				System.out.println(dayRentMoney + "\t"+day);
				//将还车的数据插入到记录表中
				
				
				//将状态设置可租
				car.setRent_status(0);
				
				//在数据库中修改状态
				recordImpl.updateStatus(car, id);
				
				
			}else if (car.getRent_status() == 0) {
				//没租车
				playCarView.returnErrorView();
			}
			
		}
		
		/**
		 * 返回主界面
		 */
		
		//管理员操作的界面
		loginView.userLoginSuccessView();
		
		//判断目前的状态
		if (sortStatus) {
			/*//升序
			playCarView.showUserCarAscView();*/
			
			//升序
			carImpl.orderByAsc();
			//升序
			playCarView.showUserCarAscView();
		}else{
			/*//降序
			playCarView.showUserCarDescView();*/
			
			//降序
			carImpl.orderByDesc();
			//降序
			playCarView.showUserCarDescView();
		}
		//用户操作的界面
		loginView.userPlayView();
		
		//管理员对其操作
		userPlay.userPlay(car,user);
		
	}

}
