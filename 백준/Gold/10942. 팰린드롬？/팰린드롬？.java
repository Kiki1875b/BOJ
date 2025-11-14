import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        
        for(int i = 0 ; i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine());
        int[][] queries = new int[M][2];
        
        for(int i = 0; i<M; i++){
            String[] input = br.readLine().split(" ");
            queries[i][0] = Integer.parseInt(input[0]);
            queries[i][1] = Integer.parseInt(input[1]);
        }
        
        boolean[][] dp = new boolean[N][N];
        
        
        for(int i = 0; i<dp.length; i++){
            dp[i][i] = true;
            
        }
        
        for(int i = 0; i<dp.length - 1; i++){
            if(arr[i]==arr[i + 1]) dp[i][i + 1] = true;
        }
        
        for(int len = 3; len <= N; len++){
            for(int l = 0; l < N - len + 1; l++){
                int r = l + len - 1;
                if(arr[l]==arr[r] && dp[l + 1][r-1]) dp[l][r] = true;
            }
        }
        
        StringBuilder sb = new StringBuilder();

        for (int[] q : queries) {
            int l = q[0] - 1;
            int r = q[1] - 1;
            sb.append(dp[l][r] ? 1 : 0).append('\n');
        }

        System.out.print(sb.toString());
        
        
    }
}