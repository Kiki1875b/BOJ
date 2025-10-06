import java.util.*;


class Solution {
    
    List<List<int[]>> graph = new ArrayList<>();
    int N;
    int[] maxIntensity;
    Set<Integer> g = new HashSet<>();
    Set<Integer> s = new HashSet<>();
    
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // int[] answer = {};
        
        maxIntensity = new int[n + 1];
        for(int i = 0; i<= n ; i++) maxIntensity[i] = Integer.MAX_VALUE;
        for(int i = 0; i<=n; i++) graph.add(new ArrayList<>());
        N = n;
        
        for(int i = 0; i<paths.length; i++){
            int a = paths[i][0]; 
            int b = paths[i][1];
            int cost = paths[i][2];
            
            graph.get(a).add(new int[]{b,cost});
            graph.get(b).add(new int[]{a,cost});

        }
        
        for(int gate: gates) g.add(gate);
        for(int summit: summits) s.add(summit);
        
        simulate(gates);
        Arrays.sort(summits);
        int minIntensity = Integer.MAX_VALUE;
        int ans = -1;
        for(int summit:summits){
            if(maxIntensity[summit] < minIntensity){
                ans = summit;
                minIntensity = maxIntensity[summit];
            }
        }
        
        return new int[]{ans, minIntensity};
    }
    
    void simulate(int[] starts){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        for(int start: starts){
            pq.add(new int[]{start, 0});
            maxIntensity[start] = 0;
        }
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int node = cur[0]; int cost = cur[1];
            
            if(maxIntensity[node] < cost) continue;
            if(s.contains(node)) continue;
            
            for(int[] next: graph.get(node)){
                int nNode = next[0];
                int nCost = next[1];
                
                if(g.contains(nNode)) continue;
                int newIntensity = Math.max(nCost, cost);
                if(newIntensity < maxIntensity[nNode]){
                    pq.add(new int[]{nNode, newIntensity});
                    maxIntensity[nNode] = newIntensity;
                }
            }
        }
    }
}