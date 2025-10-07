import java.io.*; 
import java.util.*;

public class Main {
    
    public static void main(String[] args ) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        int n = Integer.parseInt(br.readLine());
        int ans = Integer.MAX_VALUE;
        
        for(int i = n / 5; i >= 0; i--){
            int current = n;
            int bags = i;
            
            current -= (bags * 5);
            
            if(current % 3 == 0){
                bags += current / 3;
                
                ans = Math.min(bags, ans);
            }
        }
        
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}