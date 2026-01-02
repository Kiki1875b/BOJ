import java.util.*;
import java.io.*;

class Main {
    static List<List<int[]>> graph;
    static int N, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        E = Integer.parseInt(s[1]);
        
        graph = new ArrayList<>();
        
        for(int i = 0; i<=N; i++) graph.add(new ArrayList<>());
        
        for(int i =0; i<E; i++){
            s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b= Integer.parseInt(s[1]);
            int cost = Integer.parseInt(s[2]);
            
            graph.get(a).add(new int[]{b, cost});
            graph.get(b).add(new int[]{a, cost});
        }
        
        s = br.readLine().split(" ");
        
        int p1 = Integer.parseInt(s[0]);
        int p2 = Integer.parseInt(s[1]);
        
        int a1= dijk(1, p1);
        int a2= dijk(p1, p2);
        int a3= dijk(p2, N);
        
        if(a1 == Integer.MAX_VALUE || a2 == Integer.MAX_VALUE || a3 == Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        
        System.out.println(
            Math.min(
                a1 + a2 + a3,
                dijk(1, p2) + dijk(p2, p1) + dijk(p1, N)
                )
            );
    }
    
    static int dijk(int start, int end){
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> (a[1] - b[1]));
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        q.add(new int[]{start, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0];
            int cost =cur[1];
            
           //if(node == end) return cost;
            if(dist[node] < cost) continue;
            
            for(int[] next: graph.get(node)){
                int nNode = next[0];
                int nCost = cost + next[1];
                if(dist[nNode] <= nCost) continue;
                dist[nNode] = nCost;
                q.add(new int[]{nNode, nCost});
            }
        }
        
        return dist[end];
    }
}