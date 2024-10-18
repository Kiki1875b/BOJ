function solution(data, col, row_begin, row_end) {
  var answer = 0;
  

  function toBinary(num){
    if(num === 0) return "0";

    let ret = ""

    while(num > 0){
      ret = num % 2 + ret;
      num = Math.floor(num/2);
    }

    return ret;
  }

  data.sort((a,b) => {
    if(a[col - 1] !== b[col - 1]) return a[col - 1] - b[col - 1];
    return b[0] - a[0];
  });

  var parsedData = [];

  for(let i = row_begin-1; i<row_end; i++){
    let current = [...data[i]];
    let sum = 0;
    parsedData.push(current.map((_, index) => current[index] % (i+1))
              .reduce((acc,value) => acc + value, 0));
  }

  var parsedBinary = [];


  var maxLength = 0;
  for(let i = 0; i<parsedData.length; i++){
    let tmp = toBinary(parsedData[i]);
    maxLength = Math.max(maxLength, tmp.length);
    parsedBinary.push(tmp);
  }

  for(let i = 0; i<parsedBinary.length; i++){
    let tmp = "";
    let length = parsedBinary[i].length;
    if(length < maxLength){
      for(let i = 0; i<maxLength - length; i++){
        tmp = "0" + tmp;
      }
    }
    parsedBinary[i] = tmp + parsedBinary[i];
  }

  let res = parsedBinary[0];
  for(let i = 1; i<parsedBinary.length; i++){
    let ret = ""
    for(let j = 0; j<parsedBinary[i].length; j++){
      if(res[j] !== parsedBinary[i][j]){
        ret = ret + "1";
      }else{
        ret = ret + "0";
      }
    }
    res = ret;
  }

  answer = parseInt(res,2);
  return answer;
}