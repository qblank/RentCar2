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
 * 用户操作类
 * @author Administrator
 *
 */
public class ManagerPlay {
	private static LoginView loginView = null;
	private PlayCarView playCarView = null;
	private CarImpl carImpl = null;
	private ManagerPlay managerPlay = null;
	
	//默认为升序排列
//	private boolean sortStatus = true;
	
	/**
	 * 管理员选择功能
	 * @throws IOException 
	 */
	public void managerPlay(Car car) throws Exception{
		//新建一个登陆视图对象
		loginView = new LoginView();
		//新建一个Car的实现类对象
		carImpl = new CarImpl();
		//新建一个操作视图对象
		playCarView = new PlayCarView();
		//创建当前类对象
		managerPlay = new ManagerPlay();
		
		//获取用户输入
		BufferedReader br = Login.scanInfo();
		String managerSc = br.readLine();
		
		//用于获取+后面的id
		int id = 0;
		if (managerSc.length() > 2) {
			//获取+号后面的编号
			id = Integer.parseInt(managerSc.substring(2));
		}
		//测试
//		System.out.println(id);
		
		if (managerSc.equals("0")) {
			//退出
			Login.login();
			
		}else if (managerSc.equals("1+"+id+"")) {
			//查看汽车编号
			//创建ManagerCarView对象
			ManagerCarView managerCarView = new ManagerCarView();
			//设置id
			managerCarView.setId(id);
			//通过汽车编号查找汽车的sql语句
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.id = ?";
			
			//登陆的界面
			loginView.managerLoginSuccessView();
			
			//显示数据
			playCarView.showMessageById(id, sql);
			
			//返回主界面
			returnMain(car,id);
			
			
			
		}else if(managerSc.equals("5")){
			/**
			 * 查看全部汽车
			 */
			loginView.managerLoginSuccessView();
			//显示数据
			loginView.showManagerCarView();
			//管理员操作的界面
			loginView.managerPlayView();
			//管理员对其操作
			managerPlay.managerPlay(car);
			
			
		}else if (managerSc.equals("6")) {
			//添加汽车
			
			//进入添加汽车的界面
			playCarView.addCarView();
			
			//管理员输入用户信息
			playCarView.scanfCarInfo(car, br);
			
			
			//添加汽车
			carImpl.addCar(car);
			
			/**
			 * 返回主界面
			 */
			//登陆的界面
			loginView.managerLoginSuccessView();
			loginView.showManagerCarView();
			//管理员操作的界面
			loginView.managerPlayView();
			//管理员对其操作
			managerPlay.managerPlay(car);
			
		}else if (managerSc.equals("2+"+id+"")) {
			//排序
			
			if (id == 1) {
				//升序排列
				carImpl.orderByAsc();
				/**
				 * 返回主界面
				 */
				//登陆的界面
				loginView.managerLoginSuccessView();
				//升序
				playCarView.showCarByAsc();
				
				//管理员操作的界面
				loginView.managerPlayView();
				//管理员对其操作
				managerPlay.managerPlay(car);
				
			}else if (id == 2) {
				//降序排列
				carImpl.orderByDesc();
				
				/**
				 * 返回主界面
				 */
				returnMain(car,2);
			}
			
			
			
			
		}else if (managerSc.equals("3+"+id+"")) {
			//按类型搜索
			//创建一个CarCategory对象
			CarCategory cate = new CarCategory();
			
			//设置类型号
			cate.setCategory_id(id);
			
			//通过类型编号查找的sql语句
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.category_id = ?";
			
			
			//登陆的界面
			loginView.managerLoginSuccessView();
			
			//显示数据
			playCarView.showMessageById(id, sql);
			
			//返回主界面
			returnMain(car,id);
			
			
		}else if (managerSc.equals("4+"+id+"")) {
			//按品牌搜索
			//创建一个CarBrand对象
			CarBrand carBrand = new CarBrand();
			
			//设置品牌号
			carBrand.setBrand_id(id);
			
			//通过类型编号查找的sql语句
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.brand_id = ?";
			
			//登陆的界面
			loginView.managerLoginSuccessView();
			
			//显示数据
			playCarView.showMessageById(id, sql);
			
			//返回主界面
			returnMain(car,id);
			
			
		}else if (managerSc.equals("7+"+id+"")) {
			
			//准备查询语句
			String sql = "select c.id,c.model,c.t_comments,b.name,t.name,"
					+ "c.price,c.rent,c.status,c.useable"
					+ " from t_car c,t_brand b,t_category t "
					+ " where b.id = c.brand_id and t.id = c.category_id and c.id = ?";
			
			//登陆的界面
			loginView.managerLoginSuccessView();
			
			//显示数据
			playCarView.showMessageById(id, sql);
			
			//修改汽车的视图
			int updateSel = playCarView.updateCar(br, car);
			
			//修改对应编号的汽车
			carImpl.updateCar(id, car,updateSel);
			
			
			//返回主界面
			returnMain(car,id);
			
			
			
			
		}else if (managerSc.equals("8")) {
			//查询租车记录
			
			playCarView = new PlayCarView();
			
			//获取列名
			playCarView.printRentCarViewHeader();
			
			//获取数据
			playCarView.printRentCarView();
			
			//返回主菜单
			returnMain(car, id);
			
		}
		
		
	}

	/**
	 * 返回主界面
	 * @param car
	 * @throws Exception
	 */
	private void returnMain(Car car,int id) throws Exception {
		//登陆的界面
		loginView.managerLoginSuccessView();
		//降序
		playCarView.showCarByDesc();
		//管理员操作的界面
		loginView.managerPlayView();
		//管理员对其操作
		managerPlay.managerPlay(car);
	}

	
	
	
	
	
	
	
	
	
}
