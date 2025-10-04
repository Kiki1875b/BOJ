import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0 ; i< numbers.length; i++){
            String bin = Long.toBinaryString(numbers[i]);
            int len = 1;
            
            while(len < bin.length()){
                len = len * 2 + 1;
            }
            
            
            String tmp = "";
            for(int j = 0; j<len - bin.length(); j++){
                tmp += "0";
            }
            
            tmp += bin;
            bin = tmp;
            
            answer[i] = check(bin) ? 1 : 0;
        }
        
        return answer;
    }
    
    boolean check(String bin){
        int n = bin.length();
        if(n == 1) return true;
        
        char root = bin.charAt(n/2);
        String left = bin.substring(0, n / 2);
        String right = bin.substring(n / 2 + 1);
        
        if(root == '0' && (left.contains("1") || right.contains("1"))) return false;
        
        return check(left) && check(right);
    }

}