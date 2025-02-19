
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] nm = br.readLine().split(" ");
    int N = Integer.parseInt(nm[0]);
    int M = Integer.parseInt(nm[1]);

    char[][] grid = new char[N][M];
    for (int i = 0; i < N; i++) {
      grid[i] = br.readLine().toCharArray();
    }

    int maxSize = 1;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        char num = grid[i][j];

        for (int k = 1; i + k < N && j + k < M; k++) {
          if (grid[i][j] == grid[i + k][j] &&
              grid[i][j] == grid[i][j + k] &&
              grid[i][j] == grid[i + k][j + k]) {
            maxSize = Math.max(maxSize, (k + 1) * (k + 1));
          }
        }
      }
    }

    System.out.println(maxSize);
  }
}

