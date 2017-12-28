package LuceneUtilTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MockDataUtil {
    public static final Integer LENGTH = 6;
    public static final String[] IDS = { "1", "2", "3", "4", "5", "6" };
    public static final String[] EMAILS = { "aa@wdxxl.com", "aa@wdxxl", "aa@wdxxl", "aa@wdxxl.com", "aa@wdxxl.com",
            "aa@wdxxl.com" };
    public static final String[] CONTENTS = { "like1", "like2", "like2", "like4", "like4", "like5" };
    
    public static final String[] NAMES = { "Lucy", "LiLi", "Lilei", "Polly", "SunHuiMing", "Hanmeimei" };
    public static final int[] ATTACHS = { 1, 1, 1, 1, 1, 1 };
    public static final Date[] DATES;
    public static Map<String, Float> SCORES = new HashMap<String, Float>();
    
    static {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DATES = new Date[6];
        try {
            DATES[0] = sdf.parse("2013-05-12");
            DATES[1] = sdf.parse("2013-05-12");
            DATES[2] = sdf.parse("2013-05-12");
            DATES[3] = sdf.parse("2013-05-12");
            DATES[4] = sdf.parse("2013-05-12");
            DATES[5] = sdf.parse("2013-05-12");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // For Boost
        SCORES.put("wdxxl.com", 2.0f);
        SCORES.put("sinobot.org", 1.5f);
    }
}
