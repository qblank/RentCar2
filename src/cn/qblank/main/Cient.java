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
	 * 读取用户输入的信息 并将其发送到服务端
	 * @return
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static boolean readMessage(User users) throws IOException,
			UnknownHostException {
		boolean flag = false;
		
		BufferedReader br;
		Socket socket = new Socket(InetAddress.getLocalHost(), 9091);
		// 获取socket输出流对象
		OutputStreamWriter socketOut = new OutputStreamWriter(
				socket.getOutputStream());
		// 获取输入流对象
		BufferedReader socketReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		// 获取标准输入流对象
		br = new BufferedReader(new InputStreamReader(
				System.in));
		
		getInfo(socketOut, br, users);
		
		//读取服务器反馈的信息
		//判断是否成功  1表示成功  0表示失败
		int result = socketReader.read();
		if (result == 1) {
			flag = true;
		}else if (result == 0) {
			flag = false;
		}else{
			System.out.println("客户端没有接收到数据");
		}
		
		socket.close();
		return flag;
	}
	
	
	/**
	 * 管理员输入信息
	 * @param socketOut
	 * @param option
	 * @throws IOException 
	 */
	public static void getInfo(OutputStreamWriter socketOut,BufferedReader br,User users) throws IOException{
		String info = users.getUserName() + " " + users.getPassword() + " " + users.getType() +"\r\n";
		
		//将信息发送倒服务器中
		socketOut.write(info);
		//刷新
		socketOut.flush();
		
	}
}
