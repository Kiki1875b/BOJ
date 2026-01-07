
import java.util.*;
import java.io.*;

class Main {
    static int T, n, d, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            d = Integer.parseInt(s[1]);
            c = Integer.parseInt(s[2]);

            List<List<int[]>> arr = new ArrayList<>();
            for(int i =0 ; i<=n; i++) arr.add(new ArrayList<>());

            for(int i = 0; i < d; i++){
                s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int cost = Integer.parseInt(s[2]);

                arr.get(b).add(new int[]{a, cost});
            }

            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            q.add(new int[]{c, 0});
            dist[c] = 0;

            while(!q.isEmpty()){
                int[] cur = q.poll();

                int node = cur[0];
                int time = cur[1];

                if(dist[node] < time) continue;

                for(int[] next : arr.get(node)){
                    int nNode = next[0];
                    int reqTime = time + next[1];
//                    int accTime = time + 1;
//
//                    if(reqTime > accTime){
//                        accTime = reqTime;
//                    }
                    if(dist[nNode] <= reqTime) continue;

                    dist[nNode] = reqTime;
                    q.add(new int[]{nNode, reqTime});
                }
            }

            int cnt = 0;
            int maxTime = 0;
            for(int i = 1; i<=n; i++){
                if(dist[i] != Integer.MAX_VALUE) {
                    cnt++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }

            System.out.println(cnt + " " + maxTime);
        }


    }
}
