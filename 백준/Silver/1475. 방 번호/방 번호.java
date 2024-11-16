import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Character, Integer> m = new HashMap<>();
        for(int i = 0; i<=9; i++){
            m.put((char)(i + '0'), 0);
        }

        String input = sc.nextLine();
        int maxCnt = 0;


        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            m.put(current, m.get(current) + 1);
        }


        int sixNine = m.get('6') + m.get('9');

        for(char c = '0'; c <= '9'; c++){
            if(c == '6' || c == '9') continue;
            maxCnt = Math.max(maxCnt, m.get(c));
        }

        maxCnt = Math.max(maxCnt,(int) Math.ceil(sixNine/2.0));
        System.out.println(maxCnt);
    }


}
