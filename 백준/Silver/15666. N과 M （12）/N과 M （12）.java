
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

  static int N, M;
  static LinkedHashSet<String> resultSet = new LinkedHashSet<>();

  static List<Integer> numbers = new ArrayList<>();
  public static void main(String[] args) throws IOException {

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    String[] input1 = bf.readLine().split(" ");
    String[] input2 = bf.readLine().split(" ");

    N = Integer.parseInt(input1[0]);
    M = Integer.parseInt(input1[1]);

    for(int i =0; i<input2.length; i++){
      numbers.add(Integer.parseInt(input2[i]));
    }

    Collections.sort(numbers);

    dfs(new ArrayList<>(), 0, 0);

    for(String s: resultSet){
      System.out.println(s);
    }

  }

  static void dfs(List<Integer> currentNumbers, int start, int depth){
    if(depth == M){
      StringBuilder sb = new StringBuilder();
      for(int num : currentNumbers){
        sb.append(num).append(" ");
      }
      resultSet.add(sb.toString());
      return;
    }

    for(int i = start; i<numbers.size(); i++){
      currentNumbers.add(numbers.get(i));
      dfs(currentNumbers, i, depth + 1);
      currentNumbers.remove(currentNumbers.size() - 1);
    }
  }
}

