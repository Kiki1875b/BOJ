
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매
 */
class Solution {
  public int[] solution(String[] gems) {

    Set<String> unique = new HashSet<>(Arrays.asList(gems));
    Map<String, Integer> window = new HashMap<>();
    int totalTypes = unique.size();

    int left = 0, right = 0;
    int minDiff = Integer.MAX_VALUE;
    int[] ans = {1, gems.length};
    
    while(right < gems.length){
      window.put(gems[right], window.getOrDefault(gems[right], 0) + 1);
      right++;
      
      while(window.size() == totalTypes){
        if(right - left < minDiff){
          minDiff = right - left;
          ans[0] = left + 1;
          ans[1] = right;
        }
        
        window.put(gems[left], window.get(gems[left]) - 1);
        if(window.get(gems[left]) == 0){
          window.remove(gems[left]);
        }
        left++;
      }
    }
    
    return ans;
  }
}

