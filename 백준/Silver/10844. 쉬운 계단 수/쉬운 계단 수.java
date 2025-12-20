import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    
        int N = Integer.parseInt(br.readLine());
        
        int[][] dp = new int[N + 1][10];
        
        for(int i = 0; i<10; i++){
            dp[1][i] = 1;
        }
        
        dp[1][0] = 0;
        
        for(int L = 2; L <= N; L++){
            for(int i = 0; i<10; i++){
                if(i == 0){
                    dp[L][i] = dp[L - 1][i + 1] % 1000000000;
                }else if(i == 9){
                    dp[L][i] = dp[L - 1][i - 1] % 1000000000;
                }else{
                    dp[L][i] = (dp[L - 1][i - 1] + dp[L - 1][i + 1]) % 1000000000;
                }
            }
        }
        
        long ans = 0;
        for(int i = 0; i<10; i++){
            ans += (long) dp[N][i];
        }
        System.out.println(ans  % 1000000000);
    }
}