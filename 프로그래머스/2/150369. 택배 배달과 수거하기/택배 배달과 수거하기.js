function solution(cap, n, deliveries, pickups) {
  var answer = 0;


  while(deliveries.length || pickups.length){

    while(deliveries.length && deliveries[deliveries.length - 1] === 0) deliveries.pop();
    while(pickups.length && pickups[pickups.length - 1] === 0) pickups.pop();

    let dist = Math.max(deliveries.length, pickups.length);

    let boxes = 0;

    while(deliveries.length && boxes <= cap){
      let curBox = deliveries.pop();
      if(boxes + curBox <= cap){
        boxes += curBox;
      }else{
        deliveries.push(curBox - (cap - boxes));
        break;
      }
    }

    boxes = 0;
    while(pickups.length && boxes <= cap){
      let curBox = pickups.pop();
      if(boxes + curBox <= cap){
        boxes += curBox;
      }else{
        pickups.push(curBox - (cap - boxes));
        break;
      }
    }

    answer += dist*2;
  }


  return answer;
}
