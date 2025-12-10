import java.util.*;
import java.io.*;

class Main{


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int maxDeadline = 0;


        for(int i = 0; i<N; i++){
            String[] tmp = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(tmp[0]);
            arr[i][1] = Integer.parseInt(tmp[1]);
            maxDeadline = Math.max(arr[i][0], maxDeadline);

        }



        Arrays.sort(arr, (a,b) -> {
            if(a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int ans = 0;
        int maxSum = 0;

        int idx = 0;

        for(int i = 0; i < arr.length ; i++){
            int[] cur = arr[i];
            heap.add(cur[1]);
            maxSum += cur[1];
            
            if(heap.size() > cur[0]){
                maxSum -= heap.poll();
            }
            

            ans = Math.max(ans, maxSum);
            
        }

        System.out.println(ans);
    }
}
