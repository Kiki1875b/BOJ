import java.util.PriorityQueue;
class Solution {
  public int solution(int n, int k, int[] enemy) {
    int answer = 0;

    PriorityQueue<Integer> q = new PriorityQueue<>();
    
    for(int i =0 ; i<enemy.length; i++){
      int e = enemy[i];
      q.add(-e);
      n -= e;
      
      if(n < 0){
        if(k > 0){
          k--;
          n-= q.poll();
        }else{
          break;
        }
      }
      
      answer = i + 1;
    }
    
    return answer;
  }
}
