import java.util.*;

class Document{
    int originalIdx;
    int importance;

    public Document(int originalIdx, int importance){
        this.importance = importance;
        this.originalIdx = originalIdx;

    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int trial = sc.nextInt();

        for(int t = 0; t<trial ;t++){
            int numberOfDocs = sc.nextInt();
            int target = sc.nextInt();

            Queue<Document> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for(int i = 0; i < numberOfDocs; i++){
                int importance = sc.nextInt();
                queue.add(new Document(i, importance));
                pq.add(importance);
            }

            int printOrder = 0;
            while(!queue.isEmpty()){
                Document doc = queue.poll();

                if(doc.importance == pq.peek()){
                    pq.poll();
                    printOrder++;

                    if(doc.originalIdx == target){
                        System.out.println(printOrder);
                        break;
                    }
                }else {

                    queue.add(doc);
                }
            }
        }
    }
}
