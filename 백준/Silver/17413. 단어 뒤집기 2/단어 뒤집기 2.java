
import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();


        int idx = 0;
        Queue<Character> q = new LinkedList<>();
        Deque<Character> q2 = new ArrayDeque<>();
        while(idx < s.length()){
            char c = s.charAt(idx);
            if(c == '<'){
                while(!q2.isEmpty()){
                    q.add(q2.pollLast());
                }
                while(c != '>'){
                    q.add(c);
                    idx++;
                    c = s.charAt(idx);
                }
                q.add(c);

            }else if(c == ' '){
                while(!q2.isEmpty()){
                    q.add(q2.pollLast());
                }
                q.add(c);
            }else{
                q2.add(c);
            }
            idx++;
        }

        while(!q2.isEmpty()){
            q.add(q2.pollLast());
        }

        StringBuilder sb = new StringBuilder();
        for(char c : q)sb.append(c);
        System.out.println(sb);
    }
}
