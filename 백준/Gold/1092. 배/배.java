import java.util.*;
import java.io.*;

class Main {
    
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        String[] input = br.readLine().split(" ");
        Integer[] crains = new Integer[N];
        for(int i = 0; i<N; i++){
            crains[i] = Integer.parseInt(input[i]);
        }
        
        int M = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        Integer[] boxes = new Integer[M];
        
        for(int i = 0; i<M; i++){
            boxes[i] = Integer.parseInt(input[i]);
        }
        
        Arrays.sort(boxes, Collections.reverseOrder());
        Arrays.sort(crains);
        
        if(crains[N - 1] < boxes[0]) {
            System.out.println(-1);
            return;
        }
        int t = 0;
        boolean[] visited = new boolean[M];
        int moved = 0;
        
        while(moved < M){
            int boxIdx = 0;
            for(int i = N - 1; i>=0; i--){
                while(boxIdx < M) {
                    if(!visited[boxIdx] && crains[i] >= boxes[boxIdx]){
                        visited[boxIdx] = true;
                        moved++;
                        boxIdx++;
                        
                        break;
                    }
                    boxIdx++;
                }
            }
            t++;
        }
        
        System.out.println(t);
    }
}