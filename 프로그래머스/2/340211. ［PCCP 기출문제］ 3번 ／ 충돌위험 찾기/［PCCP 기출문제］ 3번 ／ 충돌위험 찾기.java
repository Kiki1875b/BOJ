import java.util.HashMap;
import java.util.Map;

class Solution {

    Map<String, Integer> timePos; // 시간, x, y = collision  수
    Map<Integer, Map<Integer,Integer>> m;        
    int answer= 0;
    public void calcDanger(int[] route){
        int time = 0;
        int[] last = {0,0};


        for(int i = 0; i<route.length - 1; i++){
            Map<Integer, Integer> startPos = m.get(route[i]);
            Map<Integer, Integer> destPos = m.get(route[i + 1]);

            int startX = startPos.keySet().iterator().next();
            int startY = startPos.get(startX);

            int destX = destPos.keySet().iterator().next();
            int destY = destPos.get(destX);

            while(startX != destX || startY != destY){

                String key = time+"-"+startX+"-"+startY;
                timePos.put(key, timePos.getOrDefault(key, 0) + 1);
                if(timePos.get(key) == 2) answer++;
                
                if(startX < destX) startX++;
                else if(startX > destX) startX--;
                else if(startY < destY) startY++;
                else if(startY > destY) startY--;

                time++;
                last[0] = startX;
                last[1] = startY;
            }
        }
        
        String key = time+"-"+last[0]+"-"+last[1];
        timePos.put(key, timePos.getOrDefault(key, 0) + 1);
        if(timePos.get(key) == 2) answer++;
    }
    public int solution(int[][] points, int[][] routes) {

        m = new HashMap<>();
        timePos = new HashMap<>();

        for(int i = 0; i<points.length; i++){
            for(int j = 0; j<points[0].length; j++){
                Map<Integer, Integer> tmp = new HashMap<>();
                tmp.put(points[i][0], points[i][1]);
                m.put(i + 1, tmp);
            }
        }

        for(int i = 0; i<routes.length; i++){
            calcDanger(routes[i]);
        }

        return answer;
    }
}
