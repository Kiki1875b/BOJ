import java.util.*;
import java.io.*;
// 1 1 1 2 2 3 4 5 7 9 12 16 21 28
class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        long[] dp = new long[101];
        dp[1] = 1L; dp[2] = 1L; dp[3] = 1L; dp[4] = 2L; dp[5] = 2L; 
        
        int[] targets = new int[T];
        int target = 0;
        for(int t = 0; t < T; t++){
            int input = Integer.parseInt(br.readLine());
            targets[t] = input;
            target = Math.max(target, targets[t]);
        }
        
        
        for(int i = 6; i <= target; i++){
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        
        for(int i : targets){
            System.out.println(dp[i]);
        }
    
    }
}