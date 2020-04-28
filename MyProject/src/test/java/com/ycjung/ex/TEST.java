package com.ycjung.ex;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TEST {
	
	public static void main(String[] args) {
		/*String currentDate = new SimpleDateFormat("yy-MM-dd hh:MM:ss").format(new java.util.Date());
        System.out.println(currentDate);*/
//		String currentDate = new SimpleDateFormat("yy-MM-dd hh:MM:ss").format(new java.sql.Date(System.currentTimeMillis()));
//		System.out.println(currentDate);
//		
//		System.out.println(new Date(System.currentTimeMillis()));
//		Date date = new Date(System.currentTimeMillis());
//		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
//		System.out.println(d);
//		
//		d.setTime(date.getTime());
//		
//		System.out.println(d);
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		System.out.println(ts);
	}
}

