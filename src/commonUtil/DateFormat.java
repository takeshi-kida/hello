package commonUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
	static public final String DATE_PATTERN ="yyyy-MM-dd HH:mm:ss";

	public static String dateFormat(Timestamp date)
	{
		return new SimpleDateFormat(DATE_PATTERN).format(date);
	}

	public static String dateFormat(Date date)
	{
		return new SimpleDateFormat(DATE_PATTERN).format(date);
	}
}
