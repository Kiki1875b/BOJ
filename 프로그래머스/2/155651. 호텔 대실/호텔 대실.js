function solution(book_time) {
  var answer = 0;
  var formattedTime = [];

  function format(time){
    let [startHour, startMin] = time[0].split(":");
    let [endHour, endMin] = time[1].split(":");

    formattedTime.push([parseInt(startHour,10)*60 + parseInt(startMin,10) , parseInt(endHour, 10) * 60 + parseInt(endMin, 10) + 10]);
  }

  for(let b of book_time){
    format(b);
  }

  //formattedTime.sort((a,b) => a[0] - b[0]);
  
  let map = new Map();
  for(let [start, end] of formattedTime){
    map.set(start, (map.get(start) || 0) + 1);
    map.set(end, (map.get(end) || 0) - 1);
  }

  let sortedMap = new Map([...map.entries()].sort((a,b) => a[0] - b[0]));
  

  let tmp = 0;

  for(let [key , value] of sortedMap){
    //console.log(key, value, tmp);
    tmp += value;
    answer = Math.max(tmp, answer);
  }
  
  //console.log(answer)
  return answer;
}
