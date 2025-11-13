import java.util.*;
import java.io.*;

class Main {
    static int C, N;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        C = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        
        int[] people = new int[N];
        int[] costs = new int[N];
        
        for(int i = 0 ; i<N; i++){
            input = br.readLine().split(" ");
            costs[i] = Integer.parseInt(input[0]);
            people[i] = Integer.parseInt(input[1]);
        }
        
        int[] dp = new int[C + 101]; // dp[i] = i명을 늘리기 위한 최소 비용
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i<N; i++){
            int cost = costs[i];
            int num = people[i];
            
            for(int j = num; j < dp.length; j++){
                
                if(dp[j - num] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j - num] + cost);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for(int i = C; i<dp.length; i++){
            ans = Math.min(dp[i], ans);
        }
        System.out.println(ans);
        
    }
}