
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Solution {

  List<List<Integer>> nodes = new ArrayList<>();
  int maxSheepCount = 0;
  public int solution(int[] info, int[][] edges) {
    for(int i = 0; i<info.length; i++){
      nodes.add(new ArrayList<>());
    }

    for(int i = 0; i<edges.length; i++){
      nodes.get(edges[i][0]).add(edges[i][1]);
    }

    List<Integer> toSearch = new ArrayList<>();
    toSearch.add(0);
    search(toSearch, info, 0,0,0);
    
    return maxSheepCount;
  }
  
  void search(List<Integer> toSearch, int[] info, int current, int wolf, int sheep){
    if(info[current] == 0) sheep++;
    else wolf++;
    
    if(wolf >= sheep) return;
    maxSheepCount = Math.max(maxSheepCount, sheep);
    
    toSearch.addAll(nodes.get(current));
    toSearch.remove(Integer.valueOf(current));
    
    for(int i = 0; i<toSearch.size(); i++){
      int next = toSearch.get(i);
      List<Integer> newToSearch = new ArrayList<>(toSearch);
      search(newToSearch, info, next, wolf, sheep);
    }
  }
}
