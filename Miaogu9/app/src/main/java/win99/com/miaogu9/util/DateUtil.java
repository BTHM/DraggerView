package win99.com.miaogu9.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author sanshu
 * @data 16/9/20 下午3:41
 * @ToDo ${TODO}
 */

public class DateUtil {

    private static final String TAG = DateUtil.class.getName();

    public static String LongToDate(long mill) {
        Date date = new Date(mill);
        //yyyy表示年MM表示月dd表示日
        //yyyy-MM-dd是日期的格式，比如2015-12-12如果你要得到2015年12月12日就换成yyyy年MM月dd日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String format = sdf.format(date);
        LogUtil.d(TAG, format);

        return format;
    }
}
