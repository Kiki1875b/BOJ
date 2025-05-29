

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 *
 */

class Pair {
  int node;
  int dist;

  public Pair(int node, int dist) {
    this.node = node;
    this.dist = dist;
  }

}
class Solution {

  List<List<Integer>> map;
  int[] dist;
  public int solution(int n, int[][] edge) {
    int answer = 0;

    map = new ArrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
      map.add(new ArrayList<>());
    }

    for(int i = 0; i < edge.length; i++){
      int from = edge[i][0];
      int to = edge[i][1];

      map.get(from).add(to);
      map.get(to).add(from);
    }

    int start = 1;
    dist = new int[n + 1];
    for(int i = 0; i<=n ; i++){
      dist[i] = Integer.MAX_VALUE;
    }

    dijk(start);

    int furthestNode = -1;
    int cur = 0;
    for(int i = 1; i<=n; i++) {
      if(dist[i] > cur){
        cur = dist[i];
        furthestNode = i;
      }
    }


    for(int i : dist){
      if(i == cur) answer++;
    }


    return answer;
  }

  public void dijk(int start){

    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(start, 0));
    dist[start] = 0;

    while(!q.isEmpty()){
      Pair current = q.poll();
      int node = current.node;
      int cost = current.dist;

      for(int i = 0; i<map.get(node).size(); i++){
        int next = map.get(node).get(i);
        int nCost = cost + 1;

        if(dist[next] > nCost){
          dist[next] = nCost;
          q.add(new Pair(next, nCost));
        }
      }
    }
  }
}