public class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder();
        int[] num = {4, 1, 2};
        
        while (n > 0) {
            int remainder = n % 3;
            n /= 3;
            
            if (remainder == 0) {
                n--;
            }
            
            answer.insert(0, num[remainder]);
        }
        
        return answer.toString();
    }
}