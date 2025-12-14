import java.io.*;
import java.util.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        
        int[][] m = new int[N][M];
        
        for(int i = 0; i<N; i++) {
            input = br.readLine().split(" ");
            for(int j = 0; j<input.length; j++){
                m[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        int[][] dp = new int[N][M];
        dp[0][0] = m[0][0];
        for(int i = 1; i<M; i++){
            dp[0][i] = dp[0][i - 1] + m[0][i];
        }
        for(int i = 1; i < N; i++){
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        
        for(int i = 1; i < N; i++){
            for(int j = 1; j < M; j++){
                dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1])) + m[i][j];
            }
        }
        
        System.out.println(dp[N - 1][M - 1]);
    }
}