import java.util.*;
import java.io.*;

class Main{
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        
        long[] seq = new long[input.length];
        
        
        
        for(int i = 0 ; i<input.length; i++){
            seq[i] = Long.parseLong(input[i]);
        }
        
        if(N == 1) {
            System.out.println(seq[0]);
            return;
        }
        
        
        long[] dp = new long[seq.length];
        
        dp[0] = seq[0];
        long ans = seq[0];
        for(int i = 1; i<seq.length; i++){
            dp[i] = Math.max(dp[i - 1] + seq[i], seq[i]);
            ans = Math.max(dp[i], ans);
        }
        
        
        System.out.println(ans);
    }
}