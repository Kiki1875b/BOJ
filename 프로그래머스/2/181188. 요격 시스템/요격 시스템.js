function solution(targets){
  let answer = 0;
  
  targets.sort((a,b) =>  a[1] - b[1]);

  let last = -1;
  for(let [start, end] of targets){
    if(start < last && end >= last){
        continue;
    }
    else{
      answer++;
      last = end;
    }
  }
  return answer;
}