import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        
        long sum = 0;
        int limit = queue1.length + queue2.length;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long q1s = 0;
        long q2s = 0;
        
        for(int num : queue1){
            q1s += (long) num;
            sum += (long) num;
            q1.add(num);
        }
        
        for(int num : queue2){
            q2.add(num);
            sum += (long) num;
            q2s += (long) num;
        }
        
        
        if(sum % 2 != 0) return -1;
        int cnt = 0;
        
        while(limit*2 >= cnt){
            if(q1s == q2s){
                return cnt;
            }else if(q1s > q2s){
                int pop = q1.poll();
                q1s -= pop;
                q2s += pop;
                q2.add(pop);
                cnt++;
            }else {
                int pop = q2.poll();
                q1s += pop;
                q2s -= pop;
                q1.add(pop);
                cnt++;
            }
        }
        
        
        return -1;
    }
}