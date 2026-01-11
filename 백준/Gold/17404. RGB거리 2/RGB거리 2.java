import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        
        for(int i  = 0; i<N; i++){
            String[] s = br.readLine().split(" ");
            for(int j = 0; j<s.length; j++){
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        for(int i = 0; i<3; i++){
            int[][] dp = new int[N][3];
            
            for(int j = 0; j<3; j++){
                dp[0][j] = (i != j) ? 1_000_000_000 : arr[0][j];
            }
            
            for(int j = 1; j<N; j++){
                dp[j][0] = Math.min(dp[j - 1][1], dp[j -1][2]) + arr[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j-1][2]) + arr[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + arr[j][2];
            }
            
            for(int j = 0; j<3; j++){
                if(i == j) continue;
                ans =Math.min(ans, dp[N-1][j]);
            }
        }
        System.out.println(ans);
    }
}