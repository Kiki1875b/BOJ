class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {0,0,0,0};
        
        int minX = 987654321;
        int minY = 987654321;
        int maxX = -1;
        int maxY = -1;
        
        for(int i = 0; i < wallpaper.length; i++){
            for(int j = 0; j < wallpaper[0].length(); j++){
                if(wallpaper[i].charAt(j) == '#'){
                    minX = Math.min(minX, i);
                    minY = Math.min(minY, j);
                    maxX = Math.max(maxX, i);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        answer[0] = minX;
        answer[1] = minY;
        answer[2] = maxX + 1;
        answer[3] = maxY + 1;
        return answer;
    }
}