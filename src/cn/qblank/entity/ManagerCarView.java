package cn.qblank.entity;

import java.io.Serializable;
/**
 * 封装管理员所看到的数据
 * @author Administrator
 *
 */
public class ManagerCarView extends CarView  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int useable;
	
	
	
	public ManagerCarView() {}

	

	

	public ManagerCarView(int id, String model, String comments,
			String brandName, String typeName, double price, double rent,
			int status) {
		super(id, model, comments, brandName, typeName, price, rent, status);
	}



	public ManagerCarView(int useable) {
		super();
		this.useable = useable;
	}





	public int getUseable() {
		return useable;
	}

	public void setUseable(int useable) {
		this.useable = useable;
	}
	
	
}
