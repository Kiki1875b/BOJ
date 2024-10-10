function solution(k, n, reqs){
  function combs(n, k, arr){
    let ret = [];
    if(n <= 0) return false;
    if(k === 1) return[[...arr, n]];

    for(let i = 1; i<n; i++){
      const attached = combs(n - i, k - 1, [...arr, i]);
      if(!attached) return false;
      ret.push(...attached);
    }

    return ret;
  }
  const combination = combs(n, k, []);

  let ret = Infinity;

  for(let c of combination){
    const typeArr = [];
    
    for(let i = 0; i<c.length; i++){
      for(let j = 0; j<c[i]; j++){
        typeArr.push({type: i + 1 , end:0});
      }
    }

    let totalTime = 0;
    for(const [start, dur, type] of reqs){
      let minCounselor = {end:Infinity};

      for(const counselor of typeArr){
        if(counselor.type === type && counselor.end < minCounselor.end){
          minCounselor = counselor;
        }
      }

      if(start < minCounselor.end){
        totalTime += minCounselor.end - start;
        minCounselor.end = minCounselor.end + dur;
      }else{
        minCounselor.end = start + dur;
      }
    }

    ret = ret > totalTime ? totalTime : ret;

  }

  return ret;
  
}
