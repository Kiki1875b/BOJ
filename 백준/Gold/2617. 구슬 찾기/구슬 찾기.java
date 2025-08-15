
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{

    static List<List<Integer>> heavier = new ArrayList<>();
    static List<List<Integer>> lighter = new ArrayList<>();
    static int N, M;
    static boolean[][] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for(int i =0; i<=N; i++){
            heavier.add(new ArrayList<>());
            lighter.add(new ArrayList<>());
        }


        for(int i = 0; i<M; i++){
            String[] input2 = br.readLine().split(" ");
            int a = Integer.parseInt(input2[0]);
            int b = Integer.parseInt(input2[1]);

            heavier.get(b).add(a);
            lighter.get(a).add(b);
        }

        int mid = (N + 1)/2;
        int ans = 0;
        for(int i = 1; i<=N; i++){
            int h = bfs(i, heavier);
            int l = bfs(i, lighter);

            if(h >= mid || l >= mid) {
                ans++;
            }
        }

        System.out.println(ans);


    }

    static int bfs(int start, List<List<Integer>> map){

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        q.add(start);
        int cnt = 0;
        while(!q.isEmpty()){
            int current = q.poll()  ;

            for(int n : map.get(current)){
                if(!visited[n]){
                    visited[n] = true;
                    q.add(n);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
