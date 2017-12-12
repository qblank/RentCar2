package cn.qblank.entity;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//���
	private int id;
	//�û���
	private String userName;	
	//����
	private String password;
	//�Ա�
	private int sex;
	//���֤��
	private String id_number;
	//�绰����
	private String tel;
	//��ַ
	private String addr;
	//����
	private int type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public User(){}
	
	
	
	public User(int id, String userName, String password, int sex, String id_number, String tel, String addr,
			int type) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.id_number = id_number;
		this.tel = tel;
		this.addr = addr;
		this.type = type;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", sex=" + sex + ", id_number="
				+ id_number + ", tel=" + tel + ", addr=" + addr + ", type=" + type + "]";
	}
	
	
}
