import java.util.ArrayList;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        String storeyString = String.valueOf(storey);
        int length = storeyString.length();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        for(int i = 0; i<length; i++){
            char ch = storeyString.charAt(i);
            list.add(Integer.parseInt(String.valueOf(ch)));
        }

        for(int i = length; i > 0 ; i--){
            if(list.get(i) > 5 || (list.get(i) >= 5 && list.get(i-1) >= 5)){
                list.set(i-1, list.get(i-1) + 1);
                answer += 10 - list.get(i);
            }else{
                answer += list.get(i);
            }
        }
        
        answer += list.get(0);

        return answer;
    }
}
