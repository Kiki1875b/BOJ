import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dimensions = br.readLine().split(" ");
        int N = Integer.parseInt(dimensions[0]);
        int M = Integer.parseInt(dimensions[1]);
        
        char[][] floor = new char[N][M];
        for (int i = 0; i < N; i++) {
            floor[i] = br.readLine().toCharArray();
        }
        
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (floor[i][j] == '-') {
                    if (j == 0 || floor[i][j - 1] != '-') {
                        count++;
                    }
                } 
                else if (floor[i][j] == '|') {
                    if (i == 0 || floor[i - 1][j] != '|') {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
