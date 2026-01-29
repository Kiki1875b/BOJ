import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        Map<String, Integer> current = new HashMap<>();
        Map<String, Integer> total = new HashMap<>();
        Set<String> s = new TreeSet<>();
        for(String rec : records){
            String[] sp = rec.split(" ");
            int time = timeToInt(sp[0]);
            String carNum = sp[1];
            String state = sp[2];
            s.add(carNum);
            if(sp[2].equals("IN")) {
                current.put(carNum, time);
            }else{
                int diff = time - current.get(carNum);
                
                total.put(carNum, total.getOrDefault(carNum, 0) + diff);
                current.remove(carNum);
            }
        }
        
        int[] ans = new int[s.size()];
        int last = timeToInt("23:59");
        
        for(Map.Entry<String, Integer> entry : current.entrySet()){
            String num = entry.getKey();
            int diff = last - entry.getValue();
            // int fee = calculateFee(diff, fees);
            // System.out.println( "CAR NUM=" + num + " diff, fee = " + diff + " " + fee);
            total.put(num, total.getOrDefault(num, 0) + diff);
        }
        
        
        int idx = 0;
        for(String carNum : s){
            ans[idx] = calculateFee(total.get(carNum), fees);
            idx++;
        }
        
        
        return ans;
    }
    
    
    public int timeToInt(String time){
        String[] sp = time.split(":");
        
        int hours = Integer.parseInt(sp[0]);
        int minutes = Integer.parseInt(sp[1]);
        
        return hours * 60 + minutes;
    }
    
    public int calculateFee(int period, int[] fees){
        if(period <= fees[0]) return fees[1];
        
        period -= fees[0];
        
        int toCalc =  period / fees[2];
        if(period % fees[2] != 0) toCalc += 1;
        
        return fees[1] + toCalc * fees[3];
    }
}