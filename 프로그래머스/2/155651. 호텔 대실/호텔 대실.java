import java.util.*;

class Solution {

    int[][] formatted;
    int width, height;

    public int[] formatTime(String[] time){
        String[] start = time[0].split(":");
        String[] end = time[1].split(":");

        int[] ret = new int[2];

        ret[0] = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
        ret[1] = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]) + 10;

        return ret;
    }

    public int solution(String[][] book_time) {
        int answer = 0;

        Map<Integer, Integer> m = new TreeMap<>();

        height = book_time.length;
        width = book_time[0].length;
        formatted = new int[height][width];

        for(int i = 0; i<height; i++){
            int[] tmp = formatTime(book_time[i]);
            formatted[i] = tmp;
        }

        for(int[] i : formatted){
            if(!m.containsKey(i[0])){
                m.put(i[0], 1);
            }else{
                m.put(i[0], m.get(i[0]) + 1);
            }

            if(!m.containsKey(i[1])){
                m.put(i[1], -1);
            }else{
                m.put(i[1], m.get(i[1])- 1);
            }
        }


        int tmp = 0;

        Iterator<Map.Entry<Integer,Integer>> it = m.entrySet().iterator();
        while(it.hasNext()){
            
            Map.Entry<Integer, Integer> tmpMap = it.next();
            tmp += tmpMap.getValue();
            answer = Math.max(tmp, answer);
        }





        return answer;
    }
}
