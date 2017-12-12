package cn.qblank.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.qblank.dao.IRecord;
import cn.qblank.entity.Car;
import cn.qblank.util.ConnectionFactory;

public class RecordImpl implements IRecord{
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	
	/**
	 * 修改租赁状态
	 */
	@Override
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
		}
		
		
		
	}

	/**
	 * 获取租赁状态
	 */
	@Override
	public int rentStatus(int id) {
		ResultSet rs = null;
		int status = 0;
		
		try {
			//准备sql语句
			String sql = "select status from t_car where id = ?";
			
			
			//获取连接
			conn = ConnectionFactory.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			//执行
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				status = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return status;
	}

	/**
	 * 还车   在表中插入归还时间和需付金额
	 */
	@Override
	public void returnCar(int id,Date returnTime, double money) {
		//准备sql
		String sql = "update t_record set return_date = ?,payment = ? where car_id = ? and return_date is null";
		
		try {
			//获取连接
			conn = ConnectionFactory.getConnection();
			
			//设置为手动提交事务
			conn.setAutoCommit(false);
			
			//建立回滚点
			conn.setSavepoint();
			
			pstmt = conn.prepareStatement(sql);
			
			
			//设置参数
			pstmt.setDate(1, returnTime);
			pstmt.setDouble(2, money);
			pstmt.setInt(3, id);
			
			//执行
			int rows = pstmt.executeUpdate();
			System.out.println("更新了" + rows + "行");
			//提交事务
			conn.commit();
		} catch (Exception e) {
			try {
				//回滚
				conn.rollback();
			} catch (SQLException e1) {
				
			}
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
		}
		
		
		
		
	}

	/**
	 * 查询汽车的每日租金
	 */
	@Override
	public double queryCarMoney(int id) {
		ResultSet rs = null;
		double money = 0;
		
		try {
			//准备sql语句
			String sql = "select rent from t_car where id = ?";
			
			
			//获取连接
			conn = ConnectionFactory.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			//执行
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				money = rs.getDouble(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		return money;
		
	}

}
