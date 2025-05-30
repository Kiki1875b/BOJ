
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


class Node{
  int level, x, num;
  Node left, right;
  
  public Node(int level, int x, int num){
    this.level = level;
    this.x = x;
    this.num = num;
  }
}
class Solution {

  List<Integer> preorderList = new ArrayList<>();
  List<Integer> postorderList = new ArrayList<>();
  public int[][] solution(int[][] nodeinfo) {

    int n = nodeinfo.length;
    List<Node> nodes = new ArrayList<>();
    for(int i = 0; i<n; i++){
      nodes.add(
          new Node(nodeinfo[i][1], nodeinfo[i][0], i + 1)
      );
    }
    
    nodes.sort((a, b) -> {
      if(a.level != b.level) return b.level - a.level;
      else return a.x - b.x;
    });
    
    Node root = nodes.get(0);
    
    for(int i = 1; i<n; i++){
      insert(root, nodes.get(i));
    }
    
    preorder(root); postorder(root);
    
    int[][] ans = new int[2][n];
    
    for(int i = 0; i<n; i++){
      ans[0][i] = preorderList.get(i);
      ans[1][i] = postorderList.get(i);
    }
    
    return ans;
    
  }

  
  void insert(Node parent, Node child){
    if(child.x < parent.x)  {
      if(parent.left == null){
        parent.left = child;
      }else{
        insert(parent.left, child);
      }
    }else {
      if(parent.right == null){
        parent.right = child  ;
      }else {
        insert(parent.right, child);
      }
    }
  }
  
  
  void preorder(Node node){
    if (node == null) return;
    preorderList.add(node.num);
    preorder(node.left);
    preorder(node.right);
  }
  
  void postorder(Node node){
    if(node == null) return;
    postorder(node.left);
    postorder(node.right);
    postorderList.add(node.num);
  }
}
