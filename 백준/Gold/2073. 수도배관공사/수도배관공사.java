import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int D, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] DP = br.readLine().split(" ");
        
        D = Integer.parseInt(DP[0]);
        P = Integer.parseInt(DP[1]);
        
        int[][] pipes = new int[P][2];
        
        for(int i = 0; i<P; i++){
            String[] tmp = br.readLine().split(" ");
            pipes[i][0] = Integer.parseInt(tmp[0]);
            pipes[i][1] = Integer.parseInt(tmp[1]);
        }
        
        
        int[] dp = new int[D + 1]; // dp[i] = 길이 i에서 만들 수 있는 최대 용량
        Arrays.fill(dp, -1);
        dp[0] = Integer.MAX_VALUE;
        
        for(int i = 0; i<pipes.length; i++){
            int len = pipes[i][0];
            int cap = pipes[i][1];
            
            for(int d = D; d >= len; d--){
                dp[d] = Math.max(dp[d], Math.min(dp[d - len], cap));
            }
        }
        
        System.out.println(dp[D]);
    }
}