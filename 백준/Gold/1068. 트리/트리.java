
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;


class Main {

    static List<List<Integer>> children;
    static int N;
    static int target;
    static int ans =0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        children = new ArrayList<>();


        for(int i = 0; i<=N; i++) children.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());

        int start = -1;
        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(num == -1){
                start = i;
                continue;
            }

            children.get(num).add(i);
        }




        target = Integer.parseInt(br.readLine());
        if(children.get(start).isEmpty() && target != 0){
            System.out.println(1);
            return;
        }
        if(target == start || N == 1) {
            System.out.println(0);
            return;
        }

        children.get(target).clear();

        bfs(start);

        if(N != 1 && target != start && ans == 0){
            System.out.println(1);
            return;
        }

        System.out.println(ans);
    }


    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        while(!q.isEmpty()){
            int cur = q.poll()  ;
            if(cur != start && children.get(cur).isEmpty() || (children.get(cur).size() == 1 && children.get(cur).get(0) == target)){
                ans++;
            }
            for(int next: children.get(cur)){
                if(next != target && !visited[next]){
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }

}




