package Mylist;

public class ThreadB extends Thread {
    private MyList list;
    
    public ThreadB(MyList list) {
        this.list = list;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            list.add("Thread B " + (i + 1));
        }
    }
    
}
