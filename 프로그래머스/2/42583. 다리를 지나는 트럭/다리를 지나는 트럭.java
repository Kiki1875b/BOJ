
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


class Solution {
  public int solution(int bridge_length, int weight, int[] truck_weights) {
    int answer = 0;
    Queue<Integer> bridge = new LinkedList<>(); 
    int time = 0;
    int idx = 0;
    int weightOnBridge = 0;
    
    while (idx < truck_weights.length || weightOnBridge > 0) {
      time++;
      
      if(bridge.size() == bridge_length) {
        weightOnBridge -= bridge.poll();
      }
      
      if(idx < truck_weights.length){
        int next = truck_weights[idx];
        if(weightOnBridge + next <= weight){
          bridge.add(next);
          idx++;
          weightOnBridge += next;
        }else {
          bridge.add(0);
        }
      } else {
        bridge.add(0);
      }
    }
    
    return time;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    solution.solution(2, 10, new int[]{7,4,5,6});
  }
}
