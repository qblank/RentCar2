package cn.qblank.dao;

import java.sql.Date;
import java.util.List;

import cn.qblank.entity.Car;
import cn.qblank.entity.CarBrand;
import cn.qblank.entity.CarCategory;
import cn.qblank.entity.CarView;
import cn.qblank.entity.ManagerCarView;
import cn.qblank.entity.RentCarView;
import cn.qblank.entity.RentRecord;
import cn.qblank.entity.User;

public interface ICar {
	
	/**
	 * 查询所有上架的车辆
	 */
	public List<CarView> findAllPost();
	
	/**
	 * 查询所有车辆(包括未上架的)
	 */
	public List<ManagerCarView> findAllCar();
	
	/**
	 * 通过编号查找车辆
	 */
	public List<ManagerCarView> findById(int id,String sql);
	
	/**
	 * 添加汽车
	 */
	public void addCar(Car car);
	
	/**
	 * 查询汽车类别
	 * @return
	 */
	public List<CarCategory> queryCarCategory();
	
	/**
	 * 查询汽车品牌
	 * @return
	 */
	public List<CarBrand> queryCarBrand();
	
	/**
	 * 升序排列 管理员
	 * id用于分辨是用户还说管理员
	 */
	public List<ManagerCarView> orderByAsc();
	
	/**
	 * 降序排列 管理员
	 * id用于分辨是用户还说管理员
	 */
	public List<ManagerCarView> orderByDesc();
	
	/**
	 * 修改汽车
	 * updateSel 选择修改  1.价格   2.上架下架 
	 */
	public void updateCar(int id,Car car,int updateSel);
	
	/**
	 * 升序排列  用户
	 * @return
	 */
	public List<CarView> orderAsc();
	/**
	 * 降序排列  用户
	 * @return
	 */
	public List<CarView> orderDesc();
	
	/**
	 * 通过id查找值
	 * @param id
	 * @param sql
	 * @return
	 */
	public List<CarView> userFindById(int id,String sql);
	
	/**
	 * 借车记录
	 */
	public void rentCar(int id,User user,RentRecord rentRecord);
	
	/**
	 * 将所借车的信息打印出来
	 * @return
	 */
	public RentCarView rentCar(int id);
	
	/**
	 * 查询租车记录
	 * @return
	 */
	public List<RentCarView> findAllRecord();
	
	/**
	 * 修改状态
	 */
//	public void updateStatus(Car car,int id);
	
	/**
	 * 查询汽车的租赁时间
	 */
	public Date queryRentTime(int id);
	
}
