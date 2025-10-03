import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;

        // 초기 hand: 앞의 n/3장
        Set<Integer> hand = new HashSet<>();
        for (int i = 0; i < n / 3; i++) hand.add(cards[i]);

        // deck 큐: 나머지 카드
        Queue<Integer> deck = new LinkedList<>();
        for (int i = n / 3; i < n; i++) deck.add(cards[i]);

        // 공개되었지만 아직 '손에 들지 않은' 카드 보관 큐
        // (remove(Object) 써야 하므로 LinkedList 유지)
        Queue<Integer> toDecide = new LinkedList<>();

        int rounds = 0;

        while (deck.size() >= 2) {
            // 라운드 시작: 두 장 공개 (아직 손패 아님)
            int c1 = deck.poll();
            int c2 = deck.poll();
            toDecide.add(c1);
            toDecide.add(c2);

            boolean progressed = false;

            // 0코인: hand 안에서 바로 페어
            int[] hp = findHandPair(hand, target);
            if (hp != null) {
                hand.remove(hp[0]);
                hand.remove(hp[1]);
                rounds++;
                progressed = true;
            } else {
                // 1코인: hand + toDecide (공개 카드 한 장 구매)
                if (!progressed && coin >= 1) {
                    Integer[] hpp = findHandPoolPair(hand, toDecide, target);
                    if (hpp != null) {
                        hand.remove(hpp[0]);              // hand카드 사용
                        removeOneFromQueue(toDecide, hpp[1]); // 공개 카드 1장 구매
                        coin -= 1;
                        rounds++;
                        progressed = true;
                    }
                }
                // 2코인: toDecide + toDecide (공개 카드 두 장 구매)
                if (!progressed && coin >= 2) {
                    Integer[] pp = findPoolPair(toDecide, target);
                    if (pp != null) {
                        removeOneFromQueue(toDecide, pp[0]);
                        removeOneFromQueue(toDecide, pp[1]);
                        coin -= 2;
                        rounds++;
                        progressed = true;
                    }
                }
            }

            if (!progressed) break; // 이 라운드에 못 냈으면 종료
        }

        return rounds + 1; // 첫 라운드도 실패하면 0 반환
    }

    // hand 내부에서 target을 만드는 임의 한 쌍 찾기 (x != y)
    private int[] findHandPair(Set<Integer> hand, int target) {
        for (int x : hand) {
            int y = target - x;
            if (y != x && hand.contains(y)) {
                return new int[]{x, y};
            }
        }
        return null;
    }

    // hand의 x + (toDecide의 need) = target 되는 쌍 찾기
    private Integer[] findHandPoolPair(Set<Integer> hand, Queue<Integer> toDecide, int target) {
        if (toDecide.isEmpty()) return null;
        // toDecide 조회용 Set (큐 자체는 보존)
        HashSet<Integer> poolSet = new HashSet<>(toDecide);
        for (int x : hand) {
            int need = target - x;
            if (need != x && poolSet.contains(need)) {
                return new Integer[]{x, need};
            }
        }
        return null;
    }

    // toDecide 내부에서 target을 만드는 임의 한 쌍 찾기
    private Integer[] findPoolPair(Queue<Integer> toDecide, int target) {
        if (toDecide.size() < 2) return null;
        HashSet<Integer> poolSet = new HashSet<>(toDecide);
        for (int x : toDecide) {
            int y = target - x;
            if (y != x && poolSet.contains(y)) {
                return new Integer[]{x, y};
            }
        }
        return null;
    }

    // Queue에서 특정 값 하나를 제거 (맨 앞부터 스캔)
    private void removeOneFromQueue(Queue<Integer> q, int val) {
        int sz = q.size();
        for (int i = 0; i < sz; i++) {
            int cur = q.poll();
            if (cur == val) {
                // 해당 원소 하나만 제거하고 나머지는 복원
                // (중복이 없다는 문제 조건 덕분에 충분)
                while (i + 1 < sz) {
                    q.add(q.poll());
                    i++;
                }
                return;
            } else {
                q.add(cur);
            }
        }
    }
}
