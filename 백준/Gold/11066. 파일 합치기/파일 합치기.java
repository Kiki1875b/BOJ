import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t<T; t++){
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int[] arr = new int[K];
            for(int i = 0; i<K; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            
            int[] prefix = new int[K + 1];
            prefix[0] = 0;
            
            for(int i = 1; i<=K; i++){
                prefix[i] = prefix[i - 1] + arr[i - 1];
            }
            
            int[][] dp = new int[K + 1][K + 1];
            for(int i = 0 ; i<dp.length; i++){
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                dp[i][i] = 0;
            }
            
            for(int len = 1; len < K; len++){
                
                for(int i = 1; i <= K-len; i++){
                    int j = i + len;
                    
                    for(int k = i; k < j; k++){
                        dp[i][j] = Math.min(
                            dp[i][j],
                            dp[i][k] + dp[k + 1][j] + prefix[j] - prefix[i - 1]
                        );
                        
                    }
                }
            }
            
            System.out.println(dp[1][K]);
            
        }
    }
}