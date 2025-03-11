import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> m = new HashMap();
        
        for(int i = 0; i<keymap.length; i++ ){
            String key = keymap[i];
            for(int j = 0; j< key.length(); j++ ){
                char c = key.charAt(j);
                m.put(c, Math.min(m.getOrDefault(c, Integer.MAX_VALUE), j + 1));
            }
        }
        
        int[] ans = new int[targets.length];
        for(int i = 0; i<targets.length; i++){
            int pressCnt = 0;
            boolean isPossible = true;
            for(char c: targets[i].toCharArray()){
                if(!m.containsKey(c)){
                    isPossible = false;
                    break;
                }
                pressCnt+=m.get(c);
            }
            ans[i] = isPossible ? pressCnt : -1;
        }
        
        return ans;

    }
}