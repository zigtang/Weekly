package net.zig.weekly.util;

import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	public static DateUtil dateUtil;
	private Calendar calendar;
	private DateUtil(){
		calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(new Date());
	}
	
	public static DateUtil getInstance(){
		if(dateUtil == null){
			dateUtil =new DateUtil();
		}
		return dateUtil;
	}
	
	
	public  String getYearMonthWeek(){
		int year = calendar.get(calendar.YEAR);
		int month = calendar.get(calendar.MONTH);
		int week = calendar.get(calendar.WEEK_OF_MONTH);
		return year+"年"+month+"月第"+week+"周";
	}
	
	public int getTodayIndex(){
		return calendar.get(calendar.DAY_OF_WEEK)-1;
	}
	
	
	
}
