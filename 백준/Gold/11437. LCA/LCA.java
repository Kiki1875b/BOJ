
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int depth;
    int num;
    Node parent;

    public Node(int num) {
        this.depth = 0;
        this.num = num;
        this.parent = null;
    }
}

class Main {

    static int N, M;
    static List<Node> nodes;
    static List<List<Integer>> adj;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] input = bf.readLine().split(" ");
        N = Integer.parseInt(input[0]);


        nodes = new ArrayList<>();
        for(int i = 0; i < N + 1; i++) nodes.add(new Node(i));
        adj = new ArrayList<>();
        for(int i = 0;  i<N + 1; i++) adj.add(new ArrayList<>());


        for (int i = 0; i < N - 1; i++) {
            String[] tmp = bf.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        bfs(1);

        M = Integer.parseInt(bf.readLine());

        for(int i = 0; i<M; i++){
            String[] tmp = bf.readLine().split(" ");
            int q1 = Integer.parseInt(tmp[0]);
            int q2 = Integer.parseInt(tmp[1]);

            System.out.println(LCA(q1, q2));
        }


    }


    static int LCA(int q1, int q2){
        Node n1 = nodes.get(q1);
        Node n2 = nodes.get(q2);

        while(n1.depth > n2.depth) n1 = n1.parent;
        while(n2.depth > n1.depth) n2 = n2.parent;

        while(n1.num != n2.num){
            n1 = n1.parent;
            n2 = n2.parent;
        }

        return n1.num;
    }

    static void bfs(int root){
        Queue<Integer> q = new LinkedList<>();
        nodes.get(root).depth = 1;
        q.add(root);

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i = 0; i<adj.get(cur).size(); i++){
                int next = adj.get(cur).get(i);
                if(nodes.get(next).depth == 0){
                    nodes.get(next).depth = nodes.get(cur).depth + 1;
                    nodes.get(next).parent = nodes.get(cur);
                    q.add(nodes.get(next).num);
                }
            }
        }
    }
}

