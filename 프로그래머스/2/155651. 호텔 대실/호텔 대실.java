import java.util.*;

/**

 */

class Time{
  int start, end;
  public Time(int start, int end){
    this.start = start;
    this.end = end;
  }

}

class Pair{
  int time;
  int value;

  public Pair(int time, int value) {
    this.time = time;
    this.value = value;
  }

  @Override
  public String toString() {
    return "Pair{" +
        "time=" + time +
        ", value=" + value +
        '}';
  }
}
class Solution {
  public int solution(String[][] book_time) {
    int answer = 0;
    List<Time> times = new ArrayList<>();

    for (String[] singleTime : book_time){
      String[] startTime = singleTime[0].split(":");
      String[] endTime = singleTime[1].split(":");
      int startTimeInt = (Integer.parseInt(startTime[0]) * 60) + Integer.parseInt(startTime[1]);
      int endTimeInt = (Integer.parseInt(endTime[0]) * 60) + Integer.parseInt(endTime[1]) + 10;

      times.add(new Time(startTimeInt, endTimeInt));
    }

    int cnt = 0;

    List<Pair> list = new ArrayList<>();

    for(Time t : times){
      list.add(new Pair(t.start, 1));
      list.add(new Pair(t.end, -1));
    }

    Collections.sort(list, (a, b) ->{
      if(a.time == b.time) return a.value - b.value;
      return a.time - b.time;
    });

    //System.out.println(list);
    for(Pair p : list){
      cnt += p.value;
      answer = Math.max(answer, cnt);
    }

    return answer;
  }
}
