import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        
        Set<Integer> hand = new HashSet<>();
        for(int i = 0; i<n/3; i++){
            hand.add(cards[i]);
        }
        
        Queue<Integer> deck = new LinkedList<>();
        for(int i = n/3; i < n; i++) deck.add(cards[i]);
        int round = 0;
        Queue<Integer> toDecide = new LinkedList<>();
        int target = n + 1;
        while(deck.size() >= 2){
            int c1 = deck.poll();
            int c2 = deck.poll();
            toDecide.add(c1);
            toDecide.add(c2);
            
            boolean progressed = false;
            
            int[] handPair = findHandPair(hand, target);
            if(handPair != null){
                hand.remove(handPair[0]);
                hand.remove(handPair[1]);
                round++;
                progressed = true;
            } else {
                
                if(coin >= 1 && !progressed){
                    int[] handPoolPair = findHandPoolPair(hand, toDecide, target);
                    if(handPoolPair != null) {
                        removeOneFromQueue(toDecide, handPoolPair[1]);
                        hand.remove(handPoolPair[0]);
                        coin--;
                        round++;
                        progressed = true;
                    }
                }
                
                if(coin >= 2 && !progressed){
                    int[] poolPair = findPoolPair(toDecide, target);
                    if(poolPair != null){
                        removeOneFromQueue(toDecide, poolPair[0]);
                        removeOneFromQueue(toDecide, poolPair[1]);
                        round++;
                        coin -= 2;
                        progressed = true;
                    }
                }
            }
            
            if(!progressed) break;
        }
        
        return round + 1;
    }
    
    int[] findPoolPair(Queue<Integer> q, int target){
        if(q.size() < 2) return null;
        
        Set<Integer> copy = new HashSet<>(q);
        for(int x: copy){
            int need = target - x;
            
            if(need != x && copy.contains(need)){
                return new int[]{x, need};
            }
        }
        
        return null;
    }
    
    int[] findHandPoolPair(Set<Integer> hand, Queue<Integer> q, int target){
        if(hand.isEmpty()|| q.isEmpty()) return null;
        Set<Integer> copy = new HashSet<>(q);
        
        for(int x : hand){
            int need = target - x;
            if(copy.contains(need)){
                return new int[]{x, need};
            }
        }
        return null;
    }
    
    int[] findHandPair(Set<Integer> hand, int target){
        if(hand.size() < 2) return null;
        
        for(int x : hand){
            int y = target - x;
            if(hand.contains(y)){
                return new int[]{x,y};
            }
        }
        
        return null;
    }
    
    void removeOneFromQueue(Queue<Integer> q, int num){
        int s = q.size();
        for(int i = 0; i<s; i++){
            int n = q.poll();
            if(n == num) continue;
            q.add(n);
        }
    }
}