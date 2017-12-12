package cn.qblank.dao;

import cn.qblank.entity.User;

public interface IloginDao {
	/**
	 * 测试登陆
	 * @param users
	 * @return
	 */
	public boolean testLogin(User users);
	
	/**
	 * 获取用户名
	 * @param users
	 * @return
	 */
	public String getUser(User users);
	
	/**
	 * 获取密码
	 * @param users
	 * @return
	 */
	public String getPassword(User users);
	/**
	 * 注册用户
	 */
	public void registerUser(User users);
	
	/**
	 * 获取用户的编号
	 * 
	 */
	public int getUserId(User user);
	
	/**
	 * 获取汽车的编号
	 */
	public double getRentMoney(int id);
}
