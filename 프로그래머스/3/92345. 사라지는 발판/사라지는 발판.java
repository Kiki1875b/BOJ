import java.util.*;

class Result {
    
    boolean win;
    int move;
    
    public Result(boolean win, int move){
        this.win = win;
        this.move = move;
    }
}

class Solution {
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        Result res = dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], true);
        
        return res.move;
    }
    
    Result dfs(int[][] board, int ax, int ay, int bx, int by, boolean aTurn){
        
        int x = aTurn ? ax : bx;
        int y = aTurn ? ay : by; 
        
        if(board[x][y] == 0) return new Result(false, 0);
        
        int minWin = Integer.MAX_VALUE;
        int maxLose = 0;
        boolean canMove = false;
        boolean canWin = false;
        
        
        for(int i = 0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) continue;
            if(board[nx][ny] == 0) continue;
            
            canMove = true;
            
            board[x][y] = 0;
            
            Result next;
            if(aTurn){
                next = dfs(board, nx, ny, bx, by, false);                
            } else {
               next = dfs(board, ax, ay, nx, ny, true); 
            }
            
            board[x][y] = 1;
            
            if(next.win){
                maxLose = Math.max(maxLose, next.move + 1);
            }else {
                canWin = true;
                minWin= Math.min(minWin, next.move + 1);
            }
        }
        
        if(!canMove) return new Result(false, 0);
        
        if(canWin){
            return new Result(true, minWin);
        }else{
            return new Result(false, maxLose);
        }
        
    }
}