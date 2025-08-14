
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int N, K;
    static char[] ess = new char[]{
        'a','c','t','n','i'
    };

    static List<String> targets = new ArrayList<>();
    static int ans = 0;
    static boolean[] learned;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NK = br.readLine().split(" ");
        N = Integer.parseInt(NK[0]);
        K = Integer.parseInt(NK[1]);

        if(K < 5) {
            System.out.println(0);
            return;
        }

        if(K >= 26) {
            System.out.println(N);
            return;
        }

        learned = new boolean[26];

        for(char c : ess){
            learned[c - 'a'] = true;
        }

        for(int i = 0; i<N; i++){
            String input = br.readLine();
            String t = input.substring(4, input.length() - 4);
            targets.add(t);
        }

        dfs(0,0);

        System.out.println(ans);

    }

    static void dfs(int idx, int cnt){
        if (cnt == K - 5){
            int count = 0;
            for (String t: targets){
                boolean possible = true;
                for(char c : t.toCharArray()){
                    if(!learned[c - 'a']){
                        possible = false;
                        break;
                    }
                }
                if(possible) count++;
            }

            ans = Math.max(ans, count);
            return;
        }

        for(int i = idx; i<26; i++){
            if(!learned[i]){
                learned[i] = true;
                dfs(i + 1, cnt + 1);
                learned[i] = false;
            }
        }
    }


}
