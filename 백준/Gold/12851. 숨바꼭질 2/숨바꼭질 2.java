import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);
        
        int[] dp = new int[200001];
        int[] dist = new int[200001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;
        dp[N] = 1;
        
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next : new int[]{cur + 1, cur - 1, cur * 2}){
                if(next < 0 || next >= 200000) continue;
                int nCost = dist[cur] + 1;
                if(dist[next] == nCost){
                    dp[next] += dp[cur];
                }else if(dist[next] > nCost){
                    dp[next] = dp[cur];
                    dist[next] = nCost;
                    q.add(next);
                }
            }
        }
        
        System.out.println(dist[K]);
        System.out.println(dp[K]);
    }
}