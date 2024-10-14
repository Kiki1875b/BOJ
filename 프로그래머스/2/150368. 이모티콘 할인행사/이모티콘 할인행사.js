function solution(users, emoticons) {
  var answer = [];
  
  let rate = [10,20,30,40];
  let combinations = [];
  let list = [];
  function makeCombs(current){
    if(current.length === emoticons.length){
      combinations.push(current);
      return;
    }

    for(let i = 0; i<4; i++){
      let tmp = [...current];
      tmp.push(rate[i]);
      makeCombs(tmp);
    }
  }

  function calculate(comb){

    let totalCost = 0;
    let plus = 0;
    for(let [rate, cost] of users){

      let currentCost = 0;

      for(let i = 0; i<comb.length; i++){

        if(comb[i] >= rate){
          currentCost += emoticons[i]*((100 - comb[i])/100);
        }
        if(currentCost >= cost){
          plus++;
          currentCost = 0;
          break;
        }
      }

      totalCost += currentCost;
    }

    return [plus, totalCost];
  }

  makeCombs([]);


  for(let c of combinations){
    
    list.push(calculate(c));
  }

  list.sort((a,b) => {
    if(a[0] === b[0]) return b[1] - a[1];
    return b[0] - a[0];
  });

  return list[0];
}