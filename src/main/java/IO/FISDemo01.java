package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FISDemo01 {
    public static void main(String args[]) {
        String content = null;
        int size = 0;
        
        byte[] buffer = new byte[1024];
        try {
            System.out.println(System.getProperty("user.dir"));
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\" + "FOSDemo.txt");
            
            while ((size = fis.read(buffer)) != -1) {
                content = new String(buffer, 0, size);
                System.out.println(content);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
