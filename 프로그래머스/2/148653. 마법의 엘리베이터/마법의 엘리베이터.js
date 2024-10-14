function solution(storey) {
  var answer = 0;
  var toStr = `${storey}`
  var len = toStr.length;

  var arr = [0];
  for(let i = 0; i<len; i++){
    arr.push(parseInt(toStr[i], 10));
  }

  for(let i = len; i > 0; i--){

    if(arr[i] > 5 || (arr[i] >= 5 && arr[i-1] >= 5)){
      arr[i - 1] += 1;
      answer += 10 - arr[i];
    }else{
      answer += arr[i];
    }
  }

  if(arr[0] > 5){
    answer += 10 - arr[0] + 1;
  }else{
    answer += arr[0];
  }

  return answer;
}