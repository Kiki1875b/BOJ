import java.util.*;
class Solution {
    
    List<List<Integer>> l = new ArrayList<>();
    int[] info;
    int ans = 0;
    int N;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        this.info = info;
        N = info.length;
        for(int i = 0; i<N; i++) l.add(new ArrayList<>());
        
        for(int i = 0; i<edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            
            l.get(parent).add(child);
        }
        
        
        
        dfs(0,0,0,new HashSet<>());
        return ans;
    }
    
    void dfs(int sheep, int wolf, int cur, Set<Integer> toSearch){
        if(info[cur] == 1) wolf++;
        else sheep++;
        
        if(wolf >= sheep) return;
        
        ans = Math.max(sheep, ans);
        
        Set<Integer> nextToSearch = new HashSet<>(toSearch);
        nextToSearch.remove(cur);
        nextToSearch.addAll(l.get(cur));
        
        for(Integer next:  nextToSearch){
            dfs(sheep, wolf, next, nextToSearch);
        }
    }
}