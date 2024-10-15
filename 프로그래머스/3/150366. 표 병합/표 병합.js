
class UnionFind{

  constructor(size){
    this.value = [];
    this.parents = [];

    let counter = 1;
    for(let i = 0; i<size; i++){
      this.value[i] = [];
      this.parents[i] = [];
      for(let j = 0; j<size; j++){
        this.value[i][j] = "";
        this.parents[i][j] = [i,j];
        counter++;
      }
    }
  }

  find(x, y){
 

    const [parentX, parentY] = this.parents[x][y];
    if(parentX === x && parentY === y) return [x, y];
    return this.parents[x][y] = this.find(parentX, parentY);
  }

  union(x1, y1, x2, y2){
    const [rootX1, rootY1] = this.find(x1, y1);
    const [rootX2, rootY2] = this.find(x2, y2);

    if(rootX1 === rootX2 && rootY1 === rootY2) return;

    if(this.value[rootX1][rootY1] !== ""){
      //this.value[rootX2][rootY2] = this.value[rootX1][rootY1];
      this.parents[rootX2][rootY2] = [rootX1, rootY1];
    }else{
      //this.value[rootX1][rootY1] = this.value[rootX2][rootY2]
      this.parents[rootX1][rootY1] = [rootX2, rootY2];
    }
  }

  unmerge(x, y){
    const [rootX, rootY] = this.find(x,y);
    const originalValue = this.value[rootX][rootY];
    const cellsToUnmerge = [];
    for(let i = 0; i < 51; i++){
      for(let j = 0; j < 51; j++){
        let [curX, curY] = this.find(i,j);
        if(curX === rootX && curY === rootY){
          cellsToUnmerge.push([i,j]);
        }
      }
    }

    cellsToUnmerge.forEach(([i,j]) => {
      this.parents[i][j] = [i,j];
      this.value[i][j] = "";
    });

    this.value[x][y] = originalValue;

  }


  updateCell(x, y, newValue){
    const [rootX, rootY] = this.find(x,y);
    this.value[rootX][rootY] = newValue;
  }


  updateValue(oldVal, newVal){
    for(let i = 0; i<51; i++){
      for(let j = 0; j<51; j++){
        if(this.value[i][j] === oldVal){
          this.value[i][j] = newVal;
        }
      }
    }
  }

  printCell(x, y){
    const[rootX, rootY] = this.find(x, y);
    return this.value[rootX][rootY] === "" ? "EMPTY" : this.value[rootX][rootY];
  }
}

function solution(commands) {
  var answer = [];
  var uf = new UnionFind(51);
  
  for(let command of commands){
    let c = command.split(" ");

    if(c[0] === 'UPDATE'){
      if(c.length === 4){

        uf.updateCell(parseInt(c[1],10), parseInt(c[2], 10), c[3]);
      }else{
        uf.updateValue(c[1], c[2]);
      }
    }else if(c[0] === 'MERGE'){
      uf.union(
        parseInt(c[1],10),
        parseInt(c[2],10),
        parseInt(c[3],10),
        parseInt(c[4],10)
      );
    }else if(c[0] === 'UNMERGE'){
      uf.unmerge(parseInt(c[1],10), parseInt(c[2], 10));
    }else{
      answer.push(uf.printCell(parseInt(c[1],10), parseInt(c[2], 10)));
    }
  }

  return answer;
}