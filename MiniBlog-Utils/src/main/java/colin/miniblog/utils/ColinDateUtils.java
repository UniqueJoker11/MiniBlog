package colin.miniblog.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 创建人 LinQiang
 * 创建日期 2016/3/25
 * 项目名称 MiniBlog
 * 当前包名 colin.miniblog.utils
 */
public class ColinDateUtils {
  private static Calendar calendar=Calendar.getInstance();
  public static Timestamp getCurrentDate(){
    Timestamp timestamp=new Timestamp(calendar.getTime().getTime());
    return timestamp;
  }
}
