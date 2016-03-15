package colin.miniblog.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by joker on 16-3-12.
 */
public class ColinDateUtils {
    private static Calendar calendar = Calendar.getInstance();

    /**
     * 获取当前的数据库时间，精确到秒
     *
     * @return
     */
    public static Timestamp generateCurrentTimestamp() {
        return new Timestamp(calendar.getTime().getTime());
    }

    /**
     * 获取当前的日期，精确到天
     *
     * @return
     */
    public static Date generateCurrentDate() {
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 返回当前的日期，格式化为 YYYY-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String generateCurrentDatetimeStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 返回当前的日期，格式化为 YYYY-MM-dd
     *
     * @return
     */
    public static String generateCurrentDateStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        return dateFormat.format(calendar.getTime());

    }
}
