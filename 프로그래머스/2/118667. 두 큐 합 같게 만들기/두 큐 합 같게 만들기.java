
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;        
        long target = 0L;
        long q1Sum = 0L;
        long q2Sum = 0L;
        
        long[] q = new long[(queue1.length + queue2.length) * 2];
        
        for(int i = 0 ; i<queue1.length; i++){
            q1Sum += (long) queue1[i];
            q2Sum += (long) queue2[i];
        }
        

        target = (q1Sum + q2Sum) / 2;
        if((q1Sum + q2Sum) % 2 != 0) return -1;
        for(int i = 0; i<queue1.length; i++) q[i] = (long) queue1[i];
        for(int i = 0; i<queue2.length; i++) q[i + queue1.length] = (long) queue2[i];
        for(int i = 0; i<queue1.length + queue2.length; i++) q[i + queue1.length + queue2.length] = q[i];

        int round = 0; boolean found = false;
        int limit = queue1.length + queue2.length;
        long curSum = q1Sum;
        int left = 0; int right = queue1.length;
        
        // for(long i: q) System.out.print(i + " ");
        // System.out.println();
        // System.out.println("curSUm: " + curSum);
        
        while(round < limit* 2){
            if(curSum == target){
                found = true;
                break;
            }    
            
            if(curSum > target){
                curSum -= q[left]; left++;
                // System.out.println("subtract left - res: " + curSum);
            } else {
                curSum += q[right]; right++;
                // System.out.println("add right - res: " + curSum);

            }
            round++;
        }
        
        return found ? round : -1;
    }
}