import java.util.*;

class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int ans = Integer.MAX_VALUE;
    static int N, M;
    static List<Point> chickens = new ArrayList<>();
    static List<Point> houses = new ArrayList<>();

    static int calculateSingleDist(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static int calculateTotalDist(List<Point> currentChickens){
        int total = 0;
        for(Point house: houses){
            int minDist = Integer.MAX_VALUE;
            for(Point chicken : currentChickens){
                minDist = Math.min(minDist, calculateSingleDist(house, chicken));
            }
            total += minDist;
        }

        return total;
    }

    static void dfs(LinkedList<Point> current, int idx){
        if(current.size() == M){
            ans = Math.min(calculateTotalDist(current), ans);
            return;
        }

        for(int i = idx; i<chickens.size(); i++){
            current.addLast(chickens.get(i));
            dfs(current, i+1);
            current.removeLast();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        for(int i = 0; i<N; i++){
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j<N; j++){
                int t = sc.nextInt();
                if(t == 2){
                    chickens.add(new Point(i,j));
                }

                if(t == 1){
                    houses.add(new Point(i,j));
                }
            }
        }

        dfs(new LinkedList<>(){}, 0);

        System.out.println(ans);
    }
}
