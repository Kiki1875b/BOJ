import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        while (T > 0) {
            T--;
            
            int N, M;
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            
            for (int i = 0; i < M; i++) {
                br.readLine(); 
            }
            
            System.out.println(N - 1);
        }
    }
}
