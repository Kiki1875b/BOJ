import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        List<Integer> p = new ArrayList<>();
        
        for(int person : people) p.add(person);
        
        
        Collections.sort(p);
        
        int l = 0; int r = p.size() - 1;
        int cnt = 0;
        
        while(l <= r){
            int left = p.get(l);
            int right = p.get(r);
            
            if(left + right > limit) {
                cnt++;
                r--;
            } else if(left + right <= limit){
                cnt++;
                r--; l++;
            }
            
            
        }
        
        return cnt;
    }
}