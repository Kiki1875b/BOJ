import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        int[][] costs = new int[T][3];
        
        for(int i = 0; i<T; i++){
            String[] input = br.readLine().split(" ");
            
            for(int j = 0; j<input.length; j++){
                
                costs[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        int[][] dp = new int[T][3];
        
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        
        for(int i = 1; i<T; i++){
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        
        int ans = Math.min(dp[T - 1][0], Math.min(dp[T-1][1], dp[T-1][2]));
        System.out.println(ans);
    }
}