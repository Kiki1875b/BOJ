import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,1,-1};
    int width, height;
    int[] start = {0,0};
    int[] lever = {0,0};
    int[] goal = {0,0};

    public int bfs(String[] maps, int[] curStart, int[] curGoal){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];
        queue.add(new int[]{curStart[0], curStart[1], 0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int cost = cur[2];
            
            if(curX == curGoal[0] && curY == curGoal[1]){
                return cost;
            }
            for(int i = 0; i<4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
                if(maps[nx].charAt(ny) == 'X') continue;
                if(visited[nx][ny]) continue;
                
                visited[nx][ny] = true;
                queue.add(new int[]{nx,ny,cost + 1});
                    
            }
        }
        
        return -1;
    }

    public int solution(String[] maps) {
        int answer = 0;
        height = maps.length;
        width = maps[0].length();

        for(int i = 0; i<height; i++){
            for(int j = 0; j < width; j++){
                if(maps[i].charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                }else if(maps[i].charAt(j) == 'L'){
                    lever[0] = i;
                    lever[1] = j;
                }else if(maps[i].charAt(j) == 'E'){
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }

        int stoL = bfs(maps, start, lever);
        int ltoG = bfs(maps, lever, goal);


        return (stoL == -1 || ltoG == -1) ? -1 : stoL + ltoG;
    }
}
