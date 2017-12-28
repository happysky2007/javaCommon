package Thread;

public class StaticClass2 extends Thread {
    private MyClass myclass;
    
    public StaticClass2(MyClass myclass) {
        this.myclass = myclass;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        myclass.f2();
    }
    
}
