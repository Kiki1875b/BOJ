import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


class Node{
  int num;
  int left;
  int right;
  int people;
  int sum;

  public Node(int num, int left, int right, int people) {
    this.num = num;
    this.left = left;
    this.right = right;
    this.people = people;
  }

  public void setSum(int sum){
    this.sum = sum;
  }
}
class Solution {
  List<Node> nodes = new ArrayList<>();
  int cnt = 0;
  public int solution(int k, int[] num, int[][] links) {
    int answer = Integer.MAX_VALUE;

    Set<Integer> s = new HashSet<>();

    int rootNum = -1;
    int sum = 0;

    for(int i = 0; i < num.length; i++){
      s.add(links[i][0]); s.add(links[i][1]);
      sum += num[i];
      Node newNode = new Node(i, links[i][0], links[i][1], num[i]);
      nodes.add(newNode);
    }

    for(int i = 0; i<links.length; i++){
      if(s.contains(i)) continue;
      else{
        rootNum = i;
        break;
      }
    }

//    calculateNodeSum(rootNum);

    int left = 0;
    int right = sum;

    while(left <= right){
      cnt = 1;
      int mid = (left + right) / 2;
      if(isPossible(mid, k, rootNum) != -1){
        answer = Math.min(answer, mid);
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }


    return answer;
  }

  int isPossible(int mid, int k, int root){ // 각 그룹이 mid명 이내가 되도록 나눌 수 있는지?
      if (nodes.get(root).people > mid) return -1;
    int leftSum = nodes.get(root).left == -1 ? 0 : isPossible(mid, k, nodes.get(root).left);
    if(leftSum == -1) return -1;
    int rightSum = nodes.get(root).right == -1 ? 0 : isPossible(mid, k, nodes.get(root).right);
    if(rightSum == -1) return -1;
    int totalSum = leftSum + rightSum + nodes.get(root).people;

    if (totalSum > mid){
      totalSum -= Math.max(leftSum, rightSum);
      cnt++;
    }

    if(totalSum > mid){
      totalSum -= Math.min(leftSum, rightSum);
      cnt++;
    }

    if(cnt > k) return -1;

    return totalSum;
  }


}