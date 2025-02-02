
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 1 ~ n 사이 정수 카드
 * 동전 coin 개
 *
 * 시작 : n/3 장의 카드를 가진다
 * 각 라운드당 카드를 뽑는다
 *    1. 둘다 버리기
 *    2. 코인을 소모해 한장
 *    3. 코인을 둘 소모해 두장
 * 각 라운드당 적힌 수의 합이 n + 1 이 되도록 카드 두장을 내야한다
 * 두 장을 낼 수 없다면 종료
 *
 * 최대 라운드를 구해야 한다
 */
class Solution {

  Set<Integer> hand = new HashSet<>();
  Set<Integer> deck = new HashSet<>();
  int n = 0;
  int target = 0;

  public int solution(int coin, int[] cards) {
    int answer = 1;

    n = cards.length;
    int curIdx = n/3;
    target = n+1;
    for(int i = 0; i<n/3; i++){
      hand.add(cards[i]);
    }

    while(curIdx < n){
      for(int i = 0; i<2; i++){
        deck.add(cards[curIdx]);
        curIdx++;
      }

      if(hand.size() >= 2 && isPossible(hand, hand)){
        answer++;
      }else if(hand.size() >= 1 && deck.size() >= 1 && coin >= 1 && isPossible(hand,deck)){
        answer++;
        coin--;
      }else if(deck.size() >= 2 && coin >= 2 && isPossible(deck, deck)){
        answer++;
        coin -= 2;
      }else{
        break;
      }
    }
    return answer;
  }

  /**
   * 손에 있는 카드만으로 해결 : hand , hand
   * 손 + 덱 : hand, deck
   * */
  boolean isPossible(Set<Integer> s1, Set<Integer> s2){
    for(int num : s1){
      if(s2.contains(target - num)){
        s1.remove(num);
        s2.remove(target - num);
        return true;
      }
    }
    return false;
  }
}
