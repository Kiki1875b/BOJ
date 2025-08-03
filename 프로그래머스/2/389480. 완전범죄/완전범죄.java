
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {



  int MAX = Integer.MAX_VALUE;
  int ans = Integer.MAX_VALUE;
  public int solution(int[][] info, int n, int m) {
    int answer = 0;
    int[][] dp = new int[m][n]; // dp[b][a] 는 b개의 B흔적으로 a개의 A흔적이 가능한지를 메모
    for(int[] row : dp) Arrays.fill(row, MAX);
  dp[0][0]  = 0;
    for(int[] item: info){
      int aTrace = item[0];
      int bTrace = item[1];

      int[][] newDp = new int[m][n];
      for(int[] row : newDp) Arrays.fill(row, MAX);

      for(int b = 0; b<m; b++){
        for(int a = 0; a<n; a++){
          if(dp[b][a] == MAX) continue; // 여기까지 못온다는 뜻.

          if(a + aTrace < n){ // a index 는 a개를 나타내기 때문에 a + aTrace 가 성립
            newDp[b][a + aTrace] = Math.min(dp[b][a] + aTrace, newDp[b][a+aTrace]);
          }

          if(b + bTrace < m){
            newDp[b + bTrace][a] = Math.min(dp[b][a], newDp[b + bTrace][a]); // dp[b][a] +bTrace 가 아닌 이유는
          }
        }
      }
      dp = newDp;
    }
    
    int res = MAX;
    
    for(int i = 0 ; i<m; i++){
      for(int j = 0; j<n; j++){
        res = Math.min(res, dp[i][j]);
      }
    }
    
    return res == MAX ? -1 : res;
  }


}
