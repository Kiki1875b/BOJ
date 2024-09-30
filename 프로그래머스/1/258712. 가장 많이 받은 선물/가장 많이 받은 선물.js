
/*
  A 와 B 가 선물을 주고 받았다면 더 많이 준 사람이 받는다
  두사람이 주고 받은 기록이 없다면 선물지수가 더 큰 사람이 받는다
  선물지수는 이번달 준 선물 개수 - 받은 선물

  선물 지수도 같다면 아무일도 이러나지 않는다
*/


function solution(friends, gifts) {
  var answer = 0;
  var past = {};
  var giftPoints = {};
  var toRecieve = {};
  

  for(let f of friends){ // 선물 주고받기 초기화
    if(!past[f]) past[f] = {};

    for(let f2 of friends){
      if(f === f2) continue;
      past[f][f2] = 0;
    }
    if(!giftPoints[f]) giftPoints[f] = 0;
    if(!toRecieve[f]) toRecieve[f] = 0;
  }

  for(let g of gifts){
    let [from, to] = g.split(' ');
    if(!past[from][to]) past[from][to] = 0;
    past[from][to] += 1;

    giftPoints[from]++;
    giftPoints[to]--;
  }


  for(let i = 0; i<friends.length - 1; i++){
    for(let j = i + 1; j<friends.length; j++){
      // 준 선물 수가 같다면
      if(past[friends[i]][friends[j]] === past[friends[j]][friends[i]]){
        if(giftPoints[friends[i]] !== giftPoints[friends[j]]){
          let larger = giftPoints[friends[i]] > giftPoints[friends[j]] ? friends[i] : friends[j];
          toRecieve[larger]++;
        }
      }
      else if(past[friends[i]][friends[j]] > past[friends[j]][friends[i]]){
        toRecieve[friends[i]]++
      }else{
        toRecieve[friends[j]]++;
      }
    }
  }


  for(let [key, val] of Object.entries(toRecieve)){
    answer = Math.max(val, answer);
  }
  return answer;
}