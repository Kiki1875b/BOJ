import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        
        int n = s1.length();
        int m = s2.length();
        
        int[][] dp = new int[n+1][m + 1];
        
        for(int i = 1; i<=n; i++){
            for(int j = 1;j<=m; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        int i = n; int j = m;
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j> 0){
            if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                sb.append(s1.charAt(i - 1));
                i--;
                j--;
            }else if(dp[i - 1][j] > dp[i][j - 1]){
                i--;
            }else{
                j--;
            }
        }
        
        if(dp[n][m] ==0 ){
            System.out.println(0);
        }else{
            System.out.println(dp[n][m]);
            System.out.println(sb.reverse().toString());
        }
    }
}