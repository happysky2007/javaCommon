package GenericTest;

public class GenericTest {
    public static void main(String args[]) {
        
        Point p = new Point();
        p.setX(10);
        p.setY(20);
        
        int x = (Integer) p.getX();
        int y = (Integer) p.getY();
        
    }
    
}
