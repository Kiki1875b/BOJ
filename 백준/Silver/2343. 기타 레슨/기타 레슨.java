import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N, M;
    static List<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = bf.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);

        String[] input = bf.readLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            arr.add(Integer.parseInt(input[i]));
        }

        int left = Collections.max(arr); // 최소 블루레이 크기는 가장 긴 강의의 길이
        int right = arr.stream().mapToInt(Integer::intValue).sum(); // 최대 블루레이 크기는 모든 강의 길이의 합
        int ans = right;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid)) {
                ans = mid; // 가능한 경우, 더 작은 크기를 찾기 위해
                right = mid - 1;
            } else {
                left = mid + 1; // 불가능한 경우, 블루레이 크기를 늘린다
            }
        }

        System.out.println(ans);
    }

    static boolean isPossible(int size) {
        int count = 1; // 필요한 블루레이 개수
        int sum = 0;

        for (int length : arr) {
            if (sum + length > size) {
                count++;
                sum = length;

                if (count > M) { // 블루레이 개수가 M을 초과하면 불가능
                    return false;
                }
            } else {
                sum += length;
            }
        }

        return true;
    }
}