

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Point{
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
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
}

class Solution {
  Map<Integer, Point> pointMap = new HashMap<>();
  Map<String, Integer> timePosMap = new HashMap<>();

  public int solution(int[][] points, int[][] routes) {
    int answer = 0;

    for(int i = 0; i<points.length; i++){
      pointMap.put(i + 1, new Point(points[i][0], points[i][1]));
    }

    for(int i = 0; i<routes.length; i++){
      simulate(routes[i]);
    }

    for(int val : timePosMap.values()){
      if(val > 1) answer++;
    }
    //System.out.println(timePosMap);
    return answer;
  }

  void simulate(int[] path){
    
    int time = 0;
    
    for(int i = 0; i<path.length - 1; i++){

      Point start = pointMap.get(path[i]);
      Point cur = new Point(start.x, start.y);
      Point goal = pointMap.get(path[i + 1]);

      while(!cur.equals(goal)){
        recordPosition(time, cur);
        if(cur.x < goal.x) cur.x++;
        else if(cur.x > goal.x) cur.x--;
        else if(cur.y < goal.y) cur.y++;
        else if(cur.y > goal.y) cur.y--;
        time++;
      }
    }

    recordPosition(time, pointMap.get(path[path.length - 1]));
  }
  
  void recordPosition(int time, Point cur){
    String key = time+"-"+cur.x+"-"+cur.y;
    timePosMap.put(key, timePosMap.getOrDefault(key, 0) + 1);
  }
}
