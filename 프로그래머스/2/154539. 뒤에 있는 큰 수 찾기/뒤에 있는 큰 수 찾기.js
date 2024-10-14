function solution(numbers) {
  var answer = [];

  var stack = [];

  for(let i = 0; i<numbers.length; i++){
    answer.push(-1);
  }
  for(let i = numbers.length - 1; i >= 0; i--){
    while(stack.length && stack[0] <= numbers[i]) stack.shift();

    if(stack.length){
      answer[i] = stack[0];
    }

    stack.unshift(numbers[i]);
  }

  return answer;
}