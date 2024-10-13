

function solution(maps) {
  var answer = 0;
  let distToLever = 0;
  let distToExit = 0;

  const dx = [-1,1,0,0];
  const dy = [0,0,-1,1];

  const height = maps.length;
  const width = maps[0].length;

  let start, goal, lever;

  for(let i = 0; i<height; i++){
    for(let j = 0; j<width; j++){
      if(maps[i][j] === "S") start = [i,j];
      else if(maps[i][j] === "L") lever = [i, j];
      else if(maps[i][j] === "E") goal = [i, j];
    }
  }



  function bfs(start, curGoal){
    const visited = Array(height).fill().map(() => Array(width).fill(false));
    let q = [];
    q.push([start[0], start[1], 0]);
    visited[start[0]][start[1]] = true;

    while(q.length){
      let [curX, curY, cost] = q.shift();

      if(curX === curGoal[0] && curY === curGoal[1]){
        return cost;
      }

      for(let i = 0; i<4; i++){
        let nx = curX + dx[i];
        let ny = curY + dy[i];

        if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
        if(maps[nx][ny] === "X") continue;
        if(visited[nx][ny]) continue;

        visited[nx][ny] = true;
        q.push([nx, ny, cost+1]);
      }
    }
    return -1;
  }

  distToLever = bfs([...start], [...lever]);
  distToExit = bfs([...lever], [...goal]);



  return (distToExit === -1 || distToLever === -1) ? -1 : distToExit + distToLever;
}