import java.util.*;
import java.io.*;

class Main {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);
        
        int[][] dp = new int[N + 1][K + 1];
        
        for(int k = 0; k<=K; k++){
            dp[0][k] = 1;
        }
        
        for(int n = 1; n <= N; n++){
            for(int k = 1; k<=K; k++){
                dp[n][k] = (dp[n - 1][k] + dp[n][k - 1]) % 1000000000;
            }
        }
        System.out.println(dp[N][K]);
    }
}