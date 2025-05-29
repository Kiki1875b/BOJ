
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

class Pair {
  int x;
  int y;

  public Pair(int x, int y){
    this.x = x;
    this.y = y;
  }
}


class Solution {
  boolean[][] visited;
  int[] dx = new int[]{-1,1,0,0};
  int[] dy = new int[]{0,0,1,-1};
  public int[] solution(String[] maps) {
    int[] answer;

    visited = new boolean[maps.length][maps[0].length()];

    List<Integer> tmp = new ArrayList<>();
    for(int i = 0; i<maps.length; i++){
      for(int j = 0; j<maps[i].length(); j++){
        if(!visited[i][j] && maps[i].charAt(j) != 'X'){
          int size = bfs(i, j, maps);
          tmp.add(size);
        }
      }
    }
    if(tmp.isEmpty()) return new int[]{-1};
    Collections.sort(tmp);
    answer = new int[tmp.size()];
    for(int i = 0; i<tmp.size(); i++){
      answer[i] = tmp.get(i);
    }

    return answer;
  }


  int bfs(int x, int y, String[] maps){

    Queue<Pair> q = new LinkedList<>();
    q.add(new Pair(x, y));
    visited[x][y] = true;
    int ret = Integer.parseInt(String.valueOf(maps[x].charAt(y)));

    while(!q.isEmpty()){
      Pair cur = q.poll();
      int cx = cur.x;
      int cy = cur.y;

      for(int i = 0; i<4; i++ ){
        int nx = cx + dx[i];
        int ny = cy + dy[i];

        if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length()) continue;
        if(maps[nx].charAt(ny) == 'X') continue;
        if(!visited[nx][ny]){
          visited[nx][ny] = true;
          q.add(new Pair(nx, ny));
          ret += Integer.parseInt(String.valueOf(maps[nx].charAt(ny)));
        }
      }
    }

    return ret;
  }
}