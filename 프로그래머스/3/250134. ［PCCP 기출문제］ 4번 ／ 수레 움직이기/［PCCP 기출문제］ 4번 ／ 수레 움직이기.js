

function solution(maze) {
  var answer = Infinity;

  const dx = [1,-1,0,0];
  const dy = [0,0,1,-1];


  const height = maze.length;
  const width = maze[0].length;
  var redGoal = [];
  var redStart = []
  var blueGoal = [];
  var blueStart = [];




  for(let i = 0; i<height; i++){
      for(let j = 0; j<width; j++){
          if(maze[i][j] === 1) redStart = [i,j];
          if(maze[i][j] === 2) blueStart = [i,j];
          if(maze[i][j] === 3) redGoal = [i,j];
          if(maze[i][j] === 4) blueGoal = [i,j];
      }
  }

  // redCurrent = [...redStart];
  // blueCurrent = [...blueStart];
  
  function dfs(cnt, redPos, bluePos , redvisit, bluevisit){

      
      if(cnt > answer) return Infinity;
      
      if(redPos[0] === redGoal[0] && redPos[1] === redGoal[1] && bluePos[0] === blueGoal[0] && bluePos[1] === blueGoal[1]){
          answer = Math.min(cnt, answer);
          return answer;
      } 

      let minMoves = Infinity;

      for(let i = 0; i<4; i++){
          
          for(let j = 0; j<4; j++){
              let nextRedPos = [...redPos];
              let nextBluePos = [...bluePos];

              let newVisitedR = redvisit.map(row => [...row]);
              let newVisitedB = bluevisit.map(row=>[...row]);

              if(!(nextRedPos[0] === redGoal[0] && nextRedPos[1] === redGoal[1])){
                nextRedPos = [nextRedPos[0] + dx[i], nextRedPos[1] + dy[i]];
                if(!isValidMove(nextRedPos, newVisitedR)) continue;
                newVisitedR[nextRedPos[0]][nextRedPos[1]] = true;
              }
              

              if(!(nextBluePos[0] === blueGoal[0] && nextBluePos[1] === blueGoal[1])){
                  nextBluePos = [bluePos[0] + dx[j], bluePos[1] + dy[j]];
                  if(!isValidMove(nextBluePos, newVisitedB)) continue;
                  newVisitedB[nextBluePos[0]][nextBluePos[1]] = true;
              }

              if (nextRedPos[0] === nextBluePos[0] && nextRedPos[1] === nextBluePos[1]) continue;
              if (nextRedPos[0] === bluePos[0] && nextRedPos[1] === bluePos[1] &&
                  nextBluePos[0] === redPos[0] && nextBluePos[1] === redPos[1]) continue;
            

              const res = dfs(cnt + 1, nextRedPos, nextBluePos, newVisitedR, newVisitedB);
              minMoves = Math.min(res, minMoves);
          }
      }
      return minMoves;
  }

  function isValidMove(pos, visited) {
    const [x, y] = pos;
    return x >= 0 && x < height && y >= 0 && y < width &&
           maze[x][y] !== 5 && !visited[x][y];
}

  const initialRedVisited = Array(height).fill().map(() => Array(width).fill(false));
  const initialBlueVisited = Array(height).fill().map(() => Array(width).fill(false));
  initialRedVisited[redStart[0]][redStart[1]] = true;
  initialBlueVisited[blueStart[0]][blueStart[1]] = true;
  dfs(0, [...redStart], [...blueStart], initialRedVisited, initialBlueVisited);
 

  return answer === Infinity ? 0 : answer;
}

