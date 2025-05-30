
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2진 트리 모양 초원
 * 루트에서 시작하여 양을 모으려 한다 -
 *   - 각 노드를 방문할때, 해당 노드에 있던 양/늑대가 따라온다
 *   - 내가 모은 양의 수보다 늑대가 많거나 같다면, 양을 잡아먹는다
 *   - 최대한 많은 수의 양을 모아서 다시 루트 노드로
 */


class Solution {

  int maxSheepCount;
  List<List<Integer>> nodes = new ArrayList<>();

  public int solution(int[] info, int[][] edges) {

    for(int i = 0 ; i<info.length; i++){
      nodes.add(new ArrayList<>());
    }

    for(int i = 0 ; i<edges.length; i++){
      nodes.get(edges[i][0]).add(edges[i][1]);
    }

    List<Integer> toSearch = new ArrayList<>();
    toSearch.add(0);
    search(info, toSearch, 0, 0, 0);
    
    return maxSheepCount;
  }

  void search(int[] info, List<Integer> toSearch, int current, int sheepCount, int wolfCount){
    if(info[current] == 0) sheepCount++;
    else wolfCount++;

    if(sheepCount <= wolfCount) return;

    maxSheepCount = Math.max(sheepCount, maxSheepCount);
    
    toSearch.addAll(nodes.get(current));
    toSearch.remove(Integer.valueOf(current));
    
    for (int i = 0 ; i<toSearch.size(); i++){
      
      int next = toSearch.get(i);
      List<Integer> newToSearch = new ArrayList<>(toSearch);
      search(info, newToSearch, next, sheepCount, wolfCount);
      
    }

  }


}
