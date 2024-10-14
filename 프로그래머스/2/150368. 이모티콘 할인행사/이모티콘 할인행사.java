import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {

    ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
    int[] rates = {10,20,30,40};
    int num;
    public void createComb(ArrayList<Integer> current){
        if(current.size() == num){
            combinations.add(new ArrayList<>(current));
            return;
        }

        for(int i = 0; i<4; i++){
            current.add(rates[i]);
            createComb(current);
            current.remove(current.size() - 1);
        }

    }

    public ArrayList<Integer> calculate(ArrayList<Integer> comb, int[][] users, int[] emoticons){
        int plus = 0;
        int totalCost = 0;
        for(int i = 0; i<users.length; i++){
            int rate = users[i][0];
            int cost = users[i][1];
            int currentCost = 0;
            for(int j = 0 ; j<comb.size(); j++){
                if(comb.get(j) >= rate){
                    currentCost += (int) Math.round(emoticons[j] * (1 - comb.get(j) / 100.0));

                }

                if(currentCost >= cost){
                    plus++;
                    currentCost = 0;
                    break;
                }
            }

            totalCost += currentCost;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(plus);
        ret.add(totalCost);
        return ret;
    }
    public int[] solution(int[][] users, int[] emoticons) {

        num = emoticons.length;

        createComb(new ArrayList<>());
        ArrayList<ArrayList<Integer>> calculated = new ArrayList<>();

        for(ArrayList<Integer> c : combinations){
            ArrayList<Integer> tmp = calculate(c, users, emoticons);
            calculated.add(tmp);
        }

        Collections.sort(calculated, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                if(a.get(0) == b.get(0)) return b.get(1) - a.get(1);
                return b.get(0) - a.get(0);
            }
        });
        int[] answer = new int[2];
        answer[0] = calculated.get(0).get(0);
        answer[1] = calculated.get(0).get(1);

        return answer;
    }


}
