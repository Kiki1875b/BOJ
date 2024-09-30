

/*

[중간 정점, 도넛, 막대, 8자] 를 반환해야 한다

도넛모양 특징
- node수 = edge 수

막대모양 특징
- node 수 = edge 수 + 1

8자모양 특징
- node 수 = edge 수 - 1

*/



function bfs(node, graph){

  var visited = new Array(graph.length).fill(false);
  var edges = 0;
  var nodes = 1;
  var queue = [];
  queue.push(node);
  
  while(queue.length > 0){
    var current = queue.shift();
    visited[current] = true;
    if(graph[current]){
      for(let n of (graph[current])){
        edges++;
        if(!visited[n]){
          nodes++;
          queue.push(n);
        }
      }
    }
  }

  return [edges, nodes];
}

function solution(edges) {
  var answer = [0,0,0,0];
  var graph = {};
  var outgoingEdges = {};
  var incomingEdges = {};
  for(let [from, to] of edges){

    if(!graph[from]) graph[from] = [];
    graph[from].push(to);

    outgoingEdges[from] = (outgoingEdges[from] || 0) + 1;
    incomingEdges[to] = (incomingEdges[to] || 0) + 1;
    
    if(!outgoingEdges[to]) outgoingEdges[to] = 0;
    if(!incomingEdges[from]) incomingEdges[from] = 0;

  }

  var centerNode = 0;
  var maxEdges = 0;

  for(let [node, edges] of Object.entries(outgoingEdges)){
    if(outgoingEdges[node] >= 2 && !incomingEdges[node]) {
      centerNode = parseInt(node);
      break;
  }}

  answer[0] = centerNode;
  for(let node of graph[centerNode]){
    let [edgeNum, nodeNum] = bfs(node, graph);
    if(edgeNum === nodeNum) answer[1]++;
    else if(edgeNum < nodeNum) answer[2]++;
    else answer[3]++;
  }

  return answer;
}


solution([[2, 3], [4, 3], [1, 1], [2, 1]]);