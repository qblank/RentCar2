package cn.qblank.View;

import java.util.List;

import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.entity.CarView;
import cn.qblank.entity.ManagerCarView;
import cn.qblank.entity.User;
import cn.qblank.service.ManagerLogin;
import cn.qblank.service.UserLogin;

public class LoginView {
	CarImpl carImpl = null;

	/**
	 * 主界面
	 */
	public void mainLoginView() {

		System.out.println("请选择你的角色:0.普通用户\t1.管理员");
	}

	/**
	 * 用户页面
	 */
	public void userMainView() {
		System.out.println("用户界面");
		System.out.println("===============");
		System.out.println("欢迎访问岳式租车店");
		System.out.println("===============");

		System.out.println("1.登陆\t2.注册\t3.退出");
	}

	/**
	 * 用户登陆页面
	 */
	public void userLoginView() {
		System.out.println("用户界面");
		System.out.println("========登陆=======");

	}

	/**
	 * 登陆错误页面
	 * 
	 * @throws Exception
	 */
	public void loginErrorView() throws Exception {
		System.out.println("账号或密码不存在，请重新登陆");
		System.out.println("------------------------");

	}

	/**
	 * 用户注册页面
	 */
	public void userRegisterView() {
		System.out.println("==========注册页面==========");

	}

	/**
	 * 注册失败页面
	 * 
	 * @throws Exception
	 */
	public void registerErrorView(User users) throws Exception {
		System.out.println("两次输入的密码不一致,请重新输入");
		UserLogin.userRegister(users);

	}

	/**
	 * 管理员登陆页面
	 */
	public void managerLoginView() {
		System.out.println("管理员界面");
		System.out.println("===============");
		System.out.println("欢迎访问岳式租车店");
		System.out.println("===============");
		System.out.println("1.登陆\t2.退出");

	}

	/**
	 * 管理员登陆失败页面
	 * 
	 * @throws Exception
	 */
	public void managerLoginErrorView() throws Exception {
		System.out.println("账号或密码不存在，请重新登陆");
		System.out.println("------------------------");
		// 不正确返回登陆页面
		ManagerLogin.manageLogin();

	}

	/**
	 * 用户登录成功后的主界面
	 */
	public void userLoginSuccessView() {

		for (int i = 0; i < 60; i++) {
			System.out.print("=");
		}

		System.out.println();
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t\t租金\t是否可租");

	}

	/**
	 * 显示用户全部汽车的视图
	 */
	public void showUserCarView() {
		// 创建一个CarImpl对象
		carImpl = new CarImpl();

		// 创建一个List集合 用于接收数据
		List<CarView> list = null;

		// 接收数据
		list = carImpl.findAllPost();

		// 遍历并将用户的汽车数据显示出来
		for (CarView carView : list) {
			System.out.println(carView.getId() + "\t" + carView.getModel()
					+ "\t" + carView.getComments() + "\t"
					+ carView.getBrandName() + "\t" + carView.getTypeName()
					+ "\t" + carView.getPrice() + "\t"
					+ carView.getRent() + "\t"
					+ getStatus(carView.getStatus()));
		}

	}
	

	/**
	 * 显示管理员全部汽车的视图
	 */
	public void showManagerCarView() {
		// 创建一个CarImpl对象
		carImpl = new CarImpl();

		// 创建一个List集合 用于接收数据
		List<ManagerCarView> managerCarViews = carImpl.findAllCar();

		// 遍历并将管理员的汽车信息显示出来
		for (ManagerCarView managerCarView : managerCarViews) {
			System.out.println(managerCarView.getId() + "\t"
					+ managerCarView.getModel() + "\t"
					+ managerCarView.getComments() + "\t"
					+ managerCarView.getBrandName() + "\t"
					+ managerCarView.getTypeName() + "\t"
					+ managerCarView.getPrice() + "\t"
					+ managerCarView.getRent() + "\t"
					+ getStatus(managerCarView.getStatus()) + "\t"
					+ getUseable(managerCarView.getUseable()));

		}

	}

	/**
	 * 用户操作的视图
	 */
	public void userPlayView() {
		System.out.println("输入0退出");
		System.out.println("输入1+汽车编号    进入租车订单  如: 1+2");
		System.out.println("输入2+1  按价格升序排列      输入2+2  按价格降序排列");
		System.out.println("输入3+类型编号    按类型搜索");
		System.out.println("输入4+品牌编号    按品牌搜索");
		System.out.println("输入5        查看全部汽车");
		System.out.println("输入6   查看我的租车记录");
		System.out.println("输入7+汽车编号      还车");

	}

	/**
	 * 管理员登陆成功界面
	 */
	public void managerLoginSuccessView() {
		for (int i = 0; i < 60; i++) {
			System.out.print("=");
		}

		System.out.println();
		System.out.println("编号\t汽车名称\t备注\t品牌\t类型\t价格\t\t租金\t是否可租\t是否上架");

	}

	/**
	 * 管理员操作界面
	 */
	public void managerPlayView() {
		System.out.println("输入0     退出");
		System.out.println("输入1+汽车编号     查看指定汽车编号");
		System.out.println("输入2+1  按价格升序排列      输入2+2  按价格降序排列");
		System.out.println("输入3+类型编号     按类型搜索");
		System.out.println("输入4+品牌编号     按品牌搜索");
		System.out.println("输入5     查看全部汽车");
		System.out.println("输入6     添加汽车");
		System.out.println("输入7+汽车编号     修改汽车信息");
		System.out.println("输入8     查看汽车记录");

	}

	/**
	 * 将status的0转换为是 1转换为否
	 * 
	 * @param status
	 * @return
	 */
	public String getStatus(int status) {
		if (status == 0) {
			return "是";
		} else {
			return "否";
		}

	}

	/**
	 * 将useable的0转换为是 1转换为否
	 * 
	 * @param useable
	 * @return
	 */
	public String getUseable(int useable) {
		if (useable == 0) {
			return "上架";
		} else {
			return "下架";
		}

	}

}
