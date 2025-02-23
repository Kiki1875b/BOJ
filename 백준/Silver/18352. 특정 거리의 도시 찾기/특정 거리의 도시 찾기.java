import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 1 ~ N 번 도시
 * M 개의 단방향 도로
 * 모든 도로의 길이 = 1
 *
 * 출발 도시 번호 x 가 주어질떄, 최단거리가 K 인 도시를 오름차순 출력
 */

class Pair{
  int node, cost;
  public Pair(int node, int cost){
    this.node = node;
    this.cost = cost;
  }
}
class Main {

  static int N, M, X, K;
  static List<List<Integer>> map;
  static int[] dist;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    String[] input = bf.readLine().split(" ");

    map = new ArrayList<>();
    N = Integer.parseInt(input[0]);
    M = Integer.parseInt(input[1]);
    K = Integer.parseInt(input[2]);
    X = Integer.parseInt(input[3]);


    dist = new int[N + 2];
    Arrays.fill(dist, Integer.MAX_VALUE);

    for(int i = 0; i<=N; i++){
      map.add(new ArrayList<>());
    }

    for(int i = 0; i<M; i++){
      String[] input2 = bf.readLine().split(" ");
      int from = Integer.parseInt(input2[0]);
      int to = Integer.parseInt(input2[1]);

      map.get(from).add(to);
    }

    dijk(X);

    List<Integer> ans = new ArrayList<>();

    for(int i = 0; i<dist.length; i++){
      if(dist[i] == K) ans.add(i);
    }

    Collections.sort(ans);
    if(ans.isEmpty()) System.out.println(-1);
    else{
      for(int num : ans){
        System.out.println(num);
      }
    }
  }

  static void dijk(int start){

    Queue<Pair> q = new LinkedList<>();
    dist[start] = 0;
    q.offer(new Pair(start, 0));

    while(!q.isEmpty()){
      Pair cur = q.poll();
      int curNode = cur.node;
      int curCost = cur.cost;

      for(int i = 0; i<map.get(curNode).size(); i++){
        int nextNode = map.get(curNode).get(i);
        int nextCost = curCost + 1;

        if(dist[nextNode] >= nextCost){
          q.offer(new Pair(nextNode, nextCost));
          dist[nextNode] = nextCost;
        }
      }
    }
  }
}

