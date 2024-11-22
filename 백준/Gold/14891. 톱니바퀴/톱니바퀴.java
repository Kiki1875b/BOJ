import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




class Wheel{
    int[] state;

    public Wheel(int[] state){
        this.state = state;
    }

    public void spinClockwise(){
        int[] tmp = Arrays.copyOf(state, state.length);

        for(int i = 1; i<state.length; i++){
            state[i] = tmp[i - 1];
        }
        state[0] = tmp[7];
    }

    public void spinAntiClockwise(){

        int[] tmp = Arrays.copyOf(state, state.length);
        for(int i = state.length - 2; i >= 0; i--){
            state[i] = tmp[i + 1];
        }
        state[7] = tmp[0];
    }

    public int getLeft(){
        return state[6];
    }

    public int getRight(){
        return state[2];
    }

    public int getTop(){
        return state[0];
    }
}
public class Main {

    static List<Wheel> wheels = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        for(int i = 0; i<4; i++){
            String[] input = bf.readLine().split("");
            int[] tmp = new int[8];
            for(int j = 0; j<input.length; j++){
                tmp[j] = Integer.parseInt(input[j]);
            }
            Wheel wheel = new Wheel(tmp);
            wheels.add(wheel);
        }


        int trial = Integer.parseInt(bf.readLine());
        for(int i = 0; i<trial; i++){
            String[] input = bf.readLine().split(" ");
            spin(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]));
        }



        int point = 1;
        for(Wheel wheel: wheels){
            if(wheel.getTop() == 1) ans+=point;
            point *=2;
        }

        System.out.println(ans);

    }

    public static void spin(int wheelIdx, int dir){

        int[] spinWhichWay = new int[4];
        spinWhichWay[wheelIdx] = dir;

        int prevDir = dir;

        for(int i = wheelIdx; i < wheels.size() - 1; i++){
            if(wheels.get(i).getRight() != wheels.get(i + 1).getLeft()){
                spinWhichWay[i + 1] = prevDir * -1;
                prevDir = prevDir * -1;
            }else{
                spinWhichWay[i + 1] = 0;
                prevDir = 0;

            }
        }

        prevDir = dir;

        for(int i = wheelIdx; i > 0; i--){
            if(wheels.get(i).getLeft() != wheels.get(i-1).getRight()){
                spinWhichWay[i - 1] = prevDir * -1;
                prevDir = prevDir * -1;
            }else{
                spinWhichWay[i - 1] = 0;
                prevDir = 0;

            }
        }


        for(int i = 0; i<wheels.size(); i++){
            if(spinWhichWay[i] == -1){
                wheels.get(i).spinAntiClockwise();
            }else if(spinWhichWay[i] == 1){
                wheels.get(i).spinClockwise();
            }else{
                continue;
            }
        }
    }

}
