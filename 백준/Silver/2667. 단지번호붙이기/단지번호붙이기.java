
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
  int x, y;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return x == point.x && y == point.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
class Main {
  static int[] dx = {1,-1,0,0};
  static int[] dy = {0,0,1,-1};

  static int N;
  static String[] map;
  static boolean[][] visited;
  static List<Integer> ans = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(bf.readLine());
    map = new String[N];
    visited = new boolean[N][N];

    for(int i = 0 ; i<N; i++){
      map[i] = bf.readLine();
    }

    for(int i = 0; i < N; i++){
      for(int j = 0; j< N; j++){
        if(map[i].charAt(j) == '1'){
          if(!visited[i][j]) bfs(i,j);
        }
      }
    }

    System.out.println(ans.size());
    Collections.sort(ans);
    for(int i : ans){
      System.out.println(i);
    }


  }

   static void bfs(int x, int y){

    Queue<Point> q = new LinkedList<>();
    visited[x][y] = true;
    q.offer(new Point(x, y));
    int cnt = 1;

    while(!q.isEmpty()){
      Point cur = q.poll();
      int cx = cur.x;
      int cy = cur.y;

      for(int i = 0; i < 4; i++){
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx  < 0 || ny < 0 || nx >= N || ny >= N)continue;
        if(map[nx].charAt(ny) == '0')continue;
        if(visited[nx][ny]) continue;

        cnt++;
        visited[nx][ny] = true;
        q.offer(new Point(nx,ny));
      }
    }
    ans.add(cnt);
   }

}

