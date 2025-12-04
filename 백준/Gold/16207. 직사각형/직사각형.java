import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N 입력
        String line1 = br.readLine();
        if(line1 == null) return;
        int N = Integer.parseInt(line1);
        
        // 막대 입력
        String line2 = br.readLine();
        if(line2 == null) return;
        StringTokenizer st = new StringTokenizer(line2);
        
        List<Integer> arr = new ArrayList<>();
        while(st.hasMoreTokens()){
            arr.add(Integer.parseInt(st.nextToken()));
        }
        
        // 1. 막대를 내림차순으로 정렬 (큰 것부터 처리하기 위함)
        Collections.sort(arr, Collections.reverseOrder());
        
        List<Long> sides = new ArrayList<>();
        
        // 2. 인접한 두 막대를 비교하여 변 만들기
        for (int i = 0; i < arr.size() - 1; i++) {
            int first = arr.get(i);
            int second = arr.get(i+1);
            
            // 두 막대의 차이가 1 이하이면 직사각형의 변으로 사용 가능
            // (같거나, 큰 쪽을 1 줄여서 같게 만듦)
            if (first - second <= 1) {
                // 변의 길이는 둘 중 작은 값 (second)
                sides.add((long)second);
                // 두 막대를 사용했으므로 다음 인덱스는 건너뜀
                i++; 
            }
            // 차이가 1보다 크면 first 막대는 버려지고 i는 1만 증가(for문에서 자동 증가)
        }
        
        // 3. 만들어진 변들로 넓이 계산
        // 변들도 큰 순서대로 sides에 들어갔으므로, 앞에서부터 2개씩 곱하면 최대 넓이
        long ans = 0;
        for (int i = 0; i < sides.size() - 1; i += 2) {
            ans += sides.get(i) * sides.get(i+1);
        }
        
        System.out.println(ans);
    }
}