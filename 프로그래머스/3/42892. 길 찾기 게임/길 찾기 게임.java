
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


class Node{
  int level;
  int x;
  int nodeNum;
  Node left;
  Node right;

  public Node(int level, int x, int nodeNum) {
    this.level = level;
    this.x = x;
    this.nodeNum = nodeNum;
  }
}
class Solution {

  List<Integer> preorderResult = new ArrayList<>();
  List<Integer> postorderResult = new ArrayList<>();
  public int[][] solution(int[][] nodeinfo) {
//    int[][] answer = {};

    List<Node> nodes = new ArrayList<>();
    for(int i = 0; i<nodeinfo.length; i++){
      nodes.add(new Node(nodeinfo[i][1], nodeinfo[i][0], i + 1));
    }


    nodes.sort((a, b) -> {
      if(b.level != a. level) return b.level - a.level;
      else return a.x - b.x;
    });

    Node root = nodes.get(0);

    for(int i = 1; i<nodeinfo.length; i++){
      insert(root, nodes.get(i));
    }

    preorder(root);
    postorder(root);

    int[][] answer = new int[2][nodeinfo.length];
    for (int i = 0; i < nodeinfo.length; i++) {
      answer[0][i] = preorderResult.get(i);
      answer[1][i] = postorderResult.get(i);
    }
    return answer;
  }

  void insert(Node parent, Node child){
    if(child.x < parent.x) {
      if(parent.left == null){
        parent.left = child;
      }else{
        insert(parent.left, child);
      }
    }else {
      if(parent.right == null){
        parent.right = child;
      }else{
        insert(parent.right, child);
      }
    }
  }

  void preorder(Node node){
    if(node == null) return;
    preorderResult.add(node.nodeNum);
    preorder(node.left);
    preorder(node.right);
  }

  void postorder(Node node){
    if(node == null) return;

    postorder(node.left);
    postorder(node.right);
    postorderResult.add(node.nodeNum);
  }
}
