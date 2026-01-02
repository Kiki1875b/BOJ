import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<List<int[]>> graph = new ArrayList<>();
        
        String[] s = br.readLine().split(" ");
        
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for(int i =0 ; i<M; i++){
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b= Integer.parseInt(s[1]);
            int cost = Integer.parseInt(s[2]);
            
            graph.get(a).add(new int[]{b, cost});
        }
        
        long INF = Long.MAX_VALUE;
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        
        for(int i = 1; i<N; i++){
            for(int u = 1; u<=N; u++){
                if(dist[u] == INF) continue;
                for(int[] edge : graph.get(u)){
                    int v = edge[0];
                    int c = edge[1];
                    
                    if(dist[v] > dist[u] + c){
                        dist[v] = dist[u] + c;
                    }
                }
            }
        }
        
        for(int u = 1; u<=N; u++){
            if(dist[u] == INF) continue;
            for(int[] edge : graph.get(u)){
                int v = edge[0];
                int c = edge[1];
                if(dist[v] > dist[u] + c){
                    System.out.println(-1);
                    return;
                }
            }
        }
        
        for(int i = 2; i<=N; i++){
            if(dist[i] != INF) System.out.println(dist[i]);
            else System.out.println(-1);
        }
    }
}