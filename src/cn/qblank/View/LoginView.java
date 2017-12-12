package cn.qblank.View;

import java.util.List;

import cn.qblank.dao.Impl.CarImpl;
import cn.qblank.entity.CarView;
import cn.qblank.entity.ManagerCarView;
import cn.qblank.entity.User;
import cn.qblank.service.ManagerLogin;
import cn.qblank.service.UserLogin;

public class LoginView {
	CarImpl carImpl = null;

	/**
	 * ������
	 */
	public void mainLoginView() {

		System.out.println("��ѡ����Ľ�ɫ:0.��ͨ�û�\t1.����Ա");
	}

	/**
	 * �û�ҳ��
	 */
	public void userMainView() {
		System.out.println("�û�����");
		System.out.println("===============");
		System.out.println("��ӭ������ʽ�⳵��");
		System.out.println("===============");

		System.out.println("1.��½\t2.ע��\t3.�˳�");
	}

	/**
	 * �û���½ҳ��
	 */
	public void userLoginView() {
		System.out.println("�û�����");
		System.out.println("========��½=======");

	}

	/**
	 * ��½����ҳ��
	 * 
	 * @throws Exception
	 */
	public void loginErrorView() throws Exception {
		System.out.println("�˺Ż����벻���ڣ������µ�½");
		System.out.println("------------------------");

	}

	/**
	 * �û�ע��ҳ��
	 */
	public void userRegisterView() {
		System.out.println("==========ע��ҳ��==========");

	}

	/**
	 * ע��ʧ��ҳ��
	 * 
	 * @throws Exception
	 */
	public void registerErrorView(User users) throws Exception {
		System.out.println("������������벻һ��,����������");
		UserLogin.userRegister(users);

	}

	/**
	 * ����Ա��½ҳ��
	 */
	public void managerLoginView() {
		System.out.println("����Ա����");
		System.out.println("===============");
		System.out.println("��ӭ������ʽ�⳵��");
		System.out.println("===============");
		System.out.println("1.��½\t2.�˳�");

	}

	/**
	 * ����Ա��½ʧ��ҳ��
	 * 
	 * @throws Exception
	 */
	public void managerLoginErrorView() throws Exception {
		System.out.println("�˺Ż����벻���ڣ������µ�½");
		System.out.println("------------------------");
		// ����ȷ���ص�½ҳ��
		ManagerLogin.manageLogin();

	}

	/**
	 * �û���¼�ɹ����������
	 */
	public void userLoginSuccessView() {

		for (int i = 0; i < 60; i++) {
			System.out.print("=");
		}

		System.out.println();
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t\t���\t�Ƿ����");

	}

	/**
	 * ��ʾ�û�ȫ����������ͼ
	 */
	public void showUserCarView() {
		// ����һ��CarImpl����
		carImpl = new CarImpl();

		// ����һ��List���� ���ڽ�������
		List<CarView> list = null;

		// ��������
		list = carImpl.findAllPost();

		// ���������û�������������ʾ����
		for (CarView carView : list) {
			System.out.println(carView.getId() + "\t" + carView.getModel()
					+ "\t" + carView.getComments() + "\t"
					+ carView.getBrandName() + "\t" + carView.getTypeName()
					+ "\t" + carView.getPrice() + "\t"
					+ carView.getRent() + "\t"
					+ getStatus(carView.getStatus()));
		}

	}
	

	/**
	 * ��ʾ����Աȫ����������ͼ
	 */
	public void showManagerCarView() {
		// ����һ��CarImpl����
		carImpl = new CarImpl();

		// ����һ��List���� ���ڽ�������
		List<ManagerCarView> managerCarViews = carImpl.findAllCar();

		// ������������Ա��������Ϣ��ʾ����
		for (ManagerCarView managerCarView : managerCarViews) {
			System.out.println(managerCarView.getId() + "\t"
					+ managerCarView.getModel() + "\t"
					+ managerCarView.getComments() + "\t"
					+ managerCarView.getBrandName() + "\t"
					+ managerCarView.getTypeName() + "\t"
					+ managerCarView.getPrice() + "\t"
					+ managerCarView.getRent() + "\t"
					+ getStatus(managerCarView.getStatus()) + "\t"
					+ getUseable(managerCarView.getUseable()));

		}

	}

	/**
	 * �û���������ͼ
	 */
	public void userPlayView() {
		System.out.println("����0�˳�");
		System.out.println("����1+�������    �����⳵����  ��: 1+2");
		System.out.println("����2+1  ���۸���������      ����2+2  ���۸�������");
		System.out.println("����3+���ͱ��    ����������");
		System.out.println("����4+Ʒ�Ʊ��    ��Ʒ������");
		System.out.println("����5        �鿴ȫ������");
		System.out.println("����6   �鿴�ҵ��⳵��¼");
		System.out.println("����7+�������      ����");

	}

	/**
	 * ����Ա��½�ɹ�����
	 */
	public void managerLoginSuccessView() {
		for (int i = 0; i < 60; i++) {
			System.out.print("=");
		}

		System.out.println();
		System.out.println("���\t��������\t��ע\tƷ��\t����\t�۸�\t\t���\t�Ƿ����\t�Ƿ��ϼ�");

	}

	/**
	 * ����Ա��������
	 */
	public void managerPlayView() {
		System.out.println("����0     �˳�");
		System.out.println("����1+�������     �鿴ָ���������");
		System.out.println("����2+1  ���۸���������      ����2+2  ���۸�������");
		System.out.println("����3+���ͱ��     ����������");
		System.out.println("����4+Ʒ�Ʊ��     ��Ʒ������");
		System.out.println("����5     �鿴ȫ������");
		System.out.println("����6     �������");
		System.out.println("����7+�������     �޸�������Ϣ");
		System.out.println("����8     �鿴������¼");

	}

	/**
	 * ��status��0ת��Ϊ�� 1ת��Ϊ��
	 * 
	 * @param status
	 * @return
	 */
	public String getStatus(int status) {
		if (status == 0) {
			return "��";
		} else {
			return "��";
		}

	}

	/**
	 * ��useable��0ת��Ϊ�� 1ת��Ϊ��
	 * 
	 * @param useable
	 * @return
	 */
	public String getUseable(int useable) {
		if (useable == 0) {
			return "�ϼ�";
		} else {
			return "�¼�";
		}

	}

}
