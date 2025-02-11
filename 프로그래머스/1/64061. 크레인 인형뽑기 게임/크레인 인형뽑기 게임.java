
import java.util.Stack;

class Solution {
 public int solution(int[][] board, int[] moves) {
    int answer = 0;

    Stack<Integer> s = new Stack<>();

    for(int move : moves){
      int idx = move - 1;
      int x = 0;

      while(x <= board.length - 1){
        int cur = board[x][idx];

        if(cur == 0) {
          x++;
        }else{

          if(!s.empty() && s.peek() == cur){
            answer += 2;
            s.pop();
          }else{
            s.push(cur);
          }

          board[x][idx] = 0;
          break;
        }
      }

    }

    return answer;
  }
}
