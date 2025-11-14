import java.util.*;
import java.io.*;
class Main{

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        int[] arr = new int[T];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i<T; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        boolean[][] dp = new boolean[T][T];
        
        for(int i = 0; i< T; i++){
            dp[i][i] = true;
            
            if(i != T - 1 && arr[i] == arr[i + 1]){
                dp[i][i + 1] = true;
            }
        }
        
        for(int len = 3; len <= T; len++){
            for(int l = 0; l < T-len; l++){
                int r = l + len - 1;
                
                if(arr[l] == arr[r] && dp[l + 1][r - 1]){
                    dp[l][r] = true;
                }
            }
        }
        
        int[][] dp2 = new int[T][T]; // 마지막 케이스 까지 검증해야 하므로 +1
        
        for(int i = 0; i<T; i++){
            for(int j = 0; j<T; j++){
                if(dp[i][j]) dp2[i][j] = 0;
            }
        }
        
        for(int len = 2; len <= T; len++){
            for(int l = 0; l <= T- len; l++){
                int r = l + len - 1;
                
                if(arr[l] == arr[r]){
                   dp2[l][r] = dp2[l + 1][r - 1];     
                }else{
                    dp2[l][r] = Math.min(
                        dp2[l + 1][r],
                        dp2[l][r - 1]
                    ) + 1;
                }
            }
        }
        
        System.out.println(dp2[0][T - 1]);
        
        
     
        
    }
}