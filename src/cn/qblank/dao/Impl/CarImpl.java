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
	//�������
	private String orderBy =  "select c.id,c.model,c.t_comments,b.name,t.name,c.price,c.rent,"
			+ "c.status,c.useable from t_car c,t_brand b,t_category t "
			+ "where b.id = c.brand_id and t.id = c.category_id ";
	

	@Override
	public List<CarView> findAllPost() {
		ResultSet rs = null;
		List<CarView> list = null;
		
		
		
		try {
			conn = ConnectionFactory.getConnection();
			
			//��ѯ����
			String findAllSQL = "select c.id,c.model,c.t_comments,b.name,t.name,c.price,c.rent,"
					+ "c.status from t_car c,t_brand b,t_category t "
					+ "where b.id = c.brand_id and t.id = c.category_id and useable <> 1 order by c.id";
			pstmt = conn.prepareStatement(findAllSQL);
			rs = pstmt.executeQuery();
			//����һ��ArrayList���ϣ������ݴ��ȥ
			list = new ArrayList<>();
			while(rs.next()){
				//����һ��CarView����
				carView = new CarView();
				//�����ݴ��������
				showMessage(rs,carView);
				//��������뼯����
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
	 * ��ʾ��ѯ������
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
	 * ������������
	 */
	@Override
	public List<ManagerCarView> findAllCar() {
		ResultSet rs = null;
		List<ManagerCarView> managerList = null;
		
		
		try {
			conn = ConnectionFactory.getConnection();
			//��ѯ����
			String findAllSQL = "select c.id,c.model,c.t_comments,b.name,t.name,c.price,c.rent,"
					+ "c.status,c.useable from t_car c,t_brand b,t_category t "
					+ "where b.id = c.brand_id and t.id = c.category_id order by c.id";
			//��Ϊ�ֶ��ύ����
			conn.setAutoCommit(false);
			//�����ع���
			conn.setSavepoint();
			pstmt = conn.prepareStatement(findAllSQL);
			rs = pstmt.executeQuery();
			//����һ��ArrayList���ϣ������ݴ��ȥ
			managerList = new ArrayList<>();
			while(rs.next()){
				//����һ��CarView����
				managerCarView = new ManagerCarView();
				//�����ݴ��������
				showMessage(rs, managerCarView);
				managerCarView.setUseable(rs.getInt(9));
				//�����ݴ��뼯����
				managerList.add(managerCarView);
				
			}
			
		} catch (Exception e) {
			//�ع�
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
	 * ��status��0ת��Ϊ��  1ת��Ϊ��
	 * @param status
	 * @return
	 */
	public String getStatus(int status){
		if(status==0){
			return "��";
		}else{
			return "��";
		}
		
	}
	
	/**
	 * ��useable��0ת��Ϊ��   1ת��Ϊ��
	 * @param useable
	 * @return
	 */
	public String getUseable(int useable){
		if (useable == 0) {
			return "�ϼ�";
		}else{
			return "�¼�";
		}
		
	}
	
	/**
	 * ͨ��id���� ����Ա
	 */
	@Override
	public List<ManagerCarView> findById(int id,String sql) {
		ResultSet rs = null;
		List<ManagerCarView> list = null;
		
		try {
			//��ȡ����
			conn = ConnectionFactory.getConnection();
			//��ȡpstmt����
			pstmt = conn.prepareStatement(sql);
			
			//���ò���
			pstmt.setInt(1, id);
			
			//ִ��sql���
			rs = pstmt.executeQuery();
			
			//����һ��ArrayList���ϣ������ݴ��ȥ
			list = new ArrayList<>();
			while(rs.next()){
				//����һ��ManagerCarView����
				managerCarView = new ManagerCarView();
				
				//�����ݴ��������
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
	 * �������
	 */
	@Override
	public void addCar(Car car) {
		
		try {
			//��ȡ����
			conn = ConnectionFactory.getConnection();
			//��Ϊ�ֶ��ύ����
			conn.setAutoCommit(false);
			//�����ع���
			conn.setSavepoint();
			//׼��sql���
			String insertSQL = "insert into t_car values(t_car_id_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			//׼��Ԥ�������
			pstmt = conn.prepareStatement(insertSQL);
			//���ò���
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
			
			//ִ��sql
			int rows = pstmt.executeUpdate();
			System.out.println("��ӳɹ�,������" + rows +"��");
			
			
		} catch (SQLException e) {
			try {
				//�ع�����
				conn.rollback();
				throw new RuntimeException(e);
			} catch (SQLException e1) {
				
			}
			
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
		}
		
		
	}

	/**
	 * ��ѯ�����������
	 */
	@Override
	public List<CarCategory> queryCarCategory() {
		ResultSet rs = null;
		List<CarCategory> carCategoryList = null;
		CarCategory category = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			//׼��sql���
			String queryCategory = "select id,name from t_category";
			//��Ϊ�ֶ��ύ����
			conn.setAutoCommit(false);
			
			//�����ع���
			conn.setSavepoint();
			pstmt = conn.prepareStatement(queryCategory);
			
			//ִ��
			rs = pstmt.executeQuery();
			
			carCategoryList = new ArrayList<>();
			
			while(rs.next()){
				//����һ��CarCategory����
				category = new CarCategory();
				
				/*rs.getInt(category.getCategory_id());
				rs.getString(category.getCategory_name());*/
				category.setCategory_id(rs.getInt(1));
				category.setCategory_name(rs.getString(2));
				
				//���뼯��
				carCategoryList.add(category);
				
			}
			
			
		} catch (SQLException e) {
			//�ع�
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
	 * ��ѯ��������Ʒ��
	 */
	@Override
	public List<CarBrand> queryCarBrand() {
		ResultSet rs = null;
		List<CarBrand> carBrandList = null;
		CarBrand carBrand = null;
		
		try {
			conn = ConnectionFactory.getConnection();
			//׼��sql���
			String queryBrand = "select id,name from t_brand";
			//��Ϊ�ֶ��ύ����
			conn.setAutoCommit(false);
			//�����ع���
			conn.setSavepoint();
			pstmt = conn.prepareStatement(queryBrand);
			
			//ִ��
			rs = pstmt.executeQuery();
			
			carBrandList = new ArrayList<>();
			
			while(rs.next()){
				//����һ��CarCategory����
				carBrand = new CarBrand();
				
				/*rs.getInt(carBrand.getBrand_id());
				rs.getString(carBrand.getBrand_name());*/
				carBrand.setBrand_id(rs.getInt(1));
				carBrand.setBrand_name(rs.getString(2));
				
				//���뼯��
				carBrandList.add(carBrand);
				
			}
			
			
		} catch (SQLException e) {
			//�ع�
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
	 * ��������
	 */
	@Override
	public List<ManagerCarView> orderByAsc() {
		ResultSet rs = null;
		List<ManagerCarView> managerList = null;
		
		
		try {
			conn = ConnectionFactory.getConnection();
			
			//��ѯ����
			orderBy += " order by c.price";
			
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//����һ��ArrayList���ϣ������ݴ��ȥ
			managerList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//����һ��CarView����
				managerCarView = new ManagerCarView();
				//�����ݴ��������
				showMessage(rs, managerCarView);
				managerCarView.setUseable(rs.getInt(9));
				//�����ݴ��뼯����
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
	 * ��������
	 */
	@Override
	public List<ManagerCarView> orderByDesc() {
		ResultSet rs = null;
		List<ManagerCarView> managerList = null;
		
		System.out.println("��������");
		try {
			conn = ConnectionFactory.getConnection();
			
			//��ѯ���� ����
			orderBy += " order by c.price desc";
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//����һ��ArrayList���ϣ������ݴ��ȥ
			managerList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//����һ��CarView����
				managerCarView = new ManagerCarView();
				//�����ݴ��������
				showMessage(rs, managerCarView);
				managerCarView.setUseable(rs.getInt(9));
				//�����ݴ��뼯����
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
	 * �޸������۸�
	 */
	@Override
	public void updateCar(int id,Car car,int updateSel) {
		
		
		try {
			//�������Ӷ���
			conn = ConnectionFactory.getConnection();
			
			//׼��sql���
			String updateSQL = "update t_car set price = ? where id = ?";
			String updateSQL2 = "update t_car set useable = ? where id = ?";
			
			if (updateSel == 1) {
				//�޸ļ۸�
				
				//��ȡPstmt����
				pstmt = conn.prepareStatement(updateSQL);
				//���ò���
				pstmt.setDouble(1, car.getCar_price());
				pstmt.setInt(2, id);
				
			}else if (updateSel == 2) {
				pstmt = conn.prepareStatement(updateSQL2);
				//���ò���
				pstmt.setDouble(1, car.getUseable_status());
				pstmt.setInt(2, id);
				
			}
			
			//ִ��sql
			int rows = pstmt.executeUpdate();
			System.out.println("�޸���" + rows + "��");
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			ConnectionFactory.close(conn, pstmt, null, null);
		}
		
		
		
		
	}

	/**
	 * �û� ��������
	 */
	@Override
	public List<CarView> orderAsc() {
		ResultSet rs = null;
		List<CarView> userList = null;
		
		System.out.println("��������");
		try {
			conn = ConnectionFactory.getConnection();
			
			//��ѯ���� ����
			orderBy += "and useable <> 1 order by c.id";
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//����һ��ArrayList���ϣ������ݴ��ȥ
			userList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//����һ��CarView����
				managerCarView = new ManagerCarView();
				//�����ݴ��������
				showMessage(rs, managerCarView);
				//�����ݴ��뼯����
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
	 * �û� ��������
	 */
	@Override
	public List<CarView> orderDesc() {
		
		ResultSet rs = null;
		List<CarView> userList = null;
		
		System.out.println("��������");
		try {
			conn = ConnectionFactory.getConnection();
			
			//��ѯ���� ����
			orderBy += "and useable <> 1 order by c.id desc";
			
			pstmt = conn.prepareStatement(orderBy);
			
			rs = pstmt.executeQuery();
			
			//����һ��ArrayList���ϣ������ݴ��ȥ
			userList = new ArrayList<>();
			
			
			
			while(rs.next()){
				//����һ��CarView����
				managerCarView = new ManagerCarView();
				//�����ݴ��������
				showMessage(rs, managerCarView);
				//�����ݴ��뼯����
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
			//��ȡ����
			conn = ConnectionFactory.getConnection();
			//��ȡpstmt����
			pstmt = conn.prepareStatement(sql);
			
			//���ò���
			pstmt.setInt(1, id);
			
			//ִ��sql���
			rs = pstmt.executeQuery();
			
			//����һ��ArrayList���ϣ������ݴ��ȥ
			list = new ArrayList<>();
			while(rs.next()){
				//����һ��CarView����
				CarView carView = new CarView();
				
				//�����ݴ��������
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
	 * ���û��⳵��Ϣ��¼����t_record����
	 */
	@Override
	public void rentCar(int id,User user,RentRecord rentRecord) {
		//׼��sql���
		String rentSQL = "insert into t_record(id,user_id,car_id,start_date) "
				+ " values(t_record_id_seq.nextval,?,?,?)";
		
		
		try {
			
			//��ȡ����
			conn = ConnectionFactory.getConnection();
			
			//��ȡpstmt����
			pstmt = conn.prepareStatement(rentSQL);
			
			//����Ϊ�ֶ��ع�
			conn.setAutoCommit(false);
			//�����ع���
			conn.setSavepoint();
			
			//���ò���
			pstmt.setInt(1, rentRecord.getUserId());
			pstmt.setInt(2, rentRecord.getCar_id());
//			pstmt.setDate(4, rentRecord.getReturnDate());
//			pstmt.setDouble(3, rentRecord.getPayment());
			pstmt.setDate(3, rentRecord.getStartDate());
			
			//ִ��sql
			int rows = pstmt.executeUpdate();
			
			System.out.println("������" + rows + "��");
			
			//�ύ����
			conn.commit();
		} catch (SQLException e) {
			//�ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				
			}
			throw new RuntimeException(e);
		}
		
		
	}

	/**
	 * ������ĳ�����Ϣ��ӡ����
	 */
	@Override
	public RentCarView rentCar(int id) {
		ResultSet rs = null;
		
		try {
			//׼��sql���
			String sql = "select c.id,c.model,c.rent,c.t_comments,b.name,t.name,r.start_date "
					+ "from t_car c,t_brand b,t_category t,t_record r "
					+ "where b.id = c.brand_id and t.id = c.category_id and c.id = r.car_id"
					+ " and c.id = ?";
			//��ȡ���Ӷ���
			conn = ConnectionFactory.getConnection();
			
			//��ȡԤ�������
			pstmt = conn.prepareStatement(sql);
			
			//���ò���
			pstmt.setInt(1, id);
			
			//ִ��sql���
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
	 * �鿴�⳵��¼
	 */
	@Override
	public List<RentCarView> findAllRecord() {
		ResultSet rs = null;
		List<RentCarView> list = null;
		try {
			conn = ConnectionFactory.getConnection();
			//��ѯ����
//			String findBrand = "select name from t_brand b,t_car c where b.id = c.brand_id";
			
			//��ѯ�⳵��¼
			String findAllSQL = "select r.id,r.car_id,c.model,r.payment,"
					+ "c.t_comments,t.name,b.name,start_date,return_date "
					+ " from t_record r,t_car c,t_category t,t_brand b "
					+ " where r.car_id = c.id and c.brand_id = b.id "
					+ " and c.category_id = t.id order by id";
			
			pstmt = conn.prepareStatement(findAllSQL);
			
			rs = pstmt.executeQuery();
			
			
			
			//����һ��ArrayList���ϣ������ݴ��ȥ
			list = new ArrayList<>();
			while(rs.next()){
				//����һ��RentRecord����
				rentCarView = new RentCarView();
				
				//�����ݴ��������
				rentCarView.setId(rs.getInt(1));
				rentCarView.setCar_id(rs.getInt(2));
				rentCarView.setCar_name(rs.getString(3));
				rentCarView.setDay_rent(rs.getDouble(4));
				rentCarView.setCar_comments(rs.getString(5));
				rentCarView.setCar_brand(rs.getString(6));
				rentCarView.setCar_category(rs.getString(7));
				rentCarView.setRent_time(rs.getString(8));
				rentCarView.setReturn_time(rs.getString(9));
				
				//��������뼯����
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
	 * �޸�����״̬
	 */
	/*@Override
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
		}*/
		
		
		
//	}

	/**
	 * ��ѯ����ʱ��
	 */
	@Override
	public Date queryRentTime(int id) {
		ResultSet rs = null;
		Date rent_time = null;
		
		try {
			//׼�� sql
			String sql = "select start_date from t_record where car_id = ? and return_date is null";
			
			//��ȡ���Ӷ���
			conn = ConnectionFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			//���ò���
			pstmt.setInt(1, id);
			
			//ִ��
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
