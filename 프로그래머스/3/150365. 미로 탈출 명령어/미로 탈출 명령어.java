import java.util.*;



class Solution {
    int K, N, M;
    int[] goal;
    int[] start;
    char[][] map;
    String[] l = new String[]{"d", "l", "r", "u"};
    Map<Character, int[]> dirMap = new HashMap<>();
    boolean foundAns = false;
    String ans = "";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        map = new char[n][m];
        x--; y--; r--; c--;
        K = k;
        N = n;
        M = m;
        dirMap.put('d', new int[]{1,0});
        dirMap.put('l', new int[]{0, -1});
        dirMap.put('r', new int[]{0,1});
        dirMap.put('u', new int[]{-1,0});
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                
                if(i == x && j == y){
                   map[i][j] = 'S';
                    start = new int[]{i, j};
                } else if(i == r && j == c){
                    map[i][j] = 'E';
                    goal = new int[]{i, j};
                } else {
                    map[i][j] = '.';
                }
            }
        }
        
        createComb("", start[0], start[1], 0);
        
        
        
        
        return foundAns ? ans : "impossible";
    }

    
    
    void createComb(String current, int x, int y, int steps){
        if(foundAns) return;
        int remains = K - steps;
        int dist = Math.abs(x - goal[0]) + Math.abs(y - goal[1]);
        
        if(dist > remains) return;
        if((remains - dist) % 2 != 0) return; 
        
        if(steps == K){
            if(x == goal[0] && y == goal[1]){
                foundAns = true;
                ans = current;
                
            }
            return; 
        }
        
        for(int i = 0; i<4; i++){
            String next = l[i];
            int[] nDir = dirMap.get(next.charAt(0));
            int nx = x + nDir[0];
            int ny = y + nDir[1];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            createComb(current + next, nx, ny, steps+1);
            if(foundAns) return;
        }
        
    }
}