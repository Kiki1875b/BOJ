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
        
        int[] dp = new int[K + 1]; // dp[i] = i 원을 만들 수 있는 경우의 수
        Arrays.fill(dp, 0);
        dp[0] = 1;
        
        for(int i = 0; i<N; i++){
            int coin = coins[i];
            
            for(int j = coin; j<= K; j++){
                 dp[j] += dp[j - coin];   
            }
        }
        System.out.println(dp[K]);        
    }
}