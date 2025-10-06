import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
         String[] input = br.readLine().split(" ");
        
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        
        int[] coins = new int[n];
        int[] dp = new int[k + 1];
        
        for(int i = 0 ; i< coins.length; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        
        dp[0] = 0;
        for(int i = 1; i <= k; i++) dp[i] = Integer.MAX_VALUE;
        
        for(int coin : coins){
            for(int i = coin; i<=k; i++){
                if(dp[i - coin] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        
        int ans = dp[k] == Integer.MAX_VALUE ? -1 : dp[k];
        
        System.out.println(ans);
    }
}