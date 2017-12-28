package Communication;

public class ThreadA extends Thread {
    private MyObject myobject;
    
    public ThreadA(MyObject myobject) {
        this.myobject = myobject;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        myobject.f1();
    }
    
}
