import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {
    
    static int N, M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        
        int[] dp = new int[N + 1];
        int[][] chapters = new int[M][2];
        
        dp[0] = 0;
        for(int i = 0; i<M; i++){
            String[] tmp = br.readLine().split(" ");
            chapters[i][0] = Integer.parseInt(tmp[0]);
            chapters[i][1] = Integer.parseInt(tmp[1]);
        }

        for(int i = 0; i < M; i++){
            int days = chapters[i][0]; int pages = chapters[i][1];
            for(int d = N; d >= days; d--){
                dp[d] = Math.max(dp[d], dp[d - days] + pages);
            }
        }
        
        System.out.println(dp[N]);
    }
}