import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);     // 마을 수
        int C = Integer.parseInt(input[1]);     // 트럭 용량

        int M = Integer.parseInt(br.readLine()); // 박스 정보 개수

        int[][] arr = new int[M][3];  // from, to, boxCount
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(input[0]);
            arr[i][1] = Integer.parseInt(input[1]);
            arr[i][2] = Integer.parseInt(input[2]);
        }

        // 받는 마을(도착지) 기준 오름차순 정렬
        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int[] load = new int[N + 1]; // 각 구간 load[i] = i→i+1 구간에 실린 박스 수
        int ans = 0;

        for (int[] cur : arr) {
            int from = cur[0];
            int to = cur[1];
            int boxes = cur[2];

            // 1) from→to-1 구간 중 남은 용량 최소값을 찾기
            int canLoad = boxes;
            for (int i = from; i < to; i++) {
                canLoad = Math.min(canLoad, C - load[i]);
            }

            if (canLoad <= 0) continue;

            // 2) 실제로 실은 만큼 구간에 반영
            for (int i = from; i < to; i++) {
                load[i] += canLoad;
            }

            ans += canLoad;
        }

        System.out.println(ans);
    }
}
