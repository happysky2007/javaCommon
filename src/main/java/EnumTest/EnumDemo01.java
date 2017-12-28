package EnumTest;

public class EnumDemo01 {
    enum Color {
        RED,
        GREEN,
        BLUE;
    }
    
    public static void main(String[] args) {
        
        Color color = Color.BLUE;
        System.out.println(color);
        for (Color c : Color.values()) {
            // System.out.println(c);
            print(c);
        }
        
        for (Color c : Color.values()) {
            System.out.println(c.ordinal() + " -->" + c.name());
        }
        
    }
    
    public static void print(Color c) {
        switch (c) {
        case RED: {
            System.out.println("r");
            break;
        }
        case GREEN: {
            System.out.println("g");
            break;
        }
        case BLUE: {
            System.out.println("b");
            break;
        }
        default: {
            System.out.println("default");
            break;
        }
        
        }
    }
}
