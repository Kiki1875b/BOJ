import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int N = Integer.parseInt(br.readLine());
        int[] arr=  new int[N];
        String[] input = br.readLine().split(" ");
        
        for(int i = 0; i<input.length; i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        if(N == 1){
            System.out.println(arr[0]);
            return;
        }
        
        int[] dp = new int[N];
        int ans =0;
        for(int i = 0; i<dp.length; i++){
            dp[i] = arr[i];
            ans = Math.max(dp[i], ans);

        }
        
        
        
        for(int i = 1; i<N; i++){
            for(int j = i - 1; j>=0; j--){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                    ans = Math.max(dp[i], ans);
                }
            }
        }
        System.out.println(ans);
    }
}