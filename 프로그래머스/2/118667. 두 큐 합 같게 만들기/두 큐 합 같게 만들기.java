import java.util.*;

/**
 * 길이가 같은 큐 2개
 * 하나에서 뽑아 다른 하나에 삽입할 수 있다
 * 목표 : 두 큐의 합이 같게 만들 수 있는 최소 횟수
 */
class Solution {
  public int solution(int[] queue1, int[] queue2) {
    int answer = 0;
    int n = queue1.length;
    List<Integer> merged = new ArrayList<>();

    Long q1 = 0L;
    Long q2 = 0L;
    
    for(int i : queue1) {
      q1 += i;
      merged.add(i);
    }
    for(int i : queue2) {
      q2 += i;
      merged.add(i);
    }

    if((q1 + q2) % 2 != 0 ) return -1;

    int left = 0;
    int right = n;

    while(left <= right && right < merged.size()){
      if(q1.equals(q2)) return answer;

      if(q1 < q2){
        q1 += merged.get(right);
        q2 -= merged.get(right);
        right++;
      }else{
        q1 -= merged.get(left);
        q2 += merged.get(left);
        left++;
      }
      answer++;
    }

    return -1;
  }
  
}