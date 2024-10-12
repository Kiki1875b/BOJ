function solution(board) {
  var answer = 0;

  const height = board.length;
  const width = board[0].length;
  const dx = [-1,1,0,0];
  const dy = [0,0,-1,1];
  const visited = Array(height).fill().map(()=> Array(width).fill(false));
  let start = [];
  let goal = [];

  for(let i = 0; i<height; i++){
    for(let j = 0; j<width; j++){
      if(board[i][j] === "R") start = [i,j];
      else if (board[i][j] === "G") goal = [i,j];
    }
  }

  function bfs(){
    let q = [];
    q.push([...start, 0]);
    visited[start[0]][start[1]] = true;

    while(q.length){
      let [curX, curY, cost] = q.shift();

      if(curX === goal[0] && curY === goal[1]){
        return cost;
      }

      for(let i = 0; i<4; i++){
        let nx = curX + dx[i];
        let ny = curY + dy[i];
        let nCost = cost + 1;

        if(!isValid(nx,ny)) continue;

        while(true){
          nx += dx[i];
          ny += dy[i];

          if(!isValid(nx, ny)){
            nx -= dx[i];
            ny -= dy[i];
            break;
          }
          if(board[nx][ny] === "D"){
            nx -= dx[i];
            ny -= dy[i];
            break;
          }
        }

        if(visited[nx][ny]) continue;

        visited[nx][ny] = true;
        q.push([nx, ny, nCost]);

      }
    }
    return -1;
  }

  function isValid(x, y){
    if(x < 0 || y < 0 || x >= height || y >= width ) return false;
    if(board[x][y] === "D") return false;

    return true;
  }




  return bfs();
}