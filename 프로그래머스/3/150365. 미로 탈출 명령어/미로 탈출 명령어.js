function solution(n, m, x, y, r, c, k) {
  var answer = '';
  let dist = Math.abs(x - r) + Math.abs(y - c);
  if((k - dist) % 2 === 1) return 'impossible';
  const dir = ["d", "l", "r", "u"];
  const direction = [[1,0],[0,-1],[0,1],[-1,0]];

  while(k > 0){
    let moved = false;
    for(let i = 0; i<4; i++){
      let nx = x + direction[i][0];
      let ny = y + direction[i][1];

      let nextDist = Math.abs(nx - r) + Math.abs(ny - c);
      if(k - 1 >= nextDist && nx > 0 && ny > 0 && nx <= n && ny <=m){
        answer += dir[i];
        k -= 1;
        x = nx;
        y = ny;
        moved = true;
        break;
      }
    }
    if(!moved) return 'impossible';
  }
  return answer;
}
