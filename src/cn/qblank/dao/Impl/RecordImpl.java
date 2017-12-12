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
	 * �޸�����״̬
	 */
	@Override
	public void updateStatus(Car car,int id) {
		
		//׼��sql���
		String sql = "update t_car set status = ? where id = ?";
		
		try {
			//��ȡ���Ӷ���
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			//���ò���
			pstmt.setInt(1, car.getRent_status());
			pstmt.setInt(2, id);
			//ִ��
			int rows = pstmt.executeUpdate();
			System.out.println("�޸���" + rows +"��");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		
	}

	/**
	 * ��ȡ����״̬
	 */
	@Override
	public int rentStatus(int id) {
		ResultSet rs = null;
		int status = 0;
		
		try {
			//׼��sql���
			String sql = "select status from t_car where id = ?";
			
			
			//��ȡ����
			conn = ConnectionFactory.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			//ִ��
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
	 * ����   �ڱ��в���黹ʱ����踶���
	 */
	@Override
	public void returnCar(int id,Date returnTime, double money) {
		//׼��sql
		String sql = "update t_record set return_date = ?,payment = ? where car_id = ? and return_date is null";
		
		try {
			//��ȡ����
			conn = ConnectionFactory.getConnection();
			
			//����Ϊ�ֶ��ύ����
			conn.setAutoCommit(false);
			
			//�����ع���
			conn.setSavepoint();
			
			pstmt = conn.prepareStatement(sql);
			
			
			//���ò���
			pstmt.setDate(1, returnTime);
			pstmt.setDouble(2, money);
			pstmt.setInt(3, id);
			
			//ִ��
			int rows = pstmt.executeUpdate();
			System.out.println("������" + rows + "��");
			//�ύ����
			conn.commit();
		} catch (Exception e) {
			try {
				//�ع�
				conn.rollback();
			} catch (SQLException e1) {
				
			}
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
		}
		
		
		
		
	}

	/**
	 * ��ѯ������ÿ�����
	 */
	@Override
	public double queryCarMoney(int id) {
		ResultSet rs = null;
		double money = 0;
		
		try {
			//׼��sql���
			String sql = "select rent from t_car where id = ?";
			
			
			//��ȡ����
			conn = ConnectionFactory.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			//ִ��
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
