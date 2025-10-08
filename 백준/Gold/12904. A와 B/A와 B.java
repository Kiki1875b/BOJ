import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
         
        String S = br.readLine();
        String T = br.readLine();
        
        int steps = T.length() - S.length();
        
        String cur = T;
        int ans = 0;
        for(int t = 0; t<steps; t++){
            if(cur.charAt(cur.length() - 1) == 'A'){
                cur = cur.substring(0, cur.length() - 1);
            } else {
                cur = cur.substring(0, cur.length() - 1);
                cur = new StringBuilder(cur).reverse().toString();
            }
            
        }
        System.out.println(cur.equals(S) ? 1 : 0);
        
    }
    
}