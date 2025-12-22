import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s1 = br.readLine();
        String s2 = br.readLine();
        
        int[][] dp = new int[s1.length()][s2.length()];
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        int ans = dp[0][0];
        for(int i = 1; i<s1.length(); i++){
            if(s1.charAt(i) == s2.charAt(0)){
                dp[i][0] = 1;
                ans = Math.max(dp[i][0], ans);
            }
        }
        
        for(int i = 1; i < s2.length(); i++){
            if(s1.charAt(0) == s2.charAt(i)){
                dp[0][i] = 1;
                ans = Math.max(dp[0][i], ans);
            }
        }
        
        for(int i = 1; i<s1.length(); i++){
            for(int j = 1; j<s2.length(); j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }
        
        System.out.println(ans);
    }
}