import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        
        int[] memories = new int[N];
        int totalMem = 0;
        input = br.readLine().split(" ");
        for(int i = 0; i<N; i++) {
            memories[i] = Integer.parseInt(input[i]);
            totalMem += memories[i];
        }
        int[] costs = new int[N];
        int totalCost = 0;
        input = br.readLine().split(" ");
        for(int i = 0; i<N; i++){ 
            costs[i] = Integer.parseInt(input[i]);
            totalCost += costs[i];
        }
        
        int[] dp = new int[totalCost + 1]; // dp[i] = i 비용을 써서 얻을 수 있는 최대 메모리 양
        
        dp[0] = 0;
        
        
        for(int i = 0;i <N; i++){
            int mem = memories[i];
            int cost = costs[i];
            
            for(int j = totalCost; j >= cost; j--){
                dp[j] = Math.max(dp[j], dp[j - cost] + mem);
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        for(int i = 0; i <dp.length; i++){
            if(dp[i] >= M){
                ans = i;
                break;
            }    
        }
        
        System.out.println(ans);
    }
}