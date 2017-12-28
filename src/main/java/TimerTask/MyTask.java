package TimerTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTask extends TimerTask {
    
    @Override
    public void run() {
        SimpleDateFormat sdf = null;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("current time : " + sdf.format(new Date()));
    }
    
    public static void main(String args[]) {
        Timer timer = new Timer();
        MyTask mytask = new MyTask();
        // timer.schedule(mytask, 1000, 2000);
        timer.scheduleAtFixedRate(mytask, 1000, 5000);
        
    }
    
}
