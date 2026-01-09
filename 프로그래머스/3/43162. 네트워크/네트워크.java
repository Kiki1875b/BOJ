import java.util.*;

class Solution {
    
    boolean[] visited;
    List<List<Integer>> graph;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        graph = new ArrayList<>();
        
        for(int i =0 ; i<=n; i++) graph.add(new ArrayList<>());
        
        for(int i = 0; i<computers.length; i++){
            for(int j = 0; j<computers.length; j++){
                if(i == j ) continue;
                if(computers[i][j] == 0) continue;
                
                graph.get(i).add(j);
            }
        }
        int ans = 0;
        for(int i =0 ; i<n; i++){
            if(!visited[i]){
                bfs(i); ans++;
            }
        }
        
        return ans;
    }
    
    void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        
        while(!q.isEmpty()){
            int node = q.poll();
            
            for(int next: graph.get(node)){
                if(!visited[next]){
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
}