function solution(maps) {
  var answer = [];
  const height = maps.length;
  const width = maps[0].length;
  const dx = [-1,1,0,0];
  const dy = [0,0,-1,1];

  const visited = Array(height).fill().map(() => Array(width).fill(false));


  function bfs(start){
    let q = [];
    q.push([start[0], start[1], maps[start[0]][start[1]]]);
    visited[start[0]][start[1]] = true;
    let totalCost = 0;
    while(q.length){
      let [curX, curY, cost] = q.shift();
      totalCost += parseInt(cost,10);

      for(let i = 0; i<4; i++){
        let nx = curX + dx[i];
        let ny = curY + dy[i];
        if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
        if( maps[nx][ny] === "X") continue;
        if(visited[nx][ny]) continue;

        visited[nx][ny] = true;
        q.push([nx,ny, maps[nx][ny]]);
      }    
    }
    return totalCost;
  }

  for(let i = 0; i<height; i++){
    for(let j = 0; j<width; j++){
      if(!visited[i][j] && maps[i][j] !== "X"){
        let tmp = bfs([i,j]);
        answer.push(tmp);
      }
    }
  }
  answer.sort((a, b) => a - b);
  
  return answer.length ? answer : [-1];
}