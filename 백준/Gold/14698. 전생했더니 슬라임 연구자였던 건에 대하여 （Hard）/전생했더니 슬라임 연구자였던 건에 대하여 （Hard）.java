import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {

    static final BigInteger MOD = BigInteger.valueOf(1_000_000_007L);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) pq.offer(Long.parseLong(st.nextToken()));

            // 전기 에너지가 전혀 필요 없는 경우: 곱의 공집합 = 1
            if (N == 1) {
                out.append(1).append('\n');
                continue;
            }

            BigInteger ans = BigInteger.ONE;

            // 항상 가장 작은 두 개를 합치는 그리디 (log로 보면 허프만과 동형)
            while (pq.size() >= 2) {
                long a = pq.poll();
                long b = pq.poll();

                long energy = a * b; // 보장에 의해 long 범위 내
                ans = ans.multiply(BigInteger.valueOf(energy)).mod(MOD);

                // 마지막 합성이 아니라면 새 슬라임을 다시 큐에 넣음
                if (!pq.isEmpty()) pq.offer(energy);
            }

            out.append(ans.mod(MOD)).append('\n');
        }

        System.out.print(out.toString());
    }
}
