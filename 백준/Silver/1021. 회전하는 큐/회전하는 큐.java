
import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        Deque<Integer> q = new ArrayDeque<>();

        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        for(int i = 1; i<=N; i++){
            q.addLast(i);
        }

        s = br.readLine().split(" ");

        int[] arr = new int[s.length];
        for(int i = 0; i<s.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        int ans = 0;
        for(int num : arr){

            if(q.peek() == num){
                q.pollFirst();
            }else{

                Deque<Integer> left = new ArrayDeque<>(q);
                Deque<Integer> right = new ArrayDeque<>(q);
                int tmp = Integer.MAX_VALUE;
                int cnt = 0;

                while(left.peek() != num){
                    left.addLast(left.pollFirst());
                    cnt++;

                }

                tmp = cnt;
                cnt = 0;
                while(right.peek() != num){
                    right.addFirst(right.pollLast());
                    cnt++;
                }

                tmp = Math.min(tmp, cnt);

                if(tmp == cnt) q = right;
                else q = left;

                q.pollFirst();
                ans+=tmp;
            }
        }

        System.out.println(ans);

    }
}
