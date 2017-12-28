package Mylist;

public class ThreadA extends Thread {
    private MyList list;
    
    public ThreadA(MyList list) {
        this.list = list;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            list.add("ThreaA " + (i + 1));
        }
    }
    
}
