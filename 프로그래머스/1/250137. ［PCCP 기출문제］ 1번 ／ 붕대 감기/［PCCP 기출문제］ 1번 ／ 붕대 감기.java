
import java.util.HashMap;
import java.util.Map;

/**
 * [ 시전 시간, 초당 회복량, 추가 회복량 ]
 *  시전 시간동안 1 초에 초당 회복량 만큼 회복
 *  시전 시간동안 감았다 -> 추가 회복량 만큼 회복
 *
 *  중간에 공격당하면 기술 취소,
 */
public class Solution {
  public int solution(int[] bandage, int health, int[][] attacks) {
    int answer = 0;
    int maxHealth = health;
    int duration = bandage[0];
    int healPerSec = bandage[1];
    int bonusHeal = bandage[2];

    Map<Integer, Integer> attackMap = new HashMap<>();

    for(int[] attack : attacks){
      attackMap.put(attack[0], attack[1]);
    }

    int prevTime = 0;
    int currentTime = 0;
    int currentDuration = 0;
    int currentIdx = 0;

    while(currentIdx < attacks.length){

      currentTime = attacks[currentIdx][0];

      for(int i = prevTime; i < currentTime; i++){
        currentDuration++;

        if(currentDuration == duration){
          currentDuration = 0;
          health += healPerSec;
          health += bonusHeal;
        }else{
          health += healPerSec;
        }

        health = Math.min(health, maxHealth);
      }

      health -= attacks[currentIdx][1];
      currentDuration = 0;

      if(health <= 0) return -1;

      currentIdx++;
      prevTime = currentTime + 1;
    }

    answer = health;

    return answer;
  }}