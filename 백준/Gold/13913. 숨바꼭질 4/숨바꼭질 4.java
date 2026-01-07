
import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);
        
        int[] parent = new int[100001];
        int[] time = new int[100001];
        boolean[] visited = new boolean[100001];

        Arrays.fill(time, Integer.MAX_VALUE);
        time[N] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        visited[N] = true;
        
        while(!q.isEmpty()){
            int pos = q.poll();
            
            if(pos == K){
                break;
            }
            
            // X + 1
            if(pos + 1 <= 100000 && !visited[pos + 1]){
                visited[pos + 1] = true;
                time[pos + 1] = time[pos] + 1;
                parent[pos + 1] = pos;
                q.add(pos + 1);
            }
            
            // X - 1
            if(pos - 1 >= 0 && !visited[pos - 1]){
                visited[pos - 1] = true;
                time[pos - 1] = time[pos] + 1;
                parent[pos - 1] = pos;
                q.add(pos - 1);
            }
            
            // 2 * X
            if(pos * 2 <= 100000 && !visited[pos * 2]){
                visited[pos * 2] = true;
                time[pos * 2] = time[pos] + 1;
                parent[pos * 2] = pos;
                q.add(pos * 2);
            }
        }

        // 경로 역추적
        List<Integer> path = new ArrayList<>();
        int cur = K;
        while(cur != N){
            path.add(cur);
            cur = parent[cur];
        }
        path.add(N);

        // 출력
        System.out.println(time[K]);
        for(int i = path.size() - 1; i >= 0; i--){
            System.out.print(path.get(i) + " ");
        }
    }
}