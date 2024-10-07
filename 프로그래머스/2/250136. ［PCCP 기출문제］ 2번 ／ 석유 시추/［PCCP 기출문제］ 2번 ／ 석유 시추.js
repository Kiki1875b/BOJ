
// class UnionFind{
//   constructor(x){
//     this.parents = Array(x).fill(0).map((_,index) => index); 
//     this.size = Array(x).fill(1);
//   }

//   find(x){
//     if(this.parents[x] === x){
//       return x;
//     }
//     return this.parents[x] = find(this.parents[x]);
//   }

//   union(x,y){
//     const rootX = this.find(x);
//     const rootY = this.find(y);

//     if(rootX !== rootY){
//       if(this.size[rootX] > this.size[rootY]){
//         this.parents[rootY] = rootX;
//         this.size[rootX] += this.size[rootY];
//       }else{
//         this.parents[rootX] = rootY;
//         this.size[rootY] += this.size[rootX];
//       }
//     }
//   }

//   getSize(x){
//     return this.size[this.find(x)];
//   }
// }

// function solution(land) {
//   var answer = 0;
//   const height = land.length;
//   const width = land[0].length;

//   const dx = [1,-1,0,0];
//   const dy = [0,0,1,-1];

//   var uf = new UnionFind(height * width);

//   for(let i = 0; i<height; i++){
//     for(let j = 0; j<width; j++){
//       if(land[i][j] === 1){
//         for(let k = 0; k<4; k++){
//           let nx = i + dx[k];
//           let ny = j + dy[k];
//           if(nx >= 0 && ny >= 0 && nx < height && ny < width && land[nx][ny] === 1){
//             uf.union(i * width + j , nx * width + ny);
//           }
//         }
//       }
//     }
//   }

//   var oil = Array(width).fill(0);
//   for(let j = 0; j<width; j++){
//     var visited = Array(width * height).fill(0);
//     for(let i = 0; i<height; i++){
//       if(land[i][j] === 1){
//         var root = uf.find(i*width + j);
//         if(!visited[root]){
//           oil[j] += uf.getSize(root);
//           visited[root] = true;
//         }
//       }
//     }
//   }
  
//   return Math.max(...oil);
// }






function solution(land) {
  
  const width = land[0].length;
  const height = land.length;
  const visited = Array.from({ length: height }, () => Array(width).fill(false));
  const dx = [1,-1,0,0];
  const dy = [0,0,1,-1];

  var rowVal = Array(width).fill(0);

  function bfs (x, y){
    const q = [[x,y]];
    const visitedRow = new Set();
    visited[x][y] = true;
    let cnt = 0;

    while(q.length > 0){
      const [cx, cy] = q.shift();
      cnt++;
      visitedRow.add(cy);

      for(let i = 0; i<4; i++){
        const nx = cx + dx[i];
        const ny = cy + dy[i];
        if(nx >= 0 && ny >= 0 && nx < height && ny < width && land[nx][ny] === 1 && !visited[nx][ny]){
          q.push([nx, ny]);
          visited[nx][ny] = true;
        }
      }
    }
    for(const s of visitedRow){
      rowVal[s] += cnt;
    }
  }

  for(let j = 0 ; j<width; j++){
    for(let i = 0; i<height; i++){
      if(land[i][j] === 1 && !visited[i][j]){
        bfs(i,j);
      }
    }
  }




  return Math.max(...rowVal);
}