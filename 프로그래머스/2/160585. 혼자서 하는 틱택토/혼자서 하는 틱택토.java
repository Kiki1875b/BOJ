class Solution {
    public int solution(String[] board) {
        
        int xCnt = 0 , oCnt = 0;
        boolean xWin = false, oWin = false;
        
        for(int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                if(board[i].charAt(j) == 'X'){
                    xCnt++;
                }else if (board[i].charAt(j) == 'O'){
                    oCnt++;
                }
            }
        }
        
        for(int i = 0; i<3; i++){
            // 가로 승리 조건
            if(board[i].charAt(0) == 'O' && board[i].charAt(1) == 'O' && board[i].charAt(2) == 'O') oWin = true;
            if(board[i].charAt(0) == 'X' && board[i].charAt(1) == 'X' && board[i].charAt(2) == 'X') xWin = true;
            
            // 세로 승리 조건
            if(board[0].charAt(i) == 'O' && board[1].charAt(i) == 'O' && board[2].charAt(i) == 'O') oWin = true;
            if(board[0].charAt(i) == 'X' && board[1].charAt(i) == 'X' && board[2].charAt(i) == 'X') xWin = true;
        }
        
        // 대각 승리 조건 (정방향)
        if(board[0].charAt(0) == 'O' && board[1].charAt(1) == 'O' && board[2].charAt(2) == 'O') oWin = true;
        if(board[0].charAt(0) == 'X' && board[1].charAt(1) == 'X' && board[2].charAt(2) == 'X') xWin = true;
        
        // 대각 승리 역방향
        if(board[0].charAt(2) == 'O' && board[1].charAt(1) == 'O' && board[2].charAt(0) == 'O') oWin = true;
        if(board[0].charAt(2) == 'X' && board[1].charAt(1) == 'X' && board[2].charAt(0) == 'X') xWin = true;
        
        if(Math.abs(xCnt - oCnt) > 1) return 0;
        if(xCnt > oCnt) return 0;
        if(xWin && oWin) return 0;
        if(oWin && (oCnt - xCnt) != 1) return 0;
        if(xWin && (xCnt - oCnt) != 0) return 0;
        
        return 1;
    }
}
