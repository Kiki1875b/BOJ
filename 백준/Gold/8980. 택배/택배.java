import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int capacity = Integer.parseInt(input[1]);
        int M = Integer.parseInt(br.readLine());

        int[][] arr = new int[M][3];

        for(int i = 0; i<M; i++){
            input = br.readLine().split(" ");

            arr[i][0] = Integer.parseInt(input[0]);
            arr[i][1] = Integer.parseInt(input[1]);
            arr[i][2] = Integer.parseInt(input[2]);
        }

        //// 받는 마을이 가까운 순이어야 미래 선택지가 넓어진다.
        Arrays.sort(arr, (a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

//        for(int i = 0; i<arr.length;i++){
//            System.out.println(Arrays.toString(arr[i]));
//        }

        int[] villages = new int[2001];
        int curCap = 0;
        int ans = 0;

        for(int i = 0; i<arr.length; i++){
            int[] cur = arr[i];
            curCap -= villages[cur[0]];
            villages[cur[0]] = 0;
            int toAdd = (capacity - curCap) >= cur[2] ? cur[2] : (capacity - curCap);
            villages[cur[1]] += toAdd;
            curCap += toAdd;
            ans += toAdd;
        }

        System.out.println(ans);
    }
}
