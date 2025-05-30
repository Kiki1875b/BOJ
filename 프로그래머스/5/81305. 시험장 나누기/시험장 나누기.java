import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 하나의 노드 = 하나의 시험장 시험장은 고유 id 시험장 개수 = n - 0 ~ n-1 번까지의 고유 id 부여 각 시험장의 응시자 수 명시
 * <p>
 * 시험장에 오는 트래픽을 k 개의 그룹으로 나누어 분산 k - 1개의 간선을 끊어서 가장 큰 그룹의 인원을 최소화
 */


class Node {
  int participants;
  int left;
  int right;

  public Node(int participants, int left, int right) {
    this.participants = participants;
    this.left = left;
    this.right = right;
  }
}
class Solution {

  int cnt;
  public int solution(int k, int[] num, int[][] links) {
    int n = links.length;
    
    List<Node> nodes = new ArrayList<>();
    Set<Integer> children = new HashSet<>();
    for(int i = 0; i<n; i++){
        children.add(links[i][0]);
        children.add(links[i][1]);
        nodes.add(
            new Node(num[i], links[i][0], links[i][1])
        );
    }
    
    int root = -1;
    for(int i = 0; i< n; i++){
      if(!children.contains(i)){
        root = i;
        break;
      }
    }
    
    int left = Arrays.stream(num).max().getAsInt();
    int right = Arrays.stream(num)  .sum();
    int ans = right ;
    
    while(left <= right){
      cnt = 1;
      int mid = (left + right)/2;
      int res = dfs(nodes.get(root), nodes, k, mid  );
      
      if(res == -1 || cnt > k){
        left = mid + 1;
      }else{
        ans = Math.min(ans, mid);
        right = mid - 1;
      }
    }
    
    return ans;
  }
  
  int dfs(Node node, List<Node> nodes, int k, int limit){
    if(cnt > k ) return -1;
    
    int leftSum = node.left == -1 ? 0 : dfs(nodes.get(node.left), nodes, k, limit);
    if(leftSum == -1) return -1;
    
    int rightSum = node.right == -1 ? 0 : dfs(nodes.get(node.right), nodes, k , limit);
    if(rightSum == -1) return -1;
    
    int sum = leftSum + rightSum;
    
    if(sum + node.participants > limit){
      cnt++;
      sum -= Math.max(leftSum, rightSum);
    }
    
    if(sum + node.participants > limit){
      cnt++;
      sum = 0;
    }
    
    return sum + node.participants;
  }


}
