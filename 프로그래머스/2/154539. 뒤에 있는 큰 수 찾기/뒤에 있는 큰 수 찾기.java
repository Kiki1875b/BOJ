import java.util.Arrays;
import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        
        Stack<Integer> s = new Stack<>();
        
        for(int i = numbers.length - 1; i >= 0 ; i--){
            while(!s.isEmpty() && s.peek() <= numbers[i]) s.pop();
            if(!s.isEmpty()) answer[i] = s.peek();
            
            s.push(numbers[i]);
        }

        return answer;
    }
}
