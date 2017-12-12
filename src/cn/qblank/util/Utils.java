package cn.qblank.util;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cn.qblank.dao.Impl.RecordImpl;

//import org.junit.Test;

/**
 * ��ȡʱ�����
 * @author Administrator
 *
 */
public class Utils {
	
	/**
	 * ��ȡ�賵��ʱ��
	 * @return long
	 */
	public static long getRentTime(){
		
		return System.currentTimeMillis();
		
	}
	
	/**
	 * ��ȡ�⳵ʱ��
	 * @return String
	 */
	public static Date getRentTimes(){
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		Date rentTime = null;
		
		try {
			java.util.Date date2 =  sdf.parse(sdf.format(date));
			rentTime = new Date(date2.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return rentTime;
	}
	
	/**
	 * ��ȡ����ʱ��
	 * @return String
	 */
	public static Date getReturnTimes(){
		
		return getRentTimes();
		
	}
	
	/**
	 * ��ȡ����ʱ��
	 * @return long
	 */
	public static  long getReturnTime(){
		
		return System.currentTimeMillis();
	}
	
	
	/**
	 * ��ȡ�賵������
	 * @return
	 */
	public static  long getDays(long rentTime,long returnTime){
		
		return (returnTime - rentTime)/1000/3600/24 +1;		//����һ��Ҳ��һ��
		
	}
	
	/**
	 * ��ȡ�ܽ��
	 * @param id
	 * @param returnTimes
	 * @param rentTimes
	 * @return
	 */
	public static double getTotalMoney(int id, Date returnTimes, Date rentTimes,RecordImpl recordImpl) {
		long day = (returnTimes.getTime() - rentTimes.getTime())/(1000*60*60*24);
		
		//��ȡ��������ÿ�����
		double dayRentMoney = recordImpl.queryCarMoney(id);
		double totalMoney = 0;
		
		if (day > 1) {
			//���踶���ܽ��Ϊ
			totalMoney = day*dayRentMoney;
		}else if (day <= 1) {
			//����С��һ�죬��һ����
			totalMoney = dayRentMoney;
			
		}
		return totalMoney;
	}
	
	
}
