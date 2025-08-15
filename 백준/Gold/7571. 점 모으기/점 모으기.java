import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] row = new int[M];
        int[] col = new int[M];

        for(int i = 0; i<M; i++){
            String[] tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            row[i] = a;
            col[i] = b;
        }

        Arrays.sort(row);
        Arrays.sort(col);

        int rowMedian = row[M/2];
        int colMedian = col[M/2];

        int total = 0;
        for(int i =0; i<row.length; i++){
            total += (Math.abs(row[i] - rowMedian) + Math.abs(col[i] - colMedian));
        }

        System.out.println(total);

    }
}
