import java.util.*;

/**
 * 1 x 1 격자 형태 미로
 * 각 칸은
 *    S : 시작
 *    E : 레버
 *    O : 통로
 *    X : 벽
 *
 * 출발지점 -> 레버 -> 출구의 가장 빠른 시간
 */

class P {
  int x, y, cost;

  public P(int x, int y, int cost) {
    this.x = x;
    this.y = y;
    this.cost = cost;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    P p = (P) o;
    return x == p.x && y == p.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}

class Solution {
  int height, width;
  P start, end, lever;
  Map<P, Integer> dist = new HashMap();

  boolean[][] visited;
  int[] dx = {1,-1,0,0};
  int[] dy = {0,0,1,-1};
  public int solution(String[] maps) {
    int answer = 0;

    height = maps.length;
    width = maps[0].length();

    visited = new boolean[height][width];

    for(int i = 0; i < height; i++) {
      for(int j = 0 ; j< width; j++){
        if(maps[i].charAt(j) == 'S') start = new P(i,j, 0);
        else if(maps[i].charAt(j) == 'E') end = new P(i,j, 0);
        else if(maps[i].charAt(j) == 'L') lever = new P(i,j, 0);
      }
    }

    int first = bfs(start, lever, maps);


    for(int i =0 ; i<height; i++) {
      Arrays.fill(visited[i], false);
    }

    int second = bfs(lever, end, maps);


    if(first == -1 || second == -1) return -1;
    

    return first+ second;
  }

  int bfs(P start, P end, String[] maps){

    Queue<P> q = new LinkedList<>();
    q.offer(new P(start.x, start.y, 0));
    visited[start.x][start.y] = true;

    while(!q.isEmpty()){
      P cur = q.poll();

      int x = cur.x;
      int y = cur.y;
      int curCost = cur.cost;

      if(x == end.x && y == end.y) return curCost;

      for(int i =0 ; i<4; i++){

        int nx = x + dx[i];
        int ny = y + dy[i];
        int nCost = curCost + 1;
        P np = new P(nx, ny, nCost);

        if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
        if(maps[nx].charAt(ny) == 'X') continue;
        if(visited[nx][ny]) continue;
        q.offer(np);
        visited[nx][ny] = true;

      }
    }
    return -1;
  }

}
