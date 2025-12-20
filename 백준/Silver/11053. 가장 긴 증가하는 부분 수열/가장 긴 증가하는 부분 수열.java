import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String[] input = br.readLine().split(" ");
        
        for(int i = 0; i<N; i++) arr[i] = Integer.parseInt(input[i]);
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        dp[0] = 1;
        int ans = 1;
        
        for(int i = 1; i<N; i++){
            for(int j = i - 1; j>=0; j--){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    ans = Math.max(ans, dp[i]);
                }
            }
            
        }
        System.out.println(ans);
    }
}