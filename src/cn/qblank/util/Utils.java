package cn.qblank.util;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cn.qblank.dao.Impl.RecordImpl;

//import org.junit.Test;

/**
 * 获取时间的类
 * @author Administrator
 *
 */
public class Utils {
	
	/**
	 * 获取借车的时间
	 * @return long
	 */
	public static long getRentTime(){
		
		return System.currentTimeMillis();
		
	}
	
	/**
	 * 获取租车时间
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
	 * 获取还车时间
	 * @return String
	 */
	public static Date getReturnTimes(){
		
		return getRentTimes();
		
	}
	
	/**
	 * 获取还车时间
	 * @return long
	 */
	public static  long getReturnTime(){
		
		return System.currentTimeMillis();
	}
	
	
	/**
	 * 获取借车的天数
	 * @return
	 */
	public static  long getDays(long rentTime,long returnTime){
		
		return (returnTime - rentTime)/1000/3600/24 +1;		//少于一天也算一天
		
	}
	
	/**
	 * 获取总金额
	 * @param id
	 * @param returnTimes
	 * @param rentTimes
	 * @return
	 */
	public static double getTotalMoney(int id, Date returnTimes, Date rentTimes,RecordImpl recordImpl) {
		long day = (returnTimes.getTime() - rentTimes.getTime())/(1000*60*60*24);
		
		//获取改汽车的每日租金
		double dayRentMoney = recordImpl.queryCarMoney(id);
		double totalMoney = 0;
		
		if (day > 1) {
			//所需付的总金额为
			totalMoney = day*dayRentMoney;
		}else if (day <= 1) {
			//天数小于一天，按一天算
			totalMoney = dayRentMoney;
			
		}
		return totalMoney;
	}
	
	
}
