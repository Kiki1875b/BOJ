
import java.util.*;

/**
 * row x col
 *  (x1, y1, x2, y2)인 정수 4개로 표현
 *  x1 행 y1 열부터 x2 행 y2 열까지의
 *  영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전
 */
class Solution {

  int[][] map;
  static List<Integer> ans = new ArrayList<>();
  public int[] solution(int rows, int columns, int[][] queries) {
    int[] answer = {};

    int querySize = queries.length;

    map = new int[rows][columns];
    int num = 1;
    for(int i = 0; i<rows; i++){
      for(int j = 0; j<columns; j++){
        map[i][j] = num;
        num++;
      }
    }

    for(int[] query : queries){
      runQuery(query);
      System.out.println();
    }

    //Collections.sort(ans);

    answer = new int[ans.size()];
    
    for(int i = 0; i< ans.size(); i++){
      answer[i] = ans.get(i);
    }

    return answer;
  }

  void runQuery(int[] query){
    Deque<Integer> q = new ArrayDeque<>();

    int minX = query[0] - 1;
    int minY = query[1] - 1;
    int maxX = query[2] - 1;
    int maxY = query[3] - 1;

    int minimum = Integer.MAX_VALUE;

    for(int i = minY; i<= maxY; i++){
      q.offer(map[minX][i]);
      minimum = Math.min(minimum, map[minX][i]);
    }

    for(int i = minX + 1; i<=maxX; i++){
      q.offer(map[i][maxY]);
      minimum = Math.min(minimum, map[i][maxY]);
    }

    for(int i = maxY - 1; i >= minY; i--){
      q.offer(map[maxX][i]);
      minimum = Math.min(minimum, map[maxX][i]);

    }

    for(int i = maxX - 1; i > minX; i--){
      q.offer(map[i][minY]);
      minimum = Math.min(minimum, map[i][minY]);
    }

    ans.add(minimum);
    q.offerFirst(q.pollLast());

    for(int i = minY; i<= maxY; i++){
      map[minX][i] = q.pollFirst();
    }

    for(int i = minX + 1; i<=maxX; i++){
      map[i][maxY] = q.pollFirst();
    }

    for(int i = maxY - 1; i >= minY; i--){
      map[maxX][i] = q.pollFirst();
    }

    for(int i = maxX - 1; i > minX; i--){
      map[i][minY] = q.pollFirst();
    }
  }

//  public static void main(String[] args) {
//    Solution solution = new Solution();
//    solution.solution(6, 6, new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}});
//  }
}
