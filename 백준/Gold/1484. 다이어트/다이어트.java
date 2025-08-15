
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int G;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());


        boolean found = false;
        long l = 1, r = 2;

        while(l < r && r < 100000){
            long diff = r*r - l*l;

            if(diff == G){
                found =true;
                System.out.println(r);
                r++;
            }else if (diff > G){
                l++;
            }else{
                r++;
            }
        }

        if (!found) {
            System.out.println(-1);
        }
    }


}
