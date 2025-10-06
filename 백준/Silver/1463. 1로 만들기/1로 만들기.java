import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    
    static int[] dp;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int x = Integer.parseInt(br.readLine());
        dp = new int[x + 1];
        for(int i = 0; i<dp.length; i++) dp[i] = Integer.MAX_VALUE;
        
        dp[x] = 0;
        
        
        for(int i = x; i >= 1; i--){
            
            if(i % 3 == 0){
                int divThree = i / 3;
                dp[divThree] = Math.min(dp[i] + 1, dp[divThree]);
            }
            
            if(i % 2 == 0){
                int divTwo = i / 2;
                dp[divTwo] = Math.min(dp[i] + 1, dp[divTwo]);
            }
            
            if(i != 1){
                dp[i - 1] = Math.min(dp[i] + 1, dp[i - 1]);
            }
        }
        
        System.out.println(dp[1]);
        
    }
}