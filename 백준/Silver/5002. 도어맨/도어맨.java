
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        String s = bf.readLine();
        int ans = 0;
        int cnt = 0;

        Map<Character, Integer> m = new HashMap<>();
        m.put('M', 1);
        m.put('W', -1);

        LinkedList<Character> q = new LinkedList<>();

        for(int i = 0; i<s.length(); i++){
            q.add(s.charAt(i));
        }


        while(!q.isEmpty()){
            boolean moved = false;

            if(!q.isEmpty() && Math.abs(cnt + m.get(q.peekFirst())) <= N){
                moved = true;
                cnt += m.get(q.pollFirst());
                ans++;
            }else if(q.size() >= 2 && Math.abs(cnt + m.get(q.get(1))) <= N){
                moved = true;
                cnt += m.get(q.remove(1));
                ans++;
            }

            if(!moved) break;
        }

        System.out.println(ans  );
    }
}
