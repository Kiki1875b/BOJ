
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class Solution {
  public int solution(int[][] routes) {
    int answer = 0;

    Arrays.sort(routes, (a,b) -> Integer.compare(a[1], b[1]));
    int prevCamEndPos = Integer.MIN_VALUE;
    int camCount = 0;

    for(int[] route: routes){

      if(route[0] > prevCamEndPos){
        camCount++;
        prevCamEndPos = route[1];
      }

    }



    return camCount;
  }
}
