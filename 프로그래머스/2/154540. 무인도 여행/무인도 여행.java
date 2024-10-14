import java.util.*;

class Solution {

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    int width, height;

    boolean[][] visited;

    public int bfs(int sx, int sy, String[] maps){
        Queue<int[]> q = new LinkedList<>();
        int temp = maps[sx].charAt(sy);
        q.add(new int[]{sx, sy, maps[sx].charAt(sy) - '0'});
        visited[sx][sy] = true;
        int total = 0;
        while(!q.isEmpty()){
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int cost = current[2];

            total += cost;

            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
                if(maps[nx].charAt(ny) == 'X') continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, maps[nx].charAt(ny) - '0'});
            }
        }
        return total;
    }
    public int[] solution(String[] maps) {


        height = maps.length;
        width = maps[0].length();
        visited = new boolean[height][width];

        List<Integer> sizeList = new ArrayList<>();
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                if(!visited[i][j] && maps[i].charAt(j) != 'X'){
                    int tmp = bfs(i,j, maps);
                    sizeList.add(tmp);
                }
            }
        }

        Collections.sort(sizeList);
        int[] answer = new int[sizeList.size()];

        for(int i = 0; i<sizeList.size(); i++){
            answer[i] = sizeList.get(i);
        }

        return sizeList.isEmpty() ? new int[]{-1} : answer;
    }
}
