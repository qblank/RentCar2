package cn.qblank.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.qblank.entity.User;

public class Cient {
	/**
	 * ��ȡ�û��������Ϣ �����䷢�͵������
	 * @return
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static boolean readMessage(User users) throws IOException,
			UnknownHostException {
		boolean flag = false;
		
		BufferedReader br;
		Socket socket = new Socket(InetAddress.getLocalHost(), 9091);
		// ��ȡsocket���������
		OutputStreamWriter socketOut = new OutputStreamWriter(
				socket.getOutputStream());
		// ��ȡ����������
		BufferedReader socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		// ��ȡ��׼����������
		br = new BufferedReader(new InputStreamReader(
				System.in));
		
		getInfo(socketOut, br, users);
		
		//��ȡ��������������Ϣ
		//�ж��Ƿ�ɹ�  1��ʾ�ɹ�  0��ʾʧ��
		int result = socketReader.read();
		if (result == 1) {
			flag = true;
		}else if (result == 0) {
			flag = false;
		}else{
			System.out.println("�ͻ���û�н��յ�����");
		}
		
		socket.close();
		return flag;
	}
	
	
	/**
	 * ����Ա������Ϣ
	 * @param socketOut
	 * @param option
	 * @throws IOException 
	 */
	public static void getInfo(OutputStreamWriter socketOut,BufferedReader br,User users) throws IOException{
		String info = users.getUserName() + " " + users.getPassword() + " " + users.getType() +"\r\n";
		
		//����Ϣ���͵���������
		socketOut.write(info);
		//ˢ��
		socketOut.flush();
		
	}
}
