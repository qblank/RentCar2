package cn.qblank.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.qblank.dao.IloginDao;
import cn.qblank.entity.User;
import cn.qblank.util.ConnectionFactory;

public class LoginDaoImpl implements IloginDao{
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 测试连接
	 */
	@Override
	public boolean testLogin(User users) {
		boolean flag = false;
		
			
		if (getUser(users) != null && getPassword(users) != null) {
			flag = true;
		}
			
		return flag;
		
	}
	
	/**
	 * 获取用户的id
	 */
	@Override
	public int getUserId(User user){
		int userId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select id from t_user "
					+ "where username = ? and password = ? and type = ?";
			
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getType());
			
			//执行sql
			rs = pstmt.executeQuery();
			
			
			//判断是否有值
			if (rs.next()) {
				userId = rs.getInt("id");
			}
			
			
		} catch (SQLException e) {
			//测试
			e.printStackTrace();
		}finally{
			//关闭资源
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		
		return userId;
	}

	/**
	 * 获取用户名
	 */
	@Override
	public String getUser(User users) {
		String userName = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select * from t_user "
					+ "where username = ? and password = ? and type = ?";
			
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			pstmt.setInt(3, users.getType());
			
			//执行sql
			rs = pstmt.executeQuery();
			
			
			//判断是否有值
			if (rs.next()) {
				userName = rs.getString("userName");
				
			}
			
			
		} catch (SQLException e) {
			//测试
			e.printStackTrace();
		}finally{
			//关闭资源
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return userName;
	}
	
	
	/**
	 * 获取密码
	 */
	@Override
	public String getPassword(User users) {
		
		
		String password = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			//准备sql语句
			String sql = "select * from t_user "
					+ "where username = ? and password = ? and type = ?";
			
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			pstmt.setInt(3, users.getType());
			
			//执行sql
			rs = pstmt.executeQuery();
			
			
			//判断是否有值
			if (rs.next()) {
				password = rs.getString("password");
				
			}
			
			
		} catch (SQLException e) {
			//测试
			e.printStackTrace();
		}finally{
			//关闭资源
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return password;
		
		
	}
	
	/**
	 * 注册用户
	 */
	@Override
	public void registerUser(User users) {
		//注册用户
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String registerSQL = "insert into t_user values(t_user_id_seq.nextval,?,?,?,?,?,?,?)";
			
			//获取预编译对象
			pstmt = conn.prepareStatement(registerSQL);
			
			//设置参数
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			pstmt.setInt(3, users.getSex());
			pstmt.setString(4, users.getId_number());
			pstmt.setString(5, users.getTel());
			pstmt.setString(6, users.getAddr());
			pstmt.setInt(7, users.getType());
			
			//编译
			int rows = pstmt.executeUpdate();
			System.out.println("更新了" + rows + "行");
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
			
		}
		
		
	}
	
	/**
	 * 获取汽车的每日租金
	 */
	@Override
	public double getRentMoney(int id) {
		double money = 0;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select rent from t_car "
					+ "where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			//设置参数
			pstmt.setInt(1, id);
			
			//执行sql
			rs = pstmt.executeQuery();
			//判断是否有值
			if (rs.next()) {
				money = rs.getDouble("rent");
			}
			
			
		} catch (SQLException e) {
			//测试
			e.printStackTrace();
		}finally{
			//关闭资源
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return money;
	}
	
	

	

}
