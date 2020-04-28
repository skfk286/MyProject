package com.ycjung.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDateFormat {
	
	public String formattedDate(String date, String fromFormatString, String toFormatString) {
		SimpleDateFormat fromFormat = new SimpleDateFormat(fromFormatString);
		SimpleDateFormat toFormat = new SimpleDateFormat(toFormatString);
		Date fromDate = null;
		
		try {
			fromDate = fromFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return toFormat.format(fromDate);
	}
	
	public String formattedDate(Date date, String format) {
		SimpleDateFormat toFormat = new SimpleDateFormat(format);
		return toFormat.format(date);
	}
	
	// LongŸ���� ��¥�� fromFromatString ������ String���� ��ȯ���ش�.
	public String formattedDate(String fromFormatString, Long today) {
		DateFormat df = new SimpleDateFormat(fromFormatString);
		return df.format(today);
	}
	
}
