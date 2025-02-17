import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {

  static int N, M, V;
  static List<List<Integer>> map = new ArrayList<>();
  static boolean[] visited;
  static StringBuilder bfsBuilder = new StringBuilder();
  static StringBuilder dfsBuilder = new StringBuilder();

  public static void main(String[] args) throws IOException {

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    String[] input = bf.readLine().split(" ");

    N = Integer.parseInt(input[0]);
    M = Integer.parseInt(input[1]);
    V = Integer.parseInt(input[2]);
    visited = new boolean[N + 1];
    for(int i = 0; i<= N; i++){
      map.add(new ArrayList<>());
    }

    for(int i = 0; i<M; i++){
      String[] tmp = bf.readLine().split(" ");
      int a = Integer.parseInt(tmp[0]);
      int b = Integer.parseInt(tmp[1]);

      map.get(a).add(b);
      map.get(b).add(a);
    }

    for (int i = 1; i <= N; i++) {
      Collections.sort(map.get(i));
    }

    dfs(V);
    Arrays.fill(visited, false);
    bfs();

    System.out.println(dfsBuilder.toString());
    System.out.println(bfsBuilder.toString());
  }

  static void bfs(){
    Queue<Integer> q = new LinkedList<>();
    visited[V] = true;

    q.offer(V);

    while(!q.isEmpty()){
      int curNode = q.poll();

      bfsBuilder.append(curNode).append(" ");
      for(int i = 0; i<map.get(curNode).size(); i++){
        int nextNode = map.get(curNode).get(i);

        if(!visited[nextNode]){

          q.add(nextNode);
          visited[nextNode] = true;
        }
      }
    }

  }

  static void dfs(int node){
    visited[node] = true;

    dfsBuilder.append(node).append(" ");

    for(int i = 0; i<map.get(node).size(); i++){
      int nextNode = map.get(node).get(i);
      if(!visited[nextNode]){
        dfs(nextNode);
      }
    }
  }



}

