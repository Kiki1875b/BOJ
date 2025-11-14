import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long[] arr = new long[n];
        for(int i = 0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        if(n == 1){
            System.out.println(arr[0]); return;
        }
        
        
        long[] dp = new long[arr.length];
        dp[0] = arr[0];
        long ans = arr[0];
        for(int i = 1; i<arr.length; i++){
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            ans = Math.max(dp[i], ans);
        }

        System.out.println(ans);
    }
}