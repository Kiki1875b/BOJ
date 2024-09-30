function possible(num, diffs, times, limit) {
  var timeTaken = times[0];
  var prevTime = times[0];
  for (let i = 1; i < diffs.length; i++) {
    var currentDiff = diffs[i];
    var currentTime = times[i];
    if (currentDiff <= num) {
      timeTaken += currentTime;
      prevTime = currentTime;
    } else {
      timeTaken += (prevTime + currentTime) * (currentDiff - num) + currentTime;
      prevTime = currentTime;
    }
    if (timeTaken > limit) {
      return false;
    }
  }
  return true;
}

function solution(diffs, times, limit) {
  var left = 1;  // Changed from 0 to 1
  var right = 300000;  // Changed to maximum difficulty

  while (left <= right) {
    var mid = Math.floor((left + right) / 2);
    if (possible(mid, diffs, times, limit)) {
      right = mid - 1;
    } else {
      left = mid + 1;
    }
  }

  return left;  // Return left instead of answer
}