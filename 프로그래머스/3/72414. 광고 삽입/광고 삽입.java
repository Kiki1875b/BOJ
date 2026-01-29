class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int play = toInt(play_time);
        int adv = toInt(adv_time);
        long[] diff = new long[play + 2];
        
        for(String log : logs){
            String[] sp = log.split("-");
            int start = toInt(sp[0]);
            int end = toInt(sp[1]);
            diff[start]+=1;
            diff[end]-=1;            
        }
        
        long[] timeline = new long[play + 1];
        timeline[0] = diff[0];
        
        for(int i = 1; i<=play; i++){
            timeline[i] = timeline[i - 1] + diff[i];
        }
        
        long[] prefix = new long[play + 1];
        
        prefix[0] = timeline[0];
        for(int i = 1; i <= play; i++){
            prefix[i] = prefix[i - 1] + timeline[i];
        }
        
        
        long bestWatch = prefix[adv - 1];
        int time = 0;
        
        for(int i = 1; i + adv - 1 <= play; i++){
            int j = i + adv - 1;
            long watch = prefix[j] - prefix[i - 1];
            if(watch > bestWatch){
                bestWatch = watch;
                time = i;
            }
        }
        
        
        return toString(time);
    }
    
    int toInt(String s){
        String[] sp = s.split(":");
        return Integer.parseInt(sp[0]) * 3600 + Integer.parseInt(sp[1]) * 60 + Integer.parseInt(sp[2]);
    }
    
    String toString(int num){
        StringBuilder sb = new StringBuilder();
        
        int hours = num / 3600;
        num %= 3600;
        int mins = num / 60;
        num %= 60;
        sb.append(hours <= 9 ? "0" + String.valueOf(hours) : hours);
        sb.append(":");
        sb.append(mins <= 9 ? "0" + String.valueOf(mins) : mins);
        sb.append(":");
        sb.append(num <= 9 ? "0" + String.valueOf(num) : num);
        
        return sb.toString();
    }
}