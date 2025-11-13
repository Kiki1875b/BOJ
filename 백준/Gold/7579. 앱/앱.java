import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        
        int[] memories = new int[N];
        int[] costs = new int[N];
        
        input = br.readLine().split(" ");
        int totalMemory = 0;
        for(int i = 0; i<N; i++){
            memories[i] = Integer.parseInt(input[i]);
            totalMemory += memories[i];
        }
        
        input = br.readLine().split(" ");
        int totalCost = 0;
        for(int i = 0; i<N; i++){
            costs[i] = Integer.parseInt(input[i]);
            totalCost += costs[i];
        }
        
        int[] dp = new int[totalCost + 1]; // dp[i] = 비용을 i 만큼 써서 확보할 수 있는 최대 메모리양
        
        Arrays.fill(dp, 0);
        
        dp[0] = 0; // 0의 메모리를 확보하는데 드는 비용은 0
        
        for(int i = 0; i<N; i++){
            int memory = memories[i];
            int cost = costs[i];
            
            for(int j = totalCost; j >= cost; j--){
                dp[j] = Math.max(dp[j], dp[j - cost] + memory);
            }
        }
        
        int ans = Integer.MAX_VALUE;
        
        for(int i = 0; i<=totalCost; i++){
            if(dp[i] >= M){
                ans = Math.min(i, ans);
            }
        }
        
        System.out.println(ans);
    }
}