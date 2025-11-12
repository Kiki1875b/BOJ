import java.io.*;

public class Main {
    static int N;
    static int ans = 0;
    static int[] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        board = new int[N];
        dfs(0);
        System.out.println(ans);
    }
    
    static void dfs(int row){
        if(row == N){
            ans++;
            return;
        }
        
        for(int i = 0; i<N; i++){
            if(check(row, i)){
                board[row] = i;
                dfs(row + 1);
            }
        }
    }
    
    static boolean check(int row, int col){
        for(int i = 0; i<row;i++){
            if(board[i] == col) return false;
            if(Math.abs(i - row) == Math.abs(col - board[i])) return false;
        }
        return true;
    }
}