import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
     
        boolean[][] dp = new boolean[s.length()][s.length()];
        int n = s.length();
        for(int i = 0; i<n; i++){
            dp[i][i] = true;
            if(i != n - 1){
                if(s.charAt(i)==s.charAt(i+1)){
                    dp[i][i + 1] = true;
                }
            }
        }
        
        
        for(int len = 3; len <= n; len++){
            for(int l = 0; l<=n-len; l++){
                int r = l + len - 1;
                
                if(s.charAt(l) == s.charAt(r) && dp[l + 1][r - 1]){
                    dp[l][r] = true;
                }
            }
        }
        
        int[] dp2 = new int[n];
        
        Arrays.fill(dp2, Integer.MAX_VALUE);
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            if(dp[0][i]){
                dp2[i] = 1;
                continue;
            }
            
            for(int j = 1; j <= i; j++){
                if(dp[j][i]){
                    dp2[i] = Math.min(dp2[i], dp2[j - 1] + 1);
                }
            }
        }
        System.out.println(dp2[n - 1]);
        
    }
}