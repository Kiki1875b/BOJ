import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        
        int[][] arr = new int[N][M];
        for(int i = 0; i<N; i++){
            input = br.readLine().split(" ");
            for(int j = 0 ; j<M; j++){
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        int[][] p = new int[N + 1][M + 1];
        
        for(int i = 1; i<=N; i++){
            p[i][1] = p[i - 1][1] + arr[i - 1][0];
        }
        
        for(int i = 1; i<=M; i++){
            p[1][i] = p[1][i - 1] + arr[0][i - 1];
        }
        
        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=M; j++){
                p[i][j] = p[i - 1][j] + p[i][j - 1] - p[i - 1][j - 1] + arr[i - 1][j - 1];
            }
        }
        
        int T = Integer.parseInt(br.readLine());
        
        for(int i = 0; i<T; i++){
            input = br.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = Integer.parseInt(input[2]);
            int y2 = Integer.parseInt(input[3]);
            
            System.out.println(p[x2][y2] - p[x2][y1 - 1] - p[x1 - 1][y2] + p[x1 - 1][y1 - 1]);
        }
    }
}