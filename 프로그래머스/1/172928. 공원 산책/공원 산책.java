
import java.util.HashMap;
import java.util.Map;

class Point{
  int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
class Solution {

  Map<String, int[]> dirMap = new HashMap<>();
  public int[] solution(String[] park, String[] routes) {

    dirMap.put("E", new int[]{0,1});
    dirMap.put("W", new int[]{0,-1});
    dirMap.put("N", new int[]{-1,0});
    dirMap.put("S", new int[]{1,0});

    Point start = null;

    for(int i = 0; i<park.length; i++){
      for(int j = 0; j<park[i].length(); j++){
        if(park[i].charAt(j) == 'S'){
          start = new Point(i,j);
        }
      }
    }

    Point currentPos = new Point(start.x, start.y);
    for(String op : routes){
      String[] curOp = op.split(" ");
      
      String dir = curOp[0];
      int cnt = Integer.parseInt(curOp[1]);

      int x = currentPos.x;
      int y = currentPos.y;
      
      boolean meetsOb = false;

      for(int i = 0; i<cnt; i++){
        int nx = x + dirMap.get(dir)[0];
        int ny = y + dirMap.get(dir)[1];

        if(nx < 0 || ny < 0 || nx >= park.length || ny >= park[0].length()) {
          meetsOb = true;
          continue;
        };
        if(park[nx].charAt(ny) == 'X') {
          meetsOb = true;
          continue;
        };
        
        x = nx;
        y = ny;
      }
      if(!meetsOb) {
        currentPos.x = x;
        currentPos.y = y;
      }
    }

    return new int[]{currentPos.x, currentPos.y};
  }

}