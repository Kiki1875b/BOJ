import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int solution(int[] elements) {
        int n = elements.length;
        int[] doubled = new int[2 * n];
        for (int i = 0; i < n; i++) {
            doubled[i] = elements[i];
            doubled[i + n] = elements[i];
        }

        Set<Integer> sumSet = new HashSet<>();
        for (int len = 1; len <= n; len++) {
            int sum = 0;
            for (int i = 0; i < len; i++) {
                sum += doubled[i];
            }
            sumSet.add(sum);
            for (int i = 1; i < n; i++) {
                sum = sum - doubled[i - 1] + doubled[i + len - 1];
                sumSet.add(sum);
            }
        }
        return sumSet.size();
    }

}
