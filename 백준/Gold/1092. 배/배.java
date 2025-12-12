import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] crane;
    static int[] box;
    static int[] firstCrane; // 각 박스를 들 수 있는 최소 크레인 인덱스

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        crane = new int[N];
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(s[i]);
        }

        M = Integer.parseInt(br.readLine());
        box = new int[M];
        s = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            box[i] = Integer.parseInt(s[i]);
        }

        Arrays.sort(crane); // 오름차순
        Arrays.sort(box);   // 오름차순

        // 가장 무거운 박스를 가장 센 크레인이 못 들면 불가능
        if (box[M - 1] > crane[N - 1]) {
            System.out.println(-1);
            return;
        }

        // 각 박스마다 "처음으로 이 박스를 들 수 있는 크레인 인덱스"를 미리 계산
        firstCrane = new int[M];
        for (int i = 0; i < M; i++) {
            int w = box[i];
            int idx = lowerBound(crane, w); // crane[idx] >= w 인 최소 idx
            firstCrane[i] = idx;
        }

        int left = 1;
        int right = M;  // 최대 M분 (1분에 1개씩, 아무도 안 겹치면)
        int answer = M;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (possible(mid)) {
                answer = mid;
                right = mid - 1; // 더 작은 시간도 가능한지 탐색
            } else {
                left = mid + 1;  // 시간이 부족하니 늘려야 함
            }
        }

        System.out.println(answer);
    }

    // T분 안에 모든 박스를 옮길 수 있는지 체크
    static boolean possible(int T) {
        int[] remain = new int[N];
        Arrays.fill(remain, T); // 각 크레인마다 T개까지 옮길 수 있음

        // 박스를 무거운 것부터 처리 (오름차순 정렬되어 있으니 뒤에서부터)
        for (int i = M - 1; i >= 0; i--) {
            int idx = firstCrane[i]; // 이 박스를 들 수 있는 최소 크레인 인덱스

            // 이 박스를 들 수 있는 크레인들 중, 남은 칸이 있는 크레인을 찾는다.
            while (idx < N && remain[idx] == 0) {
                idx++;
            }

            if (idx == N) {
                // 이 박스를 들 수 있는 크레인이 더 이상 없음 → T분으로는 불가능
                return false;
            }

            remain[idx]--; // 해당 크레인이 이 박스를 옮김
        }

        return true; // 모든 박스를 배정 성공
    }

    // crane 배열에서 val 이상이 처음 나오는 인덱스 (lower_bound)
    static int lowerBound(int[] arr, int val) {
        int l = 0, r = arr.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] >= val) r = m;
            else l = m + 1;
        }
        return l;
    }
}
