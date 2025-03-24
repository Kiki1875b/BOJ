
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


class Main {

  static int N, M;

  public static void main(String[] args) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(bf.readLine());

    Deque<Integer> deq = new ArrayDeque<>();

    String[] ops = new String[]{
        "push_front",
        "push_back",
        "pop_front",
        "pop_back",
        "size",
        "empty",
        "front",
        "back"
    };

    StringBuilder sb = new StringBuilder();


    for(int i = 0; i<N; i++){
      String[] input = bf.readLine().split(" ");

      if(input[0].startsWith(ops[0])){
        Integer num = Integer.parseInt(input[1]);
        deq.addFirst(num);
      } else if (input[0].startsWith(ops[1])){
        Integer num = Integer.parseInt(input[1])  ;
        deq.addLast(num);
      } else if (input[0].startsWith(ops[2])){
        if(deq.isEmpty()){
          sb.append("-1\n");
          continue;
        }
        sb.append(deq.pollFirst()).append("\n");
      } else if (input[0].startsWith(ops[3])){
        if(deq.isEmpty()){
          sb.append("-1\n");
          continue;
        }
        sb.append(deq.pollLast()).append("\n");
      } else if (input[0].startsWith(ops[4])) {
        sb.append(deq.size()).append("\n");
      } else if (input[0].startsWith(ops[5])){
        String tmp = deq.isEmpty() ? "1" : "0";
        sb.append(tmp).append("\n");
      } else if (input[0].startsWith(ops[6])) {
        String tmp = deq.isEmpty() ? "-1" : deq.peekFirst().toString();
        sb.append(tmp).append("\n");
      } else {
        String tmp  = deq.isEmpty() ? "-1" : deq.peekLast().toString();
        sb.append(tmp).append("\n");
      }

    }

    System.out.println(sb.toString());

  }

}

