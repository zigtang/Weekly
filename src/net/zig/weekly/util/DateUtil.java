package net.zig.weekly.util;


public class DateUtil {

	public static DateUtil dateUtil;
	private DateUtil(){}
	
	public DateUtil getInstance(){
		if(dateUtil == null){
			dateUtil =new DateUtil();
		}
		return dateUtil;
	}
	
	
	public  String getWeekIndex(){
		
		return null;
	}
	
}
