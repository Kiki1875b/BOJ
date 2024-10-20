import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        if(k >= enemy.length) return enemy.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i = 0 ; i<enemy.length; i++){
            int e = enemy[i];
            boolean possible = true;
            queue.add(-e);
            n -= e;

            if(n < 0){

                while(!queue.isEmpty() && n < 0){
                    int num = -queue.poll();
                    if(k > 0){
                        k--;
                        n += num;

                    }else{
                        possible = false;
                        break;
                    }
                }
            }

            if(!possible) break;
            answer = i;

        }

        return answer + 1;
    }
}