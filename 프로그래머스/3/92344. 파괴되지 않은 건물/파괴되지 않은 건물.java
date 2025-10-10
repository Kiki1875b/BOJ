class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length; int M = board[0].length;
        
        int[][] effect = new int[N + 1][M + 1];
        
        for(int[] s: skill){
            int type = s[0];
            int x1 = s[1], y1 = s[2], x2 = s[3], y2 = s[4], degree = s[5];
            
            if(type == 1) degree = -degree;
            
            effect[x1][y1] += degree;
            effect[x2 + 1][y2 + 1] += degree;
            effect[x1][y2 + 1] -= degree;
            effect[x2 + 1][y1] -= degree;
        }
        
        for(int i = 0; i<= N; i++){
            for(int j = 1; j<= M; j++){
                effect[i][j] += effect[i][j - 1];
            }
        }
        
        for(int i = 1; i <= N; i++){
            for(int j = 0; j<= M; j++){
                effect[i][j] += effect[i - 1][j];
            }
        }
        
        int ans = 0;
        for(int i = 0 ; i<N; i++){
            for(int j = 0; j<M; j++){
                if(board[i][j] + effect[i][j] > 0) ans++;
            }
        }
        
        
        return ans;
    }
}