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
 * 服务端
 * @author Administrator
 *
 */
public class Server extends Thread{
	Socket socket;
	static LoginDaoImpl loginDaoImpl = null;
	//登陆成功
	public static final int SUCCESS = 1;
	//登陆失败
	public static final int FAILED = 0;
	
	public Server(Socket socket){
		this.socket = socket;
	}
	
	
	/**
	 * 用多线程处理用户登陆
	 */
	@Override
	public void run() {
		loginDaoImpl = new LoginDaoImpl();
		try {
			//创建一个用户
			User users = new User();
			
			//获取socket的输入流对象
			BufferedReader bufferedReader = 
					new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			//获取socket的输出流对象
			/*OutputStreamWriter socketOut = 
				new OutputStreamWriter(socket.getOutputStream());*/
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			//读取客户端的信息
			String info = bufferedReader.readLine();
			String[] datas = info.split(" ");
			
			
			//注册 获取用户名和密码
			String userName = datas[0];
			String password = datas[1];
			int type = Integer.parseInt(datas[2]);
			
			users.setUserName(userName);
			users.setPassword(password);
			users.setType(type);
			
			//用户判断是否存在该账号
			boolean flag = loginDaoImpl.testLogin(users);
			
			if (flag) {
				//显示用户接入
				System.out.println(users.getUserName()+"用户接入");
				System.out.println();
				//存在该用户
				writer.write(SUCCESS);	
				
			}else{
				//显示用户接入
				System.out.println(users.getUserName()+"登陆失败");
				System.out.println();
				//不存在该用户
				writer.write(FAILED);
			}
			//刷新
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 服务端主方法
	 * @param args
	 */
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(9091);
			while(true){
				Socket socket = serverSocket.accept();
				//开启线程
				new Server(socket).start();
				
			}
		} catch (IOException e) {
			try {
				//抛出异常关闭资源
				serverSocket.close();
			} catch (IOException e1) {
			}
			e.printStackTrace();
		}
		
	}
	
}
