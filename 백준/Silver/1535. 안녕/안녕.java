import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {
    
    
    static int N;
    static int[] L, J;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
    
        N = Integer.parseInt(br.readLine());
        String[] tmp = br.readLine().split(" ");
        String[] tmp2 = br.readLine().split(" ");
        L = new int[N];
        J = new int[N];
        
        for(int i = 0 ; i<N; i++){
            L[i] = Integer.parseInt(tmp[i]);
            J[i] = Integer.parseInt(tmp2[i]);
        }
        
        
        int[] dp = new int[101];
        dp[0] = 0;
        int ans = 0;
        
        for(int i = 0; i<N; i++){
            for(int j = 99; j >= L[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - L[i]] + J[i]);
                ans = Math.max(dp[j], ans);
            }
        }
        
        System.out.println(ans);
    }
}