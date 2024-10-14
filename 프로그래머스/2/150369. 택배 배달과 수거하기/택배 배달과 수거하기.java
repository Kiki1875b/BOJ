import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Integer> delStack = new Stack<>();
        Stack<Integer> pickupStack = new Stack<>();
        
        for(int d : deliveries) delStack.push(d);
        for(int p : pickups) pickupStack.push(p);
        
        while(!delStack.isEmpty() || !pickupStack.isEmpty()){
            
            while(!delStack.isEmpty() && delStack.peek() == 0) delStack.pop();
            while(!pickupStack.isEmpty() && pickupStack.peek() == 0) pickupStack.pop();
            
            answer += Math.max(delStack.size(), pickupStack.size()) * 2L;
            
            int box = 0;
            while(!delStack.isEmpty() && box <= cap){
                int last = delStack.pop();
                if(last + box <= cap){
                    box += last;
                }else{
                    delStack.push(last - (cap - box));
                    break;
                }
            }
            
            box = 0;
            
            while(!pickupStack.isEmpty() && box <= cap){
                int last = pickupStack.pop();
                if(last + box <= cap){
                    box += last;
                }else{
                    pickupStack.push(last - (cap - box));
                    break;
                }
            }
            
            
        }
        
        
        return answer;
    }
}