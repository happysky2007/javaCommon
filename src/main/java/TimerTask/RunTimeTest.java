package TimerTask;

public class RunTimeTest {
    
    public static void main(String args[]) {
        Runtime run = Runtime.getRuntime();
        try {
            Process pro = run.exec("notepad.exe");
            Thread.sleep(5000);
            pro.destroy();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
