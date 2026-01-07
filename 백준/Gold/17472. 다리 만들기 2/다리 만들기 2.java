import java.util.*;
import java.io.*;

class Main {
        
    static class Edge {
        int a, b, cost;
        public Edge(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
    static List<Edge> edges = new ArrayList<>();
    static int N, M;
    static int[][] map;
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static boolean[][] visited;
    static int[] parent;
    
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static boolean union(int  a, int b){
        int ra = find(a);
        int rb = find(b);
        
        if(ra == rb) return false;
        parent[rb] = ra;
        return true;
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int i =0 ; i<N; i++){
            s = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(s[j]);
            }
        }
        
        
        int id = 1;
        for(int i = 0; i<N; i++){
            for(int j = 0; j< M; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    bfs(i, j, id);
                    id++;
                }
            }
        }
        
        parent = new int[id];
for(int i = 1; i < id; i++){
    parent[i] = i;
}
        for(int x = 0; x <N; x++){
            for(int y = 0; y < M; y++){
                if(map[x][y] == 0) continue;
                
                int num = map[x][y];
                
                for(int i = 0; i<4; i++){
                    int len = 0;
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    
                    while(nx >= 0 && ny >= 0 && nx < N && ny < M){
                        if(map[nx][ny] == num) break;
                        if(map[nx][ny] != 0 ){
                            if(len >= 2){
                                edges.add(new Edge(num, map[nx][ny], len));
                            }
                            break;
                        }
                        nx += dx[i];
                        ny += dy[i];
                        len++;
                    }
                }
            }
        }
        
        Collections.sort(edges, (a,b) -> (a.cost - b.cost));
        
        
        int total =0, lands = 0;
        for(Edge e : edges){
            if(union(e.a, e.b)){
                total += e.cost;
                lands++;
            }
        }
        System.out.println(lands == id - 2 ? total : -1);
        
    }
    
    static void bfs(int sx, int sy, int id){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        map[sx][sy] = id;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            for(int i = 0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 0) continue; 
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                map[nx][ny] = id;
            }
        }
    }
}