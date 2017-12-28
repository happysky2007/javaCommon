package Mylist;

import java.util.ArrayList;
import java.util.List;

public class MyList {
    private List list = new ArrayList();
    
    public synchronized void add(String username) {
        System.out.println("Thread name  = " + Thread.currentThread().getName() + "execute add() ");
        list.add(username);
        System.out.println("Thread name  = " + Thread.currentThread().getName() + "exit add() ");
    }
    
    public synchronized int getSize() {
        System.out.println("Thread name = " + Thread.currentThread().getName() + "execute getSize()");
        int sizeValue = list.size();
        System.out.println("Thread name = " + Thread.currentThread().getName() + "exit getSize()");
        return sizeValue;
    }
}
