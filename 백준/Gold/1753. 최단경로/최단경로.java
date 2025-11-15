import java.util.*;
import java.io.*;

class Main{
        
    static List<List<int[]>> graph = new ArrayList<>();
    static int[] dist;
    static int V, E;
    
    
    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        dist = new int[V + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        int start = Integer.parseInt(br.readLine());
        for(int i = 0; i<=V; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i<E; i++){
            String[] tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);
            int cost = Integer.parseInt(tmp[2]);
            
            graph.get(from).add(new int[]{to, cost});
        }
        
        dijk(start);
        
        for(int i = 1; i<=V; i++){
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }
    
    static void dijk(int start){
    PriorityQueue<int[]> q =
            new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        q.add(new int[]{start, 0});
        dist[start] = 0;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0];
            int cost = cur[1];
            
            if(cost > dist[node]){
                continue;
            } 
            
            for(int[] next : graph.get(node)){
                int nNode = next[0];
                int nCost = cost + next[1];
                
                if(dist[nNode] > nCost){
                    dist[nNode] = nCost;
                    q.add(new int[]{nNode, nCost});
                }
            }
        }
    }
}