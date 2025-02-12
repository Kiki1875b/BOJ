
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 컨테이너 벨트가 총 2개 있다 : 본 벨트, 보조 벨트
 *
 * 본 벨트는 맨 앞의 상자만 뺄 수 있다
 * 보조벨트는 마지막에 넣은 상자만 뺄 수 있다
 * 본 벨트에서 순서가 맞지 않는다면 보조벨트에 저장한다
 * 총 몇개의 상자가 실리는지
 *
 * 1,2,3,4,5 를 order = [4,3,1,2,5]  의 순서로 만들 수 있는지
 */

public class Solution {
  public int solution(int[] order) {
    int ans = 0;
    Queue<Integer> q = new LinkedList<>();
    Stack<Integer> s = new Stack<>();


    for(int i = 1; i<=order.length; i++) q.offer(i);

    for(int i = 0; i<order.length; i++){
      while(true){
        boolean didSomething = false;

        if(!q.isEmpty() && q.peek() == order[i]){
          q.poll();
          ans++;
          didSomething = true;
          break;
        }

        if(!s.isEmpty() && s.peek() == order[i]){
          s.pop();
          ans++;
          didSomething = true;
          break;
        }

        if(!q.isEmpty() && q.peek() != order[i]){
          s.push(q.poll());
          didSomething = true;
        }

        if(!didSomething){
          if(q.isEmpty() && (s.isEmpty() || s.peek() != order[i])) return ans;
        }
      }
    }

    return ans;
  }
}