package cn.qblank.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import cn.qblank.dao.ICar;
import cn.qblank.entity.Car;
import cn.qblank.entity.CarBrand;
import cn.qblank.entity.CarCategory;
import cn.qblank.entity.CarView;
import cn.qblank.entity.ManagerCarView;
import cn.qblank.entity.RentCarView;
import cn.qblank.entity.RentRecord;
import cn.qblank.entity.User;
import cn.qblank.util.ConnectionFactory;

public class CarImpl implements ICar{

	private CarView carView;
	private ManagerCarView managerCarView;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private RentCarView rentCarView;
	//排序语句
	private String orderBy =  "select c.id,c.model,c.t_comments,b.name,t.name,c.price,c.rent,"
			+ "c.status,c.useable from t_car c,t_brand b,t_category t "
			+ "where b.id = c.brand_id and t.id = c.category_id ";
	

	@Override
	public List<CarView> findAllPost() {
		ResultSet rs = null;
		List<CarView> list = null;
		
		
		
		try {
			conn = ConnectionFactory.getConnection();
			
			//查询主表
			String findAllSQL = "select c.id,c.model,c.t_comments,b.name,t.name,c.price,c.rent,"
					+ "c.status from t_car c,t_brand b,t_category t "
					+ "where b.id = c.brand_id and t.id = c.category_id and useable <> 1 order by c.id";
			pstmt = conn.prepareStatement(findAllSQL);
			rs = pstmt.executeQuery();
			//建立一个ArrayList集合，将数据存进去
			list = new ArrayList<>();
			while(rs.next()){
				//创建一个CarView对象
				carView = new CarView();
				//将数据存入对象中
				showMessage(rs,carView);
				//将对象存入集合中
				list.add(carView);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return list;
		
		
	}

	/**
	 * 显示查询的数据
	 * @param rs
	 * @throws SQLException
	 */
	private void showMessage(ResultSet rs ,CarView carView) throws SQLException {
		carView.setId(rs.getInt(1));
		carView.setModel(rs.getString(2));
		carView.setComments(rs.getString(3));
		carView.setBrandName(rs.getString(4));
		carView.setTypeName(rs.getString(5));
		carView.setPrice(rs.getDouble(6));
		carView.setRent(rs.getDouble(7));
		carView.setStatus(rs.getInt(8));
	}

	/**
	 * 查找所有汽车
	 */
	@Override
	public List<ManagerCarView> findAllCar() {
		ResultSet rs = null;
		List<ManagerCarView> managerList = null;
		
		
		try {
			conn = ConnectionFactory.getConnection();
			//查询主表
			String findAllSQL = "select c.id,c.model,c.t_comments,b.name,t.name,c.price,c.rent,"
					+ "c.status,c.useable from t_car c,t_brand b,t_category t "
					+ "where b.id = c.brand_id and t.id = c.category_id order by c.id";
			//改为手动提交事务
			conn.setAutoCommit(false);
			//建立回滚点
			conn.setSavepoint();
			pstmt = conn.prepareStatement(findAllSQL);
			rs = pstmt.executeQuery();
			//建立一个ArrayList集合，将数据存进去
			managerList = new ArrayList<>();
			while(rs.next()){
				//创建一个CarView对象
				managerCarView = new ManagerCarView();
				//将数据存入对象中
				showMessage(rs, managerCarView);
				managerCarView.setUseable(rs.getInt(9));
				//将数据存入集合中
				managerList.add(managerCarView);
				
			}
			
		} catch (Exception e) {
			//回滚
			try {
				conn.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
			}
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return managerList;
	}
	
	/**
	 * 将status的0转换为是  1转换为否
	 * @param status
	 * @return
	 */
	public String getStatus(int status){
		if(status==0){
			return "是";
		}else{
			return "否";
		}
		
	}
	
	/**
	 * 将useable的0转换为是   1转换为否
	 * @param useable
	 * @return
	 */
	public String getUseable(int useable){
		if (useable == 0) {
			return "上架";
		}else{
			return "下架";
		}
		
	}
	
	/**
	 * 通过id查找 管理员
	 */
	@Override
	public List<ManagerCarView> findById(int id,String sql) {
		ResultSet rs = null;
		List<ManagerCarView> list = null;
		
		try {
			//获取连接
			conn = ConnectionFactory.getConnection();
			//获取pstmt对象
			pstmt = conn.prepareStatement(sql);
			
			//设置参数
			pstmt.setInt(1, id);
			
			//执行sql语句
			rs = pstmt.executeQuery();
			
			//建立一个ArrayList集合，将数据存进去
			list = new ArrayList<>();
			while(rs.next()){
				//创建一个ManagerCarView对象
				managerCarView = new ManagerCarView();
				
				//将数据存入对象中
				showMessage(rs, managerCarView);
				managerCarView.setUseable(rs.getInt(9));
				
				list.add(managerCarView);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return list;
		
		
		
		
		
	}
	
	
	

	/**
	 * 添加汽车
	 */
	@Override
	public void addCar(Car car) {
		
		try {
			//获取连接
			conn = ConnectionFactory.getConnection();
			//改为手动提交事务
			conn.setAutoCommit(false);
			//建立回滚点
			conn.setSavepoint();
			//准备sql语句
			String insertSQL = "insert into t_car values(t_car_id_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			//准备预编译对象
			pstmt = conn.prepareStatement(insertSQL);
			//设置参数
			pstmt.setString(1, car.getCar_number());
			pstmt.setInt(2, car.getCar_brand_id());
			pstmt.setString(3, car.getCar_model());
			pstmt.setString(4, car.getCar_color());
			pstmt.setInt(5, car.getCar_category_id());
			pstmt.setString(6, car.getCar_comments());
			pstmt.setDouble(7, car.getCar_price());
			pstmt.setDouble(8, car.getCar_rent());
			pstmt.setInt(9, car.getRent_status());
			pstmt.setInt(10, car.getUseable_status());
			
			//执行sql
			int rows = pstmt.executeUpdate();
			System.out.println("添加成功,更新了" + rows +"行");
			
			
		} catch (SQLException e) {
			try {
				//回滚事务
				conn.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
				
			}
			
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
		}
		
		
	}

	/**
	 * 查询所有汽车类别
	 */
	@Override
	public List<CarCategory> queryCarCategory() {
		ResultSet rs = null;
		List<CarCategory> carCategoryList = null;
		CarCategory category = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			//准备sql语句
			String queryCategory = "select id,name from t_category";
			//改为手动提交事务
			conn.setAutoCommit(false);
			
			//建立回滚点
			conn.setSavepoint();
			pstmt = conn.prepareStatement(queryCategory);
			
			//执行
			rs = pstmt.executeQuery();
			
			carCategoryList = new ArrayList<>();
			
			while(rs.next()){
				//创建一个CarCategory对象
				category = new CarCategory();
				
				/*rs.getInt(category.getCategory_id());
				rs.getString(category.getCategory_name());*/
				category.setCategory_id(rs.getInt(1));
				category.setCategory_name(rs.getString(2));
				
				//存入集合
				carCategoryList.add(category);
				
			}
			
			
		} catch (SQLException e) {
			//回滚
			try {
				conn.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
			}
		}finally{
			
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return carCategoryList;
	}

	/**
	 * 查询所有汽车品牌
	 */
	@Override
	public List<CarBrand> queryCarBrand() {
		ResultSet rs = null;
		List<CarBrand> carBrandList = null;
		CarBrand carBrand = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			//准备sql语句
			String queryBrand = "select id,name from t_brand";
			//改为手动提交事务
			conn.setAutoCommit(false);
			//建立回滚点
			conn.setSavepoint();
			pstmt = conn.prepareStatement(queryBrand);
			
			//执行
			rs = pstmt.executeQuery();
			
			carBrandList = new ArrayList<>();
			
			while(rs.next()){
				//创建一个CarCategory对象
				carBrand = new CarBrand();
				
				/*rs.getInt(carBrand.getBrand_id());
				rs.getString(carBrand.getBrand_name());*/
				carBrand.setBrand_id(rs.getInt(1));
				carBrand.setBrand_name(rs.getString(2));
				
				//存入集合
				carBrandList.add(carBrand);
				
			}
			
			
		} catch (SQLException e) {
			//回滚
			try {
				conn.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
			}
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return carBrandList;
		
		
	}

	/**
	 * 升序排列
	 */
	@Override
	public List<ManagerCarView> orderByAsc() {
		ResultSet rs = null;
		List<ManagerCarView> managerList = null;
		
		
		try {
			conn = ConnectionFactory.getConnection();
			
			//查询主表
			orderBy += " order by c.price";
			
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//建立一个ArrayList集合，将数据存进去
			managerList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//创建一个CarView对象
				managerCarView = new ManagerCarView();
				//将数据存入对象中
				showMessage(rs, managerCarView);
				managerCarView.setUseable(rs.getInt(9));
				//将数据存入集合中
				managerList.add(managerCarView);
				
			}
			
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return managerList;
	}

	
	/**
	 * 降序排列
	 */
	@Override
	public List<ManagerCarView> orderByDesc() {
		ResultSet rs = null;
		List<ManagerCarView> managerList = null;
		
		System.out.println("降序排列");
		try {
			conn = ConnectionFactory.getConnection();
			
			//查询主表 降序
			orderBy += " order by c.price desc";
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//建立一个ArrayList集合，将数据存进去
			managerList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//创建一个CarView对象
				managerCarView = new ManagerCarView();
				//将数据存入对象中
				showMessage(rs, managerCarView);
				managerCarView.setUseable(rs.getInt(9));
				//将数据存入集合中
				managerList.add(managerCarView);
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return managerList;
		
	}

	/**
	 * 修改汽车价格
	 */
	@Override
	public void updateCar(int id,Car car,int updateSel) {
		
		
		try {
			//创建连接对象
			conn = ConnectionFactory.getConnection();
			
			//准备sql语句
			String updateSQL = "update t_car set price = ? where id = ?";
			String updateSQL2 = "update t_car set useable = ? where id = ?";
			
			if (updateSel == 1) {
				//修改价格
				
				//获取Pstmt对象
				pstmt = conn.prepareStatement(updateSQL);
				//设置参数
				pstmt.setDouble(1, car.getCar_price());
				pstmt.setInt(2, id);
				
			}else if (updateSel == 2) {
				pstmt = conn.prepareStatement(updateSQL2);
				//设置参数
				pstmt.setDouble(1, car.getUseable_status());
				pstmt.setInt(2, id);
				
			}
			
			//执行sql
			int rows = pstmt.executeUpdate();
			System.out.println("修改了" + rows + "行");
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
		}
		
		
		
		
	}

	/**
	 * 用户 升序排列
	 */
	@Override
	public List<CarView> orderAsc() {
		ResultSet rs = null;
		List<CarView> userList = null;
		
		System.out.println("升序排列");
		try {
			conn = ConnectionFactory.getConnection();
			
			//查询主表 降序
			orderBy += "and useable <> 1 order by c.id";
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//建立一个ArrayList集合，将数据存进去
			userList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//创建一个CarView对象
				managerCarView = new ManagerCarView();
				//将数据存入对象中
				showMessage(rs, managerCarView);
				//将数据存入集合中
				userList.add(managerCarView);
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return userList;
		
		
	}
	
	/**
	 * 用户 降序排列
	 */
	@Override
	public List<CarView> orderDesc() {
		
		ResultSet rs = null;
		List<CarView> userList = null;
		
		System.out.println("降序排列");
		try {
			conn = ConnectionFactory.getConnection();
			
			//查询主表 降序
			orderBy += "and useable <> 1 order by c.id desc";
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//建立一个ArrayList集合，将数据存进去
			userList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//创建一个CarView对象
				managerCarView = new ManagerCarView();
				//将数据存入对象中
				showMessage(rs, managerCarView);
				//将数据存入集合中
				userList.add(managerCarView);
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return userList;
		
		
	}

	@Override
	public List<CarView> userFindById(int id, String sql) {
		ResultSet rs = null;
		List<CarView> list = null;
		
		try {
			//获取连接
			conn = ConnectionFactory.getConnection();
			//获取pstmt对象
			pstmt = conn.prepareStatement(sql);
			
			//设置参数
			pstmt.setInt(1, id);
			
			//执行sql语句
			rs = pstmt.executeQuery();
			
			//建立一个ArrayList集合，将数据存进去
			list = new ArrayList<>();
			while(rs.next()){
				//创建一个CarView对象
				CarView carView = new CarView();
				
				//将数据存入对象中
				showMessage(rs, carView);
				
				list.add(carView);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return list;
		
	}

	/**
	 * 将用户租车信息记录存入t_record表中
	 */
	@Override
	public void rentCar(int id,User user,RentRecord rentRecord) {
		//准备sql语句
		String rentSQL = "insert into t_record(id,user_id,car_id,start_date) "
				+ " values(t_record_id_seq.nextval,?,?,?)";
		
		
		try {
			
			//获取连接
			conn = ConnectionFactory.getConnection();
			
			//获取pstmt对象
			pstmt = conn.prepareStatement(rentSQL);
			
			//设置为手动回滚
			conn.setAutoCommit(false);
			//简历回滚点
			conn.setSavepoint();
			
			//设置参数
			pstmt.setInt(1, rentRecord.getUserId());
			pstmt.setInt(2, rentRecord.getCar_id());
//			pstmt.setDate(4, rentRecord.getReturnDate());
//			pstmt.setDouble(3, rentRecord.getPayment());
			pstmt.setDate(3, rentRecord.getStartDate());
			
			//执行sql
			int rows = pstmt.executeUpdate();
			
			System.out.println("更新了" + rows + "行");
			
			//提交事务
			conn.commit();
		} catch (SQLException e) {
			//回滚事务
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
			}
			throw new RuntimeException(e);
		}
		
		
	}

	/**
	 * 将所借的车的信息打印出来
	 */
	@Override
	public RentCarView rentCar(int id) {
		ResultSet rs = null;
		
		try {
			//准备sql语句
			String sql = "select c.id,c.model,c.rent,c.t_comments,b.name,t.name,r.start_date "
					+ "from t_car c,t_brand b,t_category t,t_record r "
					+ "where b.id = c.brand_id and t.id = c.category_id and c.id = r.car_id"
					+ " and c.id = ?";
			//获取连接对象
			conn = ConnectionFactory.getConnection();
			
			//获取预编译对象
			pstmt = conn.prepareStatement(sql);
			
			//设置参数
			pstmt.setInt(1, id);
			
			//执行sql语句
			rs = pstmt.executeQuery();
			
			//
			while(rs.next()){
				rentCarView = new RentCarView();
				
				rentCarView.setId(rs.getInt(1));
				rentCarView.setCar_name(rs.getString(2));
				rentCarView.setDay_rent(rs.getDouble(3));
				rentCarView.setCar_comments(rs.getString(4));
				rentCarView.setCar_brand(rs.getString(5));
				rentCarView.setCar_category(rs.getString(6));
				rentCarView.setRent_time(rs.getString(7));
				
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return rentCarView;
	}

	/**
	 * 查看租车记录
	 */
	@Override
	public List<RentCarView> findAllRecord() {
		ResultSet rs = null;
		List<RentCarView> list = null;
		try {
			conn = ConnectionFactory.getConnection();
			//查询副表
//			String findBrand = "select name from t_brand b,t_car c where b.id = c.brand_id";
			
			//查询租车记录
			String findAllSQL = "select r.id,r.car_id,c.model,r.payment,"
					+ "c.t_comments,t.name,b.name,start_date,return_date "
					+ " from t_record r,t_car c,t_category t,t_brand b "
					+ " where r.car_id = c.id and c.brand_id = b.id "
					+ " and c.category_id = t.id order by id";
			
			pstmt = conn.prepareStatement(findAllSQL);
			
			rs = pstmt.executeQuery();
			
			
			
			//建立一个ArrayList集合，将数据存进去
			list = new ArrayList<>();
			while(rs.next()){
				//创建一个RentRecord对象
				rentCarView = new RentCarView();
				
				//将数据存入对象中
				rentCarView.setId(rs.getInt(1));
				rentCarView.setCar_id(rs.getInt(2));
				rentCarView.setCar_name(rs.getString(3));
				rentCarView.setDay_rent(rs.getDouble(4));
				rentCarView.setCar_comments(rs.getString(5));
				rentCarView.setCar_brand(rs.getString(6));
				rentCarView.setCar_category(rs.getString(7));
				rentCarView.setRent_time(rs.getString(8));
				rentCarView.setReturn_time(rs.getString(9));
				
				//将对象存入集合中
				list.add(rentCarView);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return list;
		
	}

	/**
	 * 修改租赁状态
	 */
	/*@Override
	public void updateStatus(Car car,int id) {
		
		//准备sql语句
		String sql = "update t_car set status = ? where id = ?";
		
		try {
			//获取连接对象
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setInt(1, car.getRent_status());
			pstmt.setInt(2, id);
			//执行
			int rows = pstmt.executeUpdate();
			System.out.println("修改了" + rows +"行");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}*/
		
		
		
//	}

	/**
	 * 查询租赁时间
	 */
	@Override
	public Date queryRentTime(int id) {
		ResultSet rs = null;
		Date rent_time = null;
		
		try {
			//准备 sql
			String sql = "select start_date from t_record where car_id = ? and return_date is null";
			
			//获取连接对象
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			//设置参数
			pstmt.setInt(1, id);
			
			//执行
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				rent_time = rs.getDate(1);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		return rent_time;
	}
	
	
	
	

}
