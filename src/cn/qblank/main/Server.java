package cn.qblank.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import cn.qblank.dao.Impl.LoginDaoImpl;
import cn.qblank.entity.User;

/**
 * �����
 * @author Administrator
 *
 */
public class Server extends Thread{
	Socket socket;
	static LoginDaoImpl loginDaoImpl = null;
	//��½�ɹ�
	public static final int SUCCESS = 1;
	//��½ʧ��
	public static final int FAILED = 0;
	
	public Server(Socket socket){
		this.socket = socket;
	}
	
	
	/**
	 * �ö��̴߳����û���½
	 */
	@Override
	public void run() {
		loginDaoImpl = new LoginDaoImpl();
		try {
			//����һ���û�
			User users = new User();
			
			//��ȡsocket������������
			BufferedReader bufferedReader = 
					new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			//��ȡsocket�����������
			/*OutputStreamWriter socketOut = 
				new OutputStreamWriter(socket.getOutputStream());*/
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			//��ȡ�ͻ��˵���Ϣ
			String info = bufferedReader.readLine();
			String[] datas = info.split(" ");
			
			
			//ע�� ��ȡ�û���������
			String userName = datas[0];
			String password = datas[1];
			int type = Integer.parseInt(datas[2]);
			
			users.setUserName(userName);
			users.setPassword(password);
			users.setType(type);
			
			//�û��ж��Ƿ���ڸ��˺�
			boolean flag = loginDaoImpl.testLogin(users);
			
			if (flag) {
				//��ʾ�û�����
				System.out.println(users.getUserName()+"�û�����");
				System.out.println();
				//���ڸ��û�
				writer.write(SUCCESS);	
				
			}else{
				//��ʾ�û�����
				System.out.println(users.getUserName()+"��½ʧ��");
				System.out.println();
				//�����ڸ��û�
				writer.write(FAILED);
			}
			//ˢ��
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �����������
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(9091);
			while(true){
				Socket socket = serverSocket.accept();
				//�����߳�
				new Server(socket).start();
				
			}
		} catch (IOException e) {
			try {
				//�׳��쳣�ر���Դ
				serverSocket.close();
			} catch (IOException e1) {
			}
			e.printStackTrace();
		}
		
	}
	
}
