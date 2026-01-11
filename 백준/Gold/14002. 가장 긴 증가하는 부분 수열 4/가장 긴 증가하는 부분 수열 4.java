import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] arr = new int[s.length];

        for(int i = 0; i<arr.length; i++){
            arr[i] = Integer.parseInt(s[i]);
        }

        int[] dp = new int[s.length];
        int[] parent = new int[s.length];
        for(int i = 0; i<s.length; i++){
            parent[i] = i;
            dp[i] = 1;
        }

        int maxEnd = 0;
        int maxLen =1;
        for(int i = 1; i<s.length; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if(dp[i] > maxLen){
                maxLen = dp[i];
                maxEnd = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        int cur = maxEnd;

while(true){
    ans.add(arr[cur]);
    if(parent[cur] == cur) break;
    cur = parent[cur];
}
Collections.reverse(ans);

        System.out.println(ans.size());
        for(int i = 0; i < ans.size(); i++){
            System.out.print(ans.get(i) + " ");
        }


    }
}
