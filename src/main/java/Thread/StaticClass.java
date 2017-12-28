package Thread;

public class StaticClass extends Thread {
    private MyClass myclass;
    
    public StaticClass(MyClass myclass) {
        this.myclass = myclass;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        myclass.f1();
    }
    
}
