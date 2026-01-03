import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        int T = Integer.parseInt(br.readLine());
        
        for(int t= 0; t<T; t++){
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int targets = Integer.parseInt(input[2]);
            
            input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int g = Integer.parseInt(input[1]);
            int h = Integer.parseInt(input[2]);
            
            
            List<List<int[]>> graph = new ArrayList<>();
            int gh = 0;
            for(int i = 0; i<=n; i++) graph.add(new ArrayList<>());
            
            for(int i = 0; i<m; i++){
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
                int cost = Integer.parseInt(input[2]);
                
                if((a == g && b == h) || (a == h && b == g)) {
                    gh = cost;
                }
                
                graph.get(a).add(new int[]{b,cost});
                graph.get(b).add(new int[]{a,cost});
                
            }
            
            int[] distS = new int[n + 1];
            int[] distG = new int[n + 1];
            int[] distH = new int[n + 1];
            Arrays.fill(distS, Integer.MAX_VALUE);
            Arrays.fill(distG, Integer.MAX_VALUE);
            Arrays.fill(distH, Integer.MAX_VALUE);
            
            
            List<Integer> cands = new ArrayList<>();
            for(int i = 0; i<targets; i++){
                cands.add(
                    Integer.parseInt(br.readLine())
                );
            }
            
            dijk(s, distS, graph);
            dijk(g, distG, graph);
            dijk(h, distH, graph);
            
            List<Integer> ans = new ArrayList<>();
            
            for(int cand : cands){
                if(distS[cand] == gh + distS[h] + distG[cand]){
                    ans.add(cand);
                }else if(distS[cand] == gh + distS[g] + distH[cand]){
                    ans.add(cand);
                }
            }
            Collections.sort(ans);
            
            for(int a : ans){
                System.out.print(a + " ");
            }
            System.out.println();
        }
        
        
    }
    
    static void dijk(int start, int[] dist, List<List<int[]>> graph){
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[1] - b[1]);
      
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        q.add(new int[]{start, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int node = cur[0];
            int cost =cur[1];
            
            if(dist[node] < cost) continue;
            
            for(int[] next: graph.get(node)){
                int nNode = next[0];
                int nCost = cost + next[1];
                
                if(dist[nNode] <= nCost) continue;
                
                dist[nNode] = nCost;
                q.add(new int[]{nNode, nCost});
            }
        }
        
    }
}