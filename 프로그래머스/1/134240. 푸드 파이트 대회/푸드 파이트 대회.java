import java.util.*;

class Solution {
    public String solution(int[] food) {
        String answer = "";
        List<Integer> cnt = new ArrayList<>();

        for (int i = 1; i < food.length; i++) {
            cnt.add(food[i] / 2);
        }

        for (int i = 0; i < cnt.size(); i++) {
            for (int j = 0; j < cnt.get(i); j++) {
                answer += (i + 1);
            }
        }
        
        answer += "0";
        
        for (int i = cnt.size() - 1; i >= 0; i--) {
            for (int j = 0; j < cnt.get(i); j++) {
                answer += (i + 1);
            }
        }

        return answer;
    }
}
