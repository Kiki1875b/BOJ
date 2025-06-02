
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 양방향 그래프
 * 지점의 개수 n
 * A 의 도착 지점 a
 * B 의 도착 지점 b
 * 지점 사이의 택시요금 : fares
 */

class Pair {
  int node;
  int cost;

  public Pair(int node, int cost){
    this.node = node;
    this.cost = cost;
  }
}

class Solution {

  List<List<Pair>> map;


  public int solution(int n, int s, int a, int b, int[][] fares) {
    int answer = Integer.MAX_VALUE;

    map = new ArrayList<>();


    int[] aDist = new int[n + 1];
    int[] bDist = new int[n + 1];
    int[] sDist = new int[n + 1];

    for(int i = 0; i<=n; i++){
      map.add(new ArrayList<>());
      aDist[i] = Integer.MAX_VALUE;
      bDist[i] = Integer.MAX_VALUE;
      sDist[i] = Integer.MAX_VALUE;
    }

    for(int[] fare : fares){
      int from = fare[0];
      int to = fare[1];
      int cost = fare[2];

      map.get(from).add(new Pair(to, cost));
      map.get(to).add(new Pair(from, cost));
    }

    dijkstra(a, aDist);
    dijkstra(b, bDist);
    dijkstra(s, sDist);

    for(int i = 1; i<=n; i++){
      answer = Math.min(answer, aDist[i] + bDist[i] + sDist[i]);
    }

    return answer;
  }

  void dijkstra(int start, int[] dist){

    dist[start] = 0;
    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(start, 0));

    while(!q.isEmpty()){
      Pair current = q.poll();
      int node = current.node;
      int cost = current.cost;

      for(int i = 0; i<map.get(node).size(); i++){
        Pair next = map.get(node).get(i);
        int nNode = next.node;
        int nCost = cost + next.cost;

        if(dist[nNode] <= nCost) continue;

        dist[nNode] = nCost;
        q.add(new Pair(nNode, nCost));
      }
    }

  }

//  public static void main(String[] args) {
//    Solution s = new Solution();
//
//    s.solution(6,4,6,2, new int[][]{{4,1,10},{3,5,24}, {5,6,2},{3,1,41},{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}});
//  }

}
