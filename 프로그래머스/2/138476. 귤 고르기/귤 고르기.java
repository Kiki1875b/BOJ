

import java.util.*;

/**
 * 수확한 귤 중 k 를 골라 상자 하나에 담아 판매
 * 귤을 크기별로 분류하여 서로 다른 종류의 수를 최소화
 *
 *  [1, 3, 2, 5, 4, 5, 2, 3]  이고, 귤을 6개 판매하고 싶다 -> 1,4 를 제외한 나머지 [2,2,3,3,5,5] 를 판매
 *  서로 다른 종류의 귤 최솟값
 */
class Solution {
  public int solution(int k, int[] tangerine) {
    int answer = 0;

    Map<Integer, Integer> m = new HashMap<>();
    for(int i : tangerine){
      m.put(i, m.getOrDefault(i, 0) + 1);
    }

    List<Integer> sizes = new ArrayList<>();
    sizes.addAll(m.values());

    Collections.sort(sizes, (a,b) -> {
      return b - a;
    });

    for(int i : sizes){
      k -= i;
      answer++;
      if(k <= 0) break;
    }

    return answer;
  }

}
