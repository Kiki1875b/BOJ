import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ans = 0;
        String input = sc.nextLine();
        Set<String> s = new HashSet<>();
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
            for(int length =3; length >=1; length--){
                if(length + left <= input.length()){
                    String tmp = input.substring(left, left+length);
                    if(s.contains(tmp)){
                        left += length;
                        found = true;
                        ans++;
                        break;
                    }
                }
            }
            
            if(!found){
                left++;
                ans++;
            }
        }
        System.out.println(ans);
    }
}
