
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
  int x,y;

  public Pair(int x, int y){
    this.x = x;
    this.y = y;
  }
}

class Main {

    static int N, M;
    static boolean[][] visited;
    static String[] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bf.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        visited = new boolean[N][M];
        map = new String[N];

        int enemy = 0;
        int my = 0;

        for (int i = 0; i < N; i++) {
            String tmp = bf.readLine();
            map[i] = tmp;
        }

        for (int i = 0; i < N; i++) {
          for (int j = 0; j<M; j++){
            char cur = map[i].charAt(j);
            int curSize = 0;
            if(!visited[i][j]) {
              curSize = getCurrentSize(cur, i, j);
            }

            if(cur == 'B'){
              enemy += (curSize * curSize);
            } else {
              my += (curSize * curSize);
            }
          }
        }

      System.out.println(my + " " + enemy);

    }

    static int getCurrentSize(char c, int x, int y) {
      int size = 1;
      Queue<Pair> q = new LinkedList<>();
      Pair start = new Pair(x, y);
      visited[x][y] = true;

      q.add(start);

      while(!q.isEmpty()){
        Pair cur = q.poll();
        int cx = cur.x;
        int cy = cur.y;

        for(int i = 0; i<4; i++){
          int nx = cx + dx[i];
          int ny = cy + dy[i];

          if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
          if(visited[nx][ny]) continue;
          if(map[nx].charAt(ny) != c) continue;

          Pair next = new Pair(nx,ny);
          q.add(next);
          visited[nx][ny] = true;
          size++;
        }


      }
      return size;
    }
}

