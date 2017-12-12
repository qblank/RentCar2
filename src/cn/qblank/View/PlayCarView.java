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
 * 操作汽车的界面
 * @author Administrator
 *
 */
public class PlayCarView {
	CarImpl carImpl = null;
	PlayCarView playCarView = null;
	private LoginView loginView;
	
	/**
	 * 添加汽车的界面
	 */
	public void addCarView(){
		System.out.println("====================");
		System.out.println("请分别输入一下信息:");
		System.out.println("----------------");
		System.out.println("品牌如下:");
		
		
	}
	
	
	
	/**
	 * 显示汽车类别
	 */
	public void showCarCategoryView(){
		//创建一个CarImpl对象
		carImpl = new CarImpl();
		
		List<CarCategory> category =  null;
		
		//接收品牌的信息
		category = carImpl.queryCarCategory();
		
		//遍历
		for (CarCategory carCategory : category) {
			System.out.println(carCategory.getCategory_id() +"\t" 
					+carCategory.getCategory_name());
		}
		
	
	}
	
	/**
	 * 升序显示汽车数据 
	 */
	public void showCarByAsc(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		
		List<ManagerCarView> orderAscList = null;
		
		orderAscList = carImpl.orderByAsc();
		// 遍历并将管理员的汽车信息显示出来
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
	 * 降序显示汽车数据
	 */
	public void showCarByDesc(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		List<ManagerCarView> orderDescList = null;
		
		orderDescList = carImpl.orderByDesc();
		
		// 遍历并将管理员的汽车信息显示出来
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
	 * 用户升序显示汽车数据
	 */
	public void showUserCarAscView(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		List<CarView> orderAscList = null;
		
		orderAscList = carImpl.orderAsc();
		
		// 遍历并将管理员的汽车信息显示出来
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
	 * 用户降序显示汽车数据
	 */
	public void showUserCarDescView(){
		loginView = new LoginView();
		carImpl = new CarImpl();
		
		List<CarView> orderDescList = null;
		
		orderDescList = carImpl.orderDesc();
		
		// 遍历并将管理员的汽车信息显示出来
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
	 * 显示汽车品牌的界面
	 */
	public void showCarBrand(){
		
		List<CarBrand> brand = null;
		
		//接收类型的信息
		brand = carImpl.queryCarBrand();
		
		//遍历
		for (CarBrand carBrand : brand) {
			System.out.println(carBrand.getBrand_id() + "\t"
					+ carBrand.getBrand_name());
		}
		
		
	}
	
	
	
	
	
	/**
	 * 管理员输入用户信息
	 * @param car
	 * @param br
	 * @throws IOException
	 */
	public void scanfCarInfo(Car car, BufferedReader br) throws IOException {
		playCarView = new PlayCarView();
		
		//选择品牌编号
		System.out.println("品牌编号\t品牌名");
		//查询品牌表
		playCarView.showCarCategoryView();
		
		System.out.println("请选择品牌编号:");
		int cate_id = Integer.parseInt(br.readLine());
		car.setCar_category_id(cate_id);
		
		
		//类型
		System.out.println("类型如下:");
		System.out.println("类型编号\t类型名");
		//查询类型表
		playCarView.showCarBrand();
		System.out.println("请选择类型编号:");
		int brand_id = Integer.parseInt(br.readLine());
		car.setCar_brand_id(brand_id);
		
		
		System.out.println("----------------");
		System.out.println("型号:");
		String model = br.readLine();
		car.setCar_model(model);
		
		System.out.println("----------------");
		System.out.println("车牌号:");
		String car_number = br.readLine();
		car.setCar_number(car_number);
		
		System.out.println("----------------");
		System.out.println("概要:");
		String comments = br.readLine();
		car.setCar_comments(comments);
		
		System.out.println("----------------");
		System.out.println("颜色:");
		String color = br.readLine();
		car.setCar_color(color);
		
		System.out.println("----------------");
		System.out.println("汽车价格:");
		double price = Double.parseDouble(br.readLine());
		car.setCar_price(price);
		
		System.out.println("----------------");
		System.out.println("每日租金:");
		double rent = Double.parseDouble(br.readLine());
		car.setCar_rent(rent);
		
		System.out.println("----------------");
		System.out.println("是否可借(0:可借   1:不可借):");
		int status = Integer.parseInt(br.readLine());
		car.setRent_status(status);
		
		System.out.println("----------------");
		System.out.println("是否上架(0:上架    1:下架):");
		int useable = Integer.parseInt(br.readLine());
		car.setUseable_status(useable);
	}
	
	
	/**
	 * 将通过id查找信息的数据显示出来
	 */
	public void showMessageById(int id,String sql){
		//创建一个CarImpl对象
		carImpl = new CarImpl();
		//创建LoginView对象
		loginView = new LoginView();
		
		
		List<ManagerCarView> list = carImpl.findById(id, sql);
		
		for (ManagerCarView managerCarView : list) {
			
			//打印数据
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
	 * 将通过id查找信息的数据显示出来
	 */
	public void showUserMessageById(int id,String sql){
		//创建一个CarImpl对象
		carImpl = new CarImpl();
		//创建LoginView对象
		loginView = new LoginView();
		
		
		List<CarView> list = carImpl.userFindById(id, sql);
		
		for (CarView carView : list) {
			
			//打印数据
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
	 * 管理员修改
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public int updateCar(BufferedReader br,Car car) throws IOException{
		System.out.println("请输入要修改的内容编号:");
		System.out.println("1.租赁价格    2.上架下架");
		
		int managerSc = Integer.parseInt(br.readLine());
		
		//租赁价格
		if (managerSc == 1) {
			//请输入新的租赁价格
			System.out.println("请输入新的租赁价格:");
			double price = Double.parseDouble(br.readLine());
			car.setCar_price(price);
			
			
		}else if (managerSc == 2) {
			System.out.println("1.上架   2.下架");
			int sel = Integer.parseInt(br.readLine());
			if (sel == 1) {
				//上架
				car.setUseable_status(0);
				
			}else if (sel == 2) {
				//下架
				car.setUseable_status(1);
			}
			
		}
		
		return managerSc;
		
	}
	
	
	/**
	 * 用户在租赁不可租赁的车的情况下发生的错误
	 */
	public void rentErrorView(){
		System.out.println("该车已经被租走了...");
	}
	
	
	/**
	 * 租车信息头
	 */
	public void showRentCarHeaderView(){
		System.out.println("编号\t汽车名称\t每日租金\t备注\t品牌\t类型\t借车时间");
		
	}
	
	/**
	 * 打印租车的信息
	 */
	public void showRentCarView(int id){
		System.out.println("租车成功，租车信息如下:");
		carImpl = new CarImpl();
		
		//创建对象用于接收数据
		RentCarView rentCarView = new RentCarView();
		
		rentCarView = carImpl.rentCar(id);
		
		//打印信息
		System.out.println(rentCarView.getId()+"\t"+rentCarView.getCar_name()
				+"\t"+ rentCarView.getDay_rent()+"\t"+rentCarView.getCar_comments()
				+"\t"+ rentCarView.getCar_brand()+"\t"+rentCarView.getCar_category()
				+"\t"+ rentCarView.getRent_time());
		
		
	}
	
	/**
	 * 将租车记录列名打印出来
	 */
	public void printRentCarViewHeader(){
		for (int i = 0; i < 20; i++) {
			System.out.print("=");
		}
		System.out.println();
		
		System.out.println("编号\t汽车编号\t汽车名称\t租金总额\t备注\t品牌\t类型\t借车时间\t\t\t还车时间");
	}
	
	/**
	 * 将租车记录打印出来
	 */
	public void printRentCarView(){
		carImpl = new CarImpl();
		
		List<RentCarView> list = carImpl.findAllRecord();
		
		//遍历
		for (RentCarView rentCarView : list) {
			
			System.out.println(rentCarView.getId()+"\t"+rentCarView.getCar_id()
					+"\t"+rentCarView.getCar_name()+"\t"+rentCarView.getDay_rent()
					+"\t"+rentCarView.getCar_comments()+"\t"+rentCarView.getCar_brand()
					+"\t"+rentCarView.getCar_category()+"\t"+rentCarView.getRent_time()
					+"\t"+rentCarView.getReturn_time());
		}
		
	}
	
	
	/**
	 * 没租车却要还车的错误
	 */
	public void returnErrorView(){
		System.out.println("少年，你没有租赁这辆车");
	}
	
}
