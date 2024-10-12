




function solution(wallpaper) {
  var answer = [];
  let minX = Infinity;
  let minY = Infinity;
  let maxX = -1;
  let maxY = -1;

  for(let i = 0; i<wallpaper.length; i++){
    for(let j = 0; j<wallpaper[0].length; j++){
      if(wallpaper[i][j] === '#'){
        if(i < minX) minX = i;
        if(j < minY) minY = j;
        if(i > maxX) maxX = i;
        if(j > maxY ) maxY = j;
      }
    }
  }

  answer = [minX, minY, maxX + 1, maxY + 1];

  return answer;
}