import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = br.readLine().toCharArray();
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n - 8; i++) {
            for (int j = 0; j <= m - 8; j++) {
                int c1 = 0, c2 = 0;
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        char w = ((x + y) % 2 == 0) ? 'W' : 'B';
                        char b = ((x + y) % 2 == 0) ? 'B' : 'W';
                        if (a[i + x][j + y] != w) c1++;
                        if (a[i + x][j + y] != b) c2++;
                    }
                }
                res = Math.min(res, Math.min(c1, c2));
            }
        }
        System.out.println(res);
    }
}
