
import java.util.*;


class Solution {
  int[][] prefixSum;
  public int solution(int[][] board, int[][] skill) {
    int answer = 0;
        prefixSum = new int[board.length + 2][board[0].length + 2 ];

    for(int[] s : skill){
      int type = s[0];
      int r1 = s[1];
      int c1 = s[2];
      int r2 = s[3];
      int c2 = s[4];
      int degree = type == 1 ? -s[5] : s[5];
      
      prefixSum[r1][c1] += degree;
      prefixSum[r1][c2 + 1] -= degree;
      prefixSum[r2 + 1][c1] -= degree;
      prefixSum[r2 + 1][c2 + 1] += degree;
    }
    
    
    for(int i = 0; i<prefixSum.length; i++){
      for (int j = 1; j<prefixSum[0].length; j++){
        prefixSum[i][j] += prefixSum[i][j - 1];
      }
    }
    
    for(int i = 1; i<prefixSum.length; i++){
      for(int j = 0; j<prefixSum[0].length; j++){
        prefixSum[i][j] += prefixSum[i - 1][j];
      }
    }
    
    for(int i = 0; i<board.length; i++){
      for(int j = 0; j<board[0].length; j++){
        if(board[i][j] + prefixSum[i][j] > 0) answer++;
      }
    }
    return answer;
  }
}