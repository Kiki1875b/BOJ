import java.util.*;

/**
 *  N x N 정사각 보드
 *  벽, 자신의 몸과 부딪히면 게임이 끝난다
 *  처음 뱀의 길이는 1
 *  이동한 칸에 사과가 있다면, 꼬리는 움직이지 않고, 해당 칸에 몸이 하나 더 생긴다
 *  이동한 칸에 사과가 없다면, 꼬리가 위치한 칸을 비운다.
 */

class Pair{
    int x;
    int y;
    int dir;
    public Pair(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Pair pair = (Pair) obj;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}


public class Main {
    static int N, M, T;
    static Set<Pair> apples = new HashSet<>();
    static Queue<Pair> currentPos = new LinkedList<>();
    static Set<Pair> currentPosSet = new HashSet<>();
    static Pair lastHead;
    static boolean crash = false;

    static public int[] directions(int dir){
        if(dir == 0) return new int[] {0,1}; // right
        else if(dir == 1) return new int[] {1,0}; // down
        else if(dir == 2) return new int[] {-1,0}; // up
        else return new int[] {0,-1}; // left
    }

    static public int getNextDir(int dir, String s){
        if(dir == 0){
            if(s.equals("D")) return 1;
            else return 2;
        }else if(dir == 1){
            if(s.equals("D")) return 3;
            else return 0;
        }else if(dir == 2){
            if(s.equals("D")) return 0;
            else return 3;
        }else{
            if(s.equals("D")) return 2;
            else return 1;
        }
    }
    
    static boolean check(){
        int[] direct = directions(lastHead.dir);
        int tx = lastHead.x + direct[0];
        int ty = lastHead.y + direct[1];
        if(tx < 1 || ty < 1 || ty >= N + 1 || tx >= N + 1) return false;
        if(currentPosSet.contains(new Pair(tx, ty, lastHead.dir))) return false;
        return true;
    }

    static public int simulate(String turn, int duration){
        Pair current = lastHead;
        boolean isFirst = true;
        int dur = 0;
        int x = current.x;
        int y = current.y;
        int dir = current.dir;
        currentPosSet.add(new Pair(x, y, dir));
        while(duration > 0){
            duration--;

            int[] vec = directions(dir); // 방향

            int nx = x + vec[0]; // 다음 pos
            int ny = y + vec[1];

            if(duration == 0){ // 마지막 이라면 방향 전환하여 저장
                dir = getNextDir(dir, turn);
            }

            if(nx < 1 || ny < 1 || nx >= N + 1 || ny >= N + 1) {
                crash = true;
                return dur + 1;
            }

            if(apples.contains(new Pair(nx,ny, dir))){ // 사과가 있다면
                if(currentPosSet.contains(new Pair(nx,ny,dir))){
                    crash = true;
                    return dur + 1;
                }
                apples.remove(new Pair(nx,ny,dir));
                currentPos.add(new Pair(nx, ny, dir));
                currentPosSet.add(new Pair(nx,ny,dir));

            }else{
                if(currentPosSet.contains(new Pair(nx,ny,dir))){
                    crash = true;
                    return dur + 1;
                }
                currentPos.add(new Pair(nx,ny,dir));
                currentPosSet.add(new Pair(nx,ny,dir));
                Pair p = currentPos.poll();
                currentPosSet.remove(p);
            }
            x = nx;
            y = ny;
            lastHead = new Pair(nx,ny,dir);
            dur++;
        }

        return dur;
    }

    public static void main(String[] args) {
        int totalDuration = 0;
        Scanner sc = new Scanner(System.in);


        N = Integer.parseInt(sc.nextLine());
        M = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < M ; i++){
            String tmp = sc.nextLine();
            String[] p = tmp.split(" ");
            Pair pair = new Pair(Integer.parseInt(p[0]), Integer.parseInt(p[1]), -1);
            apples.add(pair);
        }

        T = Integer.parseInt(sc.nextLine());

        currentPos.add(new Pair(1,1,0));
        lastHead = new Pair(1,1,0);
        for(int i = 0; i<T; i++){
            if(!check()){
                break;
            }
            String input = sc.nextLine();
            String[] tmp = input.split(" ");
            int duration = Integer.parseInt(tmp[0]);
            String turn = tmp[1];
            duration = Math.abs(duration - totalDuration);
            
            int curDur = simulate(turn, duration);

            if(curDur != duration){
                totalDuration += curDur;
                break;
            }
            totalDuration += curDur;

        }

        if(!crash){
            int last = simulate("D", Integer.MAX_VALUE);
            totalDuration += last;
        }

        System.out.println(totalDuration);
    }
}
