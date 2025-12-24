import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        
        int[] coins = new int[N];
        
        for(int i = 0; i<N; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        
        int[] dp = new int[K + 1]; // dp[i] = k원을 만들 수 있는 최소 동전 수
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for(int i = 0; i<N; i++){
            int coin = coins[i];
            
            for(int j = coin; j <= K ; j++){
                if(dp[j - coin] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
                }
            }
        }
        
        System.out.println(dp[K] == Integer.MAX_VALUE ? -1 : dp[K]);
    }
}