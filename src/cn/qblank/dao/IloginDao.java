package cn.qblank.dao;

import cn.qblank.entity.User;

public interface IloginDao {
	/**
	 * ���Ե�½
	 * @param users
	 * @return
	 */
	public boolean testLogin(User users);
	
	/**
	 * ��ȡ�û���
	 * @param users
	 * @return
	 */
	public String getUser(User users);
	
	/**
	 * ��ȡ����
	 * @param users
	 * @return
	 */
	public String getPassword(User users);
	/**
	 * ע���û�
	 */
	public void registerUser(User users);
	
	/**
	 * ��ȡ�û��ı��
	 * 
	 */
	public int getUserId(User user);
	
	/**
	 * ��ȡ�����ı��
	 */
	public double getRentMoney(int id);
}
