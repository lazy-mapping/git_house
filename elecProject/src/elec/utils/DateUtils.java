package elec.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/**将日期类型转换成String类型，精确到年月日时分秒*/
	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**将String类型转换成日期类型，精确到年月日*/
	public static Date stringToDate(String sDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(sDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
