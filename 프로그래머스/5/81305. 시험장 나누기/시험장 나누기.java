

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


class Node{
  int participant, left, right;

  public Node(int participant, int left, int right) {
    this.participant = participant;
    this.left = left;
    this.right = right;
  }
}
class Solution {

  int cnt = 0;
  public int solution(int k, int[] num, int[][] links) {
    int n = links.length;
    List<Node> nodes = new ArrayList<>();
    Set<Integer> children = new HashSet<>();
    for(int i = 0; i<n; i++){
      children.add(links[i][0]);
      children.add(links[i][1]);
      nodes.add(new Node(num[i], links[i][0], links[i][1]));
    }


    int root = 0;
    for(int i = 0 ; i<n ; i++){
      if(!children.contains(i)){
        root = i;
        break;
      }
    }

    int left = Arrays.stream(num).max().getAsInt();
    int right = Arrays.stream(num).sum();
    int ans = right;

    while(left <= right){

      int mid = (left + right) / 2;

      cnt = 1;
      int res = dfs(k, nodes.get(root), nodes, mid);

      if(res == -1 || cnt > k){
        left = mid + 1;
      } else {

        ans = Math.min(mid, ans)  ;
        right = mid - 1;
      }
    }

    return ans;
  }

  int dfs(int k, Node current, List<Node> nodes, int limit){
    if(cnt > k) return -1;

    int leftSum = current.left == -1 ? 0 : dfs(k, nodes.get(current.left), nodes, limit);
    if(leftSum == -1) return -1;
    int rightSum = current.right == -1 ? 0 : dfs(k, nodes.get(current.right), nodes, limit);
    if(rightSum == -1) return -1;
    int sum = leftSum + rightSum;


    if(sum + current.participant > limit){
      cnt++;
      sum -= Math.max(leftSum, rightSum);
    }

    if(sum + current.participant > limit){
      cnt++;
      sum = 0;
    }

    return sum + current.participant;
  }



}
