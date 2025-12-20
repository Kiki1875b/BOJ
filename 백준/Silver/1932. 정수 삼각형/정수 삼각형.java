import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int N = Integer.parseInt(br.readLine());
        
        int[][] arr= new int[N][N];
        
        for(int i = 0; i<N; i++){
            String[] input = br.readLine().split(" ");
            
            int l = input.length;
            for(int j = 0; j<l; j++){
                arr[i][j] = Integer.parseInt(input[j]);
            }
            for(int j = l; j<N; j++){
                arr[i][j] = 0;
            }
        }
        
        if(N == 1){
            System.out.println(arr[0][0]);
            return;
        }
        
        int[][] dp = new int[N][N];
        dp[0][0] = arr[0][0];
        
        int ans = 0;
        for(int i = 1; i<N; i++){
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        
        for(int i = 1; i<N; i++){
            for(int j = 1; j<N; j++){
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
                ans = Math.max(dp[i][j], ans);
            }
        }
       System.out.println(ans);
    }
}