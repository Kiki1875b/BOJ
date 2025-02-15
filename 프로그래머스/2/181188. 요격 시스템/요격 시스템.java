
import java.util.*;
class Solution {
  public int solution(int[][] targets) {
    int answer = 0;

    
    Arrays.sort(targets, (a,b) -> {
      return a[1] - b[1];
    });
    
    int last = -1;
    
    for(int t[] : targets){
      if(t[0] < last && t[1] >= last) continue;
      else{
        last = t[1];
        answer++;
      }
    }
    
    return answer;
  }
}
