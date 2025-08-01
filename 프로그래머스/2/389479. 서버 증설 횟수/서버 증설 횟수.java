import java.util.LinkedList;
import java.util.Queue;

class Solution {
  public int solution(int[] players, int m, int k) {
    int answer = 0;

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < players.length; i++) {

      while(!q.isEmpty() && q.peek() <= i){
        q.poll();
      }


      int current = players[i];
      int currentServer = q.size();
      int neededServer = current / m;

      if(neededServer > currentServer){
        for(int j = 0; j < neededServer - currentServer; j++){
          q.add(i + k);
          answer++;
        }
      }

    }

    return answer;
  }
}