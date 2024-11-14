import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class Main {
    static Set<String> s = new HashSet<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int ans = 0;

        s.add("c=");
        s.add("c-");
        s.add("dz=");
        s.add("d-");
        s.add("lj");
        s.add("nj");
        s.add("s=");
        s.add("z=");

        int left = 0;

        while(left < input.length()){
            boolean found = false;

            for(int length = 3; length >= 1; length--){
                if (left + length <= input.length()) {
                    String tmp = input.substring(left, left + length);

                    if (s.contains(tmp)) {
                        left += length;
                        ans++;
                        found = true;
                        break;
                    }
                }
            }
            if(!found){
                ans++;
                left++;
            }
        }
        System.out.println(ans);
    }
}
