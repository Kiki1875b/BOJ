import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    String letters = "AEIOU";
    Set<String> dictionary = new TreeSet<>();

     void dfs(String cur){
        if(cur.length() <= 5 && cur.length() > 0){
            dictionary.add(cur);
        }

        if(cur.length() > 5) return;

        for(int i = 0; i<5; i++){
            dfs(cur + letters.charAt(i));
        }
    }

    public int solution(String word) {
        int answer = 0;
        
        dfs("");
        
        int idx = 1;
        
        for(String s : dictionary){
            if(Objects.equals(s, word)){
                return idx;
            }
            
            idx++;
        }
        
        return answer;
    }
}

