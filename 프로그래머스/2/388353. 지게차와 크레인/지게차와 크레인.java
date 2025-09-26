import java.util.*;

class Solution {
    
    char[][] m;
    int minus = 0;
    
    int[] dx = new int[]{1,-1,0,0};
    int[] dy = new int[]{0,0,1,-1};
    
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        m = new char[storage.length][storage[0].length()];
        for(int i = 0; i<storage.length; i++){
            for(int j = 0; j<storage[0].length(); j++){
                m[i][j] = storage[i].charAt(j);
            }
        }
                
        
        for(String command: requests){
            if(command.length() == 1){
                simulate1(command.charAt(0));
            }else{
                simulate2(command.charAt(0));
            }
        }
        
        return storage.length * storage[0].length() - minus;
    }
    
    void simulate1(char target){
        List<int[]> targets = new ArrayList<>();
        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m[0].length; j++){
                if(m[i][j] == target && bfs(i,j)){
                    targets.add(new int[]{i, j});
                }
            }
        }
        
        for(int[] t: targets){
            m[t[0]][t[1]] = '.';
            minus++;
        }
    }
    
    void simulate2(char target){
        for(int i = 0; i<m.length; i++){
            for(int j = 0; j<m[0].length; j++){
                if(m[i][j] == target){
                    m[i][j] = '.';
                    minus++;
                }
            }
        }
    }
    

    
    boolean bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        boolean[][] visited = new boolean[m.length][m[0].length];
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            
            for(int i = 0; i<4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= m.length || ny >= m[0].length) return true;
                if(m[nx][ny] != '.') continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        
        return false;
    }
    

}