
import java.util.*;
import java.io.*;

class CO implements Comparable<CO>{
    char c;
    int weight;

    public CO(char c, int weight) {
        this.c = c;
        this.weight= weight;
    }

    @Override
    public int compareTo(CO o){
        return o.weight - this.weight;
    }
}


public class Main {

    static Map<Character, Integer> m = new HashMap<>();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> m = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        List<String> l = new ArrayList<>();

        for(int i = 0; i<N; i++){
            String tmp = br.readLine();
            l.add(tmp);
            for(int j = 0; j<tmp.length(); j++){
                char c = tmp.charAt(j);
                int w = m.getOrDefault(c, 0);
                int toAdd = (int) Math.pow(10, tmp.length() - j - 1);
                w += toAdd;
                m.put(c, w);
            }
        }

        PriorityQueue<CO> pq = new PriorityQueue<>();

        for(Character key: m.keySet()){
            pq.add(new CO(key, m.get(key)));
        }

        int num = 9;
        Map<Character, Integer> vMap = new HashMap<>();

        while (!pq.isEmpty()){
            CO cur = pq.poll();
            vMap.put(cur.c, num);
            num--;
        }
        int ans = 0;
        for(String s : l){
            String tmp = "";
            for(int i = 0; i<s.length(); i++){
                int val = vMap.get(s.charAt(i));
                tmp += Integer.toString(val);
            }
            ans += Integer.parseInt(tmp);
        }

        System.out.println(ans);
    }
}