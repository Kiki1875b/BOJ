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
        
        for(int i = 0 ; i<n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        
        int[] dp = new int[k + 2]; // dp[i] = i원을 만들 수 있는 경우의 수
        dp[0] = 1;
        for(int coin: coins){
            for(int x = coin; x <= k; x++){
                dp[x] += dp[x - coin];
            }
        }
        
        System.out.println(dp[k]);
    }
}