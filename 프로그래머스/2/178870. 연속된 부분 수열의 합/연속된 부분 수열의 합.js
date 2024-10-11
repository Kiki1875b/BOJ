function solution(sequence, k) {
  var answer = [];
 
  let left = 0;
  let right = 0;
  let sum = sequence[left];

  while(right < sequence.length){

    if(sum === k){
      let ansLength = answer[1] - answer[0];

      if(!answer.length){
        answer = [left, right];
      }
      else if(ansLength > right - left){
        answer = [left, right];
      }
    }

    if(sum <= k){
      right++;
      sum+=sequence[right];
    }else if(left<=right){
      sum-=sequence[left];
      left++;
      
    }

  }
 

  return answer;
}