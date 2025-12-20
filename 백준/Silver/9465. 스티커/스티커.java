import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][2];
            String[] input1 = br.readLine().split(" ");
            String[] input2=  br.readLine().split(" ");
            
            for(int i = 0; i<N; i++){
                arr[i][0] = Integer.parseInt(input1[i]);
                arr[i][1] = Integer.parseInt(input2[i]);
            }
            
            if(N == 1){
                System.out.println(Math.max(arr[0][0], arr[0][1]));
                continue;
            }
            
            int[][] dp = new int[N][2];
            
            dp[0][0] = arr[0][0];
            dp[0][1] = arr[0][1];
            
            dp[1][0] = dp[0][1] + arr[1][0];
            dp[1][1] = dp[0][0] + arr[1][1];
            
            int ans = Math.max(dp[1][0], dp[1][1]);
            for(int i = 2; i<N; i++){
                dp[i][0] = Math.max(dp[i - 1][1], Math.max(dp[i - 2][0], dp[i - 2][1])) + arr[i][0];
                dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 2][0], dp[i - 2][1])) + arr[i][1];
                
                ans = Math.max(dp[i][0], Math.max(dp[i][1], ans));
                
            }
            
            System.out.println(ans);
        }        
    }
}