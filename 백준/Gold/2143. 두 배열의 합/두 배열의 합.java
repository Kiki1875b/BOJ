import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static long T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        int aLength = Integer.parseInt(br.readLine());
        int[] a = new int[aLength];
        String[] aInput = br.readLine().split(" ");
        for(int i = 0; i<aLength; i++){
            a[i] = Integer.parseInt(aInput[i])  ;
        }


        int bLength = Integer.parseInt(br.readLine());
        int[] b = new int[bLength];
        String[] bInput = br.readLine().split(" ");
        for(int i = 0; i<bLength; i++){
            b[i] = Integer.parseInt(bInput[i]);
        }

        List<Long> aSum = new ArrayList<>();
        for(int i =0; i<aLength; i++){
            long sum = 0;
            for(int j = i; j<aLength; j++){
                sum += a[j];
                aSum.add(sum);
            }
        }

        List<Long> bSum = new ArrayList<>();
        for(int i = 0; i<bLength; i++){
            long sum = 0;
            for(int j = i; j<bLength; j++){
                sum += b[j];
                bSum.add(sum);
            }
        }

        Collections.sort(aSum);
        Collections.sort(bSum);

        int left = 0;
        int right = bSum.size() - 1;
        long cnt = 0;

        while(left < aSum.size() && right >= 0){
            long totalSum = aSum.get(left) + bSum.get(right);

            if(totalSum == T){
                long aVal = aSum.get(left)   ;
                long bVal = bSum.get(right)  ;
                long aCnt = 0;
                long bCnt = 0;

                while(left < aSum.size() && aVal == aSum.get(left)){
                    left++;
                    aCnt++;
                }

                while(right >= 0 && bVal == bSum.get(right)){
                    right--;
                    bCnt++;
                }
                cnt += aCnt * bCnt;
            }else if(totalSum < T){
                left++;
            }else right--;
        }

        System.out.println(cnt);
    }
}
