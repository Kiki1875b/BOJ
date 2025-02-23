import java.util.*;
/**
 * 같은 시간에 게임을 이용하는 사람이 m 명 늘어날 떄마다, 서버 1대 추가 필요.
 *
 * 어느 시간대의 이용자가 m명 미만이라면 증설 x
 *
 * 어느 시간대의 이용자가 n x m <= 이용자 수 < (n + 1) x m 이라면, 최소 n 대의 서버가 운영중이어야 한다
 *
 * 한번 증설한 서버는 k 시간동안 운영한다
 *       ex ) k = 5 일때 10시에 증설한 서버는 10 ~ 15 시 동안 운영
 *
 * 하루동안 모든 이용자가 게임을 하기 위해 서버를 최소 몇번 증설해야 하는지
 */

class P{
  int num, endTime;

  public P(int num, int endTime) {
    this.num = num;
    this.endTime = endTime;
  }
}
class Solution {
  public int solution(int[] players, int m, int k) {
    int answer = 0;
    int currentServerCnt = 0;
    Queue<P> q = new LinkedList<>();

    for(int i = 0; i < players.length; i++){

      int currentPlayers = players[i];

      while(!q.isEmpty() && q.peek().endTime <= i){
        currentServerCnt -= q.poll().num;
      }

      if(currentPlayers / m > currentServerCnt){
        int serversToAdd = (currentPlayers / m) - currentServerCnt;
        currentServerCnt += serversToAdd;
        q.add(new P(serversToAdd, i + k));
        answer += serversToAdd;
      }

    }


    return answer;
  }


}
