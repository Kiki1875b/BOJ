import java.util.*;

class Solution {
    List<List<Integer>> m;
    public int[] solution(int[][] edges) {
        int[] answer = new int[]{0,0,0,0};
        int[] out = new int[1000001];
        int[] in = new int[1000001];
        
        m = new ArrayList<>();
        
        for(int i = 0; i<1000000; i++) m.add(new ArrayList<>());
        
        for(int i = 0; i<edges.length; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            
            out[from]++;
            in[to]++;
            
            m.get(from).add(to);
        }
        
        int start = -1;
        

        for(int i = 0; i<1000001; i++){
            if(out[i] >= 2 && in[i] == 0){
                start = i;
                break;
            }
        }
        
        answer[0] = start;
        for(Integer s: m.get(start)){
            int[] res = search(s);
            
            int nodeCnt = res[0]; int edgeCnt = res[1];
            
            if(nodeCnt == edgeCnt){
                answer[1]++;
            }else if(nodeCnt > edgeCnt){
                answer[2]++;
            }else{
                answer[3]++;
            }
        }
        
        
        
        
        return answer;
    }
    
    public int[] search(int startNode){ // node 수 , edge 수
        Set<Integer> visited = new HashSet<>();
        
        Queue<Integer> q = new LinkedList<>();
        
        visited.add(startNode);
        q.add(startNode);
        int edgeCnt = 0;
        
        while(!q.isEmpty()){
            
            int current = q.poll();
            
            int node = current;
            
            for(Integer nextNode : m.get(node)){
                if(!visited.contains(nextNode)){
                    visited.add(nextNode);
                    q.add(nextNode);
                }
                edgeCnt++;
            }
        }
        
        return new int[]{visited.size(), edgeCnt};
    }
}