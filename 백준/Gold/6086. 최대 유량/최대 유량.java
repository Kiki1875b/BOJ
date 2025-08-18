import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;



class Main {

    static int N, M;
    static int SIZE = 58;
    static int[][] cap = new int[SIZE][SIZE];
    static int[][] flow = new int[SIZE][SIZE];

    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        for(int i = 0; i<SIZE; i++) graph.add(new ArrayList<>());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            int c = Integer.parseInt(st.nextToken());

            int u = getIdx(a);
            int v = getIdx(b);

            cap[u][v] += c;
            cap[v][u] += c;

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        System.out.println(simulate(getIdx('A'), getIdx('Z')));
    }

    static int getIdx(char c){
        if('A' <= c && c <= 'Z') return c - 'A';
        return c - 'a' + 26;
    }

    static int simulate(int start, int goal){
        int res = 0;

        while(true){
            int[] parent = new int[SIZE];
            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();

            q.add(start);
            parent[start] = start;

            while(!q.isEmpty() && parent[goal] == -1){
                int cur =q.poll();
                for(int next: graph.get(cur)){
                    if(parent[next] == -1 && cap[cur][next] - flow[cur][next] > 0){
                        parent[next] = cur;
                        q.add(next);
                        if(next == goal) break;
                    }
                }
            }

            if(parent[goal] == -1) break;

            int add = Integer.MAX_VALUE ;
            for(int i = goal; i!=start; i = parent[i]){
                int j = parent[i];
                add = Math.min(add, cap[j][i] - flow[j][i]);
            }

            for(int i = goal; i!=start; i = parent[i]) {
                int j = parent[i];

                flow[i][j] -= add;
                flow[j][i] += add;
            }

            res += add;
        }

        return res;
    }
}




