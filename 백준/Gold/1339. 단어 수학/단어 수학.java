import java.util.*;
import java.io.*;


class CharacterOccurance implements Comparable<CharacterOccurance> {
    
    char c;
    int weight;
    
    public CharacterOccurance(char c, int weight){
        this.c = c;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(CharacterOccurance co){
        return co.weight - this.weight; // 큰 가중치 먼저
    }
}
public class Main {
    
    static Map<Character, Integer> m = new HashMap<>();
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> l = new ArrayList<>();
        
        int N = Integer.parseInt(br.readLine());
        
        for(int i = 0 ;i <N; i++){
            l.add(br.readLine());
        }
        
        PriorityQueue<CharacterOccurance> pq = new PriorityQueue<>();

        for(String s : l){
            for(int i = 0; i<s.length(); i++){
                char c = s.charAt(i);
                int idx = (int) Math.pow(10, s.length() - i - 1);
                int cur = m.getOrDefault(c, 0);
                cur += idx;   // 가중치 누적
                m.put(c, cur);
            }
        }
        
        
        for(Character c : m.keySet()){
            int cur = m.get(c);
            // System.out.println("CHAR: " + c + " maxIdx: " + cur[0] + " times: " + cur[1]);
            pq.add(new CharacterOccurance(c, cur));
        }
        
        int num = 9;
        
        Map<Character, Integer> cVal = new HashMap<>();
        
        while(!pq.isEmpty()){
            CharacterOccurance co = pq.poll();
            cVal.put(co.c, num);
            // System.out.println("CHAR: " + co.c + " val: " + num);
            num--;
        }
        
        int ans = 0;
        for(String s : l){
            String tmp = "";
            for(int i = 0; i<s.length(); i++){
                int val = cVal.get(s.charAt(i));
                tmp += Integer.toString(val);
                
            }
            ans += Integer.parseInt(tmp);
        }
        
        System.out.println(ans);
    }
}