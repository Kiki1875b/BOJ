import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    
    static int T, N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        
        for(int c = 0; c < T; c++){
            N = Integer.parseInt(br.readLine());

            int[] coins = new int[N];
            String[] tmp = br.readLine().split(" ");
            for(int i = 0; i<N; i++) coins[i] = Integer.parseInt(tmp[i]);
            
            int target = Integer.parseInt(br.readLine());
            
            int[] dp = new int[target + 1];
            
            dp[0] = 1;
            
      
            for(int coin: coins){
                for(int i = coin; i <= target; i++){
                    dp[i] += dp[i - coin]; 
                }
            }
            
            System.out.println(dp[target]);
        }
        
    }
}