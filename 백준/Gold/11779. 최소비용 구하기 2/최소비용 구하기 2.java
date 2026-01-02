import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0; i<=N; i++) graph.add(new ArrayList<>());
        for(int i = 0; i<M; i++){
            String[] s = br.readLine().split(" ");
            int a= Integer.parseInt(s[0]);
            int b= Integer.parseInt(s[1]);
            int c= Integer.parseInt(s[2]);
            
            graph.get(a).add(new int[]{b,c});
        }
        
        String[] s= br.readLine().split(" ");
        int start = Integer.parseInt(s[0]);
        int end = Integer.parseInt(s[1]);
        
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        int[] parent = new int[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    
        pq.add(new int[]{start, 0});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            
            if(dist[node] < cost) continue;
            
            for(int[] next: graph.get(node)){
                int nNode = next[0];
                int nCost = cost + next[1];
                
                if(dist[nNode] <= nCost) continue;
                
                dist[nNode] = nCost;
                parent[nNode] = node;
                pq.add(new int[]{nNode, nCost});
                
            }
        }

        
        System.out.println(dist[end]);
        
        List<Integer> path = new ArrayList<>();
        int cur = end;
        
        while(cur != 0){
            path.add(cur);

            cur = parent[cur];
        }
        Collections.reverse(path);

        System.out.println(path.size());
        for (int p : path) {
            System.out.print(p + " ");
        }
    
    }
}