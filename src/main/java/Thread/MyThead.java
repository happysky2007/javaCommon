package Thread;

class TheadMain implements Runnable {
    public void run() {
        System.out.println("我休息了！");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("一秒后在叫我吧！");
    }
}

public class MyThead {
    public static void main(String[] args) {
        TheadMain myThead = new TheadMain();
        Thread thread = new Thread(myThead);
        thread.start();
    }
}