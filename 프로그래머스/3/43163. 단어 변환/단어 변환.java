import java.util.*;
class Solution {
    static class State {
        String s;
        int cost;
        
        public State (String s , int cost){
            this.s = s;
            this.cost = cost;
        }
        
        @Override
        public boolean equals(Object o){
            State st = (State) o;
            return st.s.equals(this.s);
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(s);
        }
    }
    public int solution(String begin, String target, String[] words) {
        
        int answer = 0;
        boolean contains = false;
        for(String w : words){
            if(w.equals(target)){
                contains = true;
                break;
            }
        }
        if(!contains) return 0;
        
        Queue<State> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        q.add(new State(begin, 0));
        
        while(!q.isEmpty()){
            State cur = q.poll();
            
            if(cur.s.equals(target)){
                return cur.cost;
            }
            
            for(String next : words){
                if(cur.s.equals(next)) continue;
                if(diff(cur.s, next) > 1) continue;
                if(visited.contains(next)) continue;
                
                visited.add(next);
                q.add(new State(next, cur.cost + 1));
            }
        }
        
        
        return 0;
    }
    
    
    
    int diff(String s1, String s2){
        int ret = 0;
        for(int i = 0; i<s1.length(); i++){
            if(s1.charAt(i) == s2.charAt(i)) continue;
            ret++;
        }
        return ret;
    }
}