import java.util.*;

class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    List<String> ans = new LinkedList<>();
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        for(String[] t : tickets){
            graph.computeIfAbsent(t[0], k -> new PriorityQueue<>())
                .add(t[1]);
        }
        
        dfs("ICN");
        
        Collections.reverse(ans);
        return ans.toArray(new String[0]);
    }
    
    void dfs(String port){
        PriorityQueue<String> q = graph.get(port);
        
        while(q != null && !q.isEmpty()){
            dfs(q.poll());
        }
        
        ans.add(port);
    }
}