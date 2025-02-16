import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 바이러스는 연결된 네트워크를 통해 전파된다
 * 컴퓨터의 수, 연결 정보가 주어질때, 1번 컴퓨터를 통해 바이러스에 걸리게 되는 컴퓨터의 수 출력
 */
class Main {

  static int N, M;
  static int ans = 0;
  static Map<Integer, List<Integer>> computerMap;
  static boolean[] visited;
  public static void main(String[] args) throws IOException {

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(bf.readLine());
    M = Integer.parseInt(bf.readLine());
    visited = new boolean[N + 1];
    computerMap = new HashMap<>();

    for(int i = 1; i<=N; i++){
      computerMap.put(i, new ArrayList<>());
    }

    for(int i = 0; i<M; i++){
      String[] input = bf.readLine().split(" ");
      int a = Integer.parseInt(input[0]);
      int b = Integer.parseInt(input[1]);

      computerMap.get(a).add(b);
      computerMap.get(b).add(a);
    }

    bfs();

    System.out.println(ans);

  }

  static void bfs(){
    int start = 1;
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    visited[start] = true;

    while(!q.isEmpty()){
      int curNode = q.poll();

      if(!computerMap.get(curNode).isEmpty()){
        for(int i = 0; i<computerMap.get(curNode).size(); i++){
          int nextNode = computerMap.get(curNode).get(i);
          if(visited[nextNode]) continue;

          q.offer(nextNode);
          visited[nextNode] = true;
          ans++;
        }
      }
    }
  }

}

