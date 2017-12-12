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
	 * ��������
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
	 * ��ȡ�û���id
	 */
	@Override
	public int getUserId(User user){
		int userId = 0;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select id from t_user "
					+ "where username = ? and password = ? and type = ?";
			
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getType());
			
			//ִ��sql
			rs = pstmt.executeQuery();
			
			
			//�ж��Ƿ���ֵ
			if (rs.next()) {
				userId = rs.getInt("id");
			}
			
			
		} catch (SQLException e) {
			//����
			e.printStackTrace();
		}finally{
			//�ر���Դ
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		
		return userId;
	}

	/**
	 * ��ȡ�û���
	 */
	@Override
	public String getUser(User users) {
		String userName = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select * from t_user "
					+ "where username = ? and password = ? and type = ?";
			
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			pstmt.setInt(3, users.getType());
			
			//ִ��sql
			rs = pstmt.executeQuery();
			
			
			//�ж��Ƿ���ֵ
			if (rs.next()) {
				userName = rs.getString("userName");
				
			}
			
			
		} catch (SQLException e) {
			//����
			e.printStackTrace();
		}finally{
			//�ر���Դ
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return userName;
	}
	
	
	/**
	 * ��ȡ����
	 */
	@Override
	public String getPassword(User users) {
		
		
		String password = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			//׼��sql���
			String sql = "select * from t_user "
					+ "where username = ? and password = ? and type = ?";
			
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			pstmt.setInt(3, users.getType());
			
			//ִ��sql
			rs = pstmt.executeQuery();
			
			
			//�ж��Ƿ���ֵ
			if (rs.next()) {
				password = rs.getString("password");
				
			}
			
			
		} catch (SQLException e) {
			//����
			e.printStackTrace();
		}finally{
			//�ر���Դ
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return password;
		
		
	}
	
	/**
	 * ע���û�
	 */
	@Override
	public void registerUser(User users) {
		//ע���û�
		
		try {
			conn = ConnectionFactory.getConnection();
			
			String registerSQL = "insert into t_user values(t_user_id_seq.nextval,?,?,?,?,?,?,?)";
			
			//��ȡԤ�������
			pstmt = conn.prepareStatement(registerSQL);
			
			//���ò���
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			pstmt.setInt(3, users.getSex());
			pstmt.setString(4, users.getId_number());
			pstmt.setString(5, users.getTel());
			pstmt.setString(6, users.getAddr());
			pstmt.setInt(7, users.getType());
			
			//����
			int rows = pstmt.executeUpdate();
			System.out.println("������" + rows + "��");
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
			
		}
		
		
	}
	
	/**
	 * ��ȡ������ÿ�����
	 */
	@Override
	public double getRentMoney(int id) {
		double money = 0;
		try {
			conn = ConnectionFactory.getConnection();
			String sql = "select rent from t_car "
					+ "where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setInt(1, id);
			
			//ִ��sql
			rs = pstmt.executeQuery();
			//�ж��Ƿ���ֵ
			if (rs.next()) {
				money = rs.getDouble("rent");
			}
			
			
		} catch (SQLException e) {
			//����
			e.printStackTrace();
		}finally{
			//�ر���Դ
			ConnectionFactory.close(conn, pstmt, null, rs);
		}
		
		
		return money;
	}
	
	

	

}
