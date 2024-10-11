function solution(players, callings) {
  var answer = [];
  let m = new Map();
  for(let i = 0; i<players.length; i++){
    m.set(players[i], i);
  }

  for(let c of callings){
    let cIdx = m.get(c); 
    let prevIdx = cIdx - 1;
    let prevPlayer = players[prevIdx];
    m.set(c, prevIdx);
    m.set(prevPlayer, cIdx);

    players[prevIdx] = c;
    players[cIdx] = prevPlayer;
  }

  return players;
}