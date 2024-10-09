function solution(maze) {
  const height = maze.length;
  const width = maze[0].length;
  const dx = [-1,1, 0, 0];
  const dy = [0, 0, -1,1];

  var answer = Infinity;
  var redStart, blueStart, redGoal, blueGoal;

  // 시작점, 도착점 설정
  for (let i = 0; i < height; i++) {
    for (let j = 0; j < width; j++) {
      if (maze[i][j] === 1) redStart = [i, j];
      else if (maze[i][j] === 2) blueStart = [i, j];
      else if (maze[i][j] === 3) redGoal = [i, j];
      else if (maze[i][j] === 4) blueGoal = [i, j];
    }
  }

  function dfs(cnt, redPos, bluePos, redVisit, blueVisit){


    if(cnt > answer){
      return Infinity;
    }

    if(redPos[0] === redGoal[0] &&
       redPos[1] === redGoal[1] &&
       bluePos[0] === blueGoal[0] &&
       bluePos[1] === blueGoal[1]
    ){
      answer = Math.min(cnt,answer);
      return answer;
    }

    let minMoves = Infinity;


    for(let i = 0; i<4; i++){
      let nextRedX = redPos[0] + dx[i];
      let nextRedY = redPos[1] + dy[i];

      let newVisitedRed = redVisit.map(row => [...row]);

      if(redPos[0] === redGoal[0] && redPos[1] === redGoal[1]){
        nextRedX = redGoal[0];
        nextRedY = redGoal[1];
      }

      if(!(nextRedX === redGoal[0] && nextRedY === redGoal[1]) && !isValid([nextRedX, nextRedY], newVisitedRed)){
        continue;
      }

      for(let j = 0; j<4; j++){

        let nextBlueX = bluePos[0] + dx[j];
        let nextBlueY = bluePos[1] + dy[j];

        let newvisitedBlue = blueVisit.map((row) => [...row]);

        if(bluePos[0] === blueGoal[0] && bluePos[1] === blueGoal[1]){
          nextBlueX = blueGoal[0];
          nextBlueY = blueGoal[1];
        }

        if(!(nextBlueX === blueGoal[0] && nextBlueY === blueGoal[1]) && !isValid([nextBlueX, nextBlueY], newvisitedBlue)){
          continue;
        }

        if(nextRedX === nextBlueX && nextBlueY === nextRedY){
          continue;
        }

        if(nextRedX === bluePos[0] && nextRedY === bluePos[1] && nextBlueX === redPos[0] && nextBlueY === redPos[1]){
          continue;
        }

        

        newVisitedRed[nextRedX][nextRedY] = true;
        newvisitedBlue[nextBlueX][nextBlueY] = true;
        const res = dfs(cnt + 1, [nextRedX, nextRedY], [nextBlueX, nextBlueY], newVisitedRed, newvisitedBlue);
        minMoves = Math.min(res, minMoves);
      }
    }
    return minMoves;
  }

  function isValid(pos, visit){

    if(pos[0] < 0 || pos[1] < 0 || pos[0] >= height || pos[1] >= width){
      return false;
    }

    if(visit[pos[0]][pos[1]] === true){
      return false;
    }

    if(maze[pos[0]][pos[1]] === 5){
      return false;
    }

    return true;
  }
  

  const RedSet = Array(height).fill().map(()=> Array(width).fill(false))
  const BlueSet = Array(height).fill().map(()=> Array(width).fill(false))
  RedSet[redStart[0]][redStart[1]] = true;
  BlueSet[blueStart[0]][blueStart[1]] = true;
  answer = dfs(0, [...redStart], [...blueStart], RedSet, BlueSet);

  return answer === Infinity ? 0 : answer;
}
