import java.util.*;
class Solution {
    int[] dx = new int[]{1,-1,0,0};
    int[] dy = new int[]{0,0,1,-1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            
            int x=  cur[0];
            int y = cur[1];
            int cost = cur[2];
            
            if(x == maps.length - 1 && y == maps[0].length - 1){
                return cost;
            }
            
            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length) continue;
                if(maps[nx][ny] == 0) continue;
                if(visited[nx][ny]) continue;
                
                int nCost = cost + 1;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, nCost});
            }
        }
        
        return -1;
    }
}