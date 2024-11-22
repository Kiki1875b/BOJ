import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Wheel {
    int[] state;

    public Wheel(int[] state) {
        this.state = state;
    }

    public void spin(int direction) {
        if (direction == 1) { // Clockwise
            int last = state[7];
            System.arraycopy(state, 0, state, 1, 7);
            state[0] = last;
        } else if (direction == -1) { // Anti-clockwise
            int first = state[0];
            System.arraycopy(state, 1, state, 0, 7);
            state[7] = first;
        }
    }

    public int getLeft() {
        return state[6];
    }

    public int getRight() {
        return state[2];
    }

    public int getTop() {
        return state[0];
    }
}

public class Main {

    static List<Wheel> wheels = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 톱니바퀴 상태 입력
        for (int i = 0; i < 4; i++) {
            String[] input = bf.readLine().split("");
            int[] tmp = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            wheels.add(new Wheel(tmp));
        }

        // 회전 정보 입력 및 처리
        int trial = Integer.parseInt(bf.readLine());
        for (int i = 0; i < trial; i++) {
            String[] input = bf.readLine().split(" ");
            int wheelIdx = Integer.parseInt(input[0]) - 1;
            int dir = Integer.parseInt(input[1]);
            spin(wheelIdx, dir);
        }

        // 점수 계산 및 출력
        int ans = 0, point = 1;
        for (Wheel wheel : wheels) {
            if (wheel.getTop() == 1) ans += point;
            point *= 2;
        }
        System.out.println(ans);
    }

    public static void spin(int wheelIdx, int dir) {
        int[] spinDirections = new int[4];
        spinDirections[wheelIdx] = dir;

        // 오른쪽으로 전달
        int prevDir = dir;
        for (int i = wheelIdx; i < 3; i++) {
            if (wheels.get(i).getRight() != wheels.get(i + 1).getLeft()) {
                prevDir = -prevDir;
                spinDirections[i + 1] = prevDir;
            } else {
                break;
            }
        }

        // 왼쪽으로 전달
        prevDir = dir;
        for (int i = wheelIdx; i > 0; i--) {
            if (wheels.get(i).getLeft() != wheels.get(i - 1).getRight()) {
                prevDir = -prevDir;
                spinDirections[i - 1] = prevDir;
            } else {
                break;
            }
        }

        // 실제 회전 수행
        for (int i = 0; i < 4; i++) {
            wheels.get(i).spin(spinDirections[i]);
        }
    }
}
