function solution(picks, minerals) {
  var answer = 0;
  let total = picks[0] + picks[1] + picks[2];

  let simulated = [];
  for(let i = 0; i<total*5; i+=5){
    let tmp = [0,0,0];

    for(let j = i; j<Math.min(i+5, minerals.length); j++){
      if(minerals[j] === "diamond"){
        tmp[0]++;
        tmp[1]+=5;
        tmp[2]+=25;
      }else if(minerals[j] === "iron"){
        tmp[0]++;
        tmp[1]++;
        tmp[2]+=5;
      }else{
        tmp[0]++;
        tmp[1]++;
        tmp[2]++;
      }
    }
    simulated.push([...tmp]);
  }

  simulated.sort((a,b) => b[2] - a[2]);

  for(let i = 0; i<simulated.length; i++){
    if(picks[0]){
      picks[0]--;
      answer += simulated[i][0];
    }else if(picks[1]){
      picks[1]--;
      answer += simulated[i][1];
    }else if(picks[2]){
      picks[2]--;
      answer += simulated[i][2];
    }else break;

    if((i + 1) * 5 >= minerals.length) break;
  }



  return answer;
}