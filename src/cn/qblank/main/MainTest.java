package cn.qblank.main;

import cn.qblank.service.Login;

public class MainTest {
	public static void main(String[] args) {
		try {
			Login.login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
