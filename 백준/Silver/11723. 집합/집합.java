import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int n = Integer.parseInt(br.readLine());
    int mask = 0;

    for(int i = 0; i<n; i++){
      String[] command = br.readLine().split(" ");
      String op = command[0];

      if(op.equals("add")){
        int x = Integer.parseInt(command[1]);
        mask |= (1 << (x - 1));
      }else if(op.equals("remove")){
        int x = Integer.parseInt(command[1]);
        mask &= ~(1 << (x - 1));
      }else if(op.equals("check")){
        int x = Integer.parseInt(command[1]);
        sb.append((mask & (1<<(x-1))) != 0 ? "1\n" : "0\n");
      } else if (op.equals("toggle")) {
        int x = Integer.parseInt(command[1]);
        mask ^= (1 << (x - 1));
      }else if(op.equals("all")){
        mask = (1 << 20) - 1;
      }else if(op.equals("empty")){
        mask = 0;
      }
    }

    System.out.println(sb);
  }
}

