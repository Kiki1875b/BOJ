
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


class Node{
  boolean isSheep;
  List<Integer> children;
  public Node(boolean isSheep) {
    this.isSheep = isSheep;
    this.children = new ArrayList<>();
  }

}
class Solution {

  List<Node> nodes = new ArrayList<>();
  int ans = 0;
  public int solution(int[] info, int[][] edges) {
    int answer = 0;

    for(int i = 0 ; i<info.length; i++){
      nodes.add(new Node(info[i] == 0));
    }

    for(int i = 0; i< edges.length; i++){
      nodes.get(edges[i][0]).children.add(edges[i][1]);
    }

    dfs(0, 0, 0, new ArrayList<>());

    return ans;
  }

  void dfs(int root, int sheepCnt, int wolfCnt, List<Integer> toSearch){

    if(nodes.get(root).isSheep){
      sheepCnt++;
    }else{
      wolfCnt++;
    }

    if (wolfCnt >= sheepCnt) {
     return;
    }

    ans = Math.max(sheepCnt, ans);

    List<Integer> newToSearch = new ArrayList<>(toSearch);
    newToSearch.addAll(nodes.get(root).children);
    newToSearch.remove(Integer.valueOf(root));

    for(int nextNode: newToSearch){
      dfs(nextNode, sheepCnt, wolfCnt, newToSearch);
    }
  }
}