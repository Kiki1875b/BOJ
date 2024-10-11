function solution(plans) {
  var answer = [];
  var formattedPlans = [];

  function change(plan) {
    let stringTime = plan[1];
    let subject = plan[0];
    let duration = parseInt(plan[2], 10);

    let [hour, min] = stringTime.split(":");
    formattedPlans.push({ subject: subject, start: parseInt(hour, 10) * 60 + parseInt(min, 10), duration: duration });
  }

  for (let p of plans) {
    change([...p]);
  }

  // Sort plans by start time
  formattedPlans.sort((a, b) => a.start - b.start);

  let pending = [];
  let prevEndTime = 0;
  let last = "";

  for (let i = 0; i < formattedPlans.length; i++) {
    let current = formattedPlans[i];
    let currentTime = current.start;
    let endTime = currentTime + current.duration;
    last = current.subject;

    // Push the previous task into the answer if it has finished
    if (prevEndTime <= currentTime && i !== 0) {
      answer.push(formattedPlans[i - 1].subject);
    }

    // Handle pending tasks that were interrupted
    while (pending.length && prevEndTime < currentTime) {
      let pendingCur = pending[0];
      let timeLeft = currentTime - prevEndTime;

      if (pendingCur.duration <= timeLeft) {
        answer.push(pendingCur.subject);
        pending.shift();
        prevEndTime += pendingCur.duration;
      } else {
        pending[0].duration -= timeLeft;
        prevEndTime = currentTime;
      }
    }

    // If the current task overlaps with a previous one, add the previous to pending
    if (prevEndTime > currentTime) {
      let prevSubject = formattedPlans[i - 1];
      pending.unshift({
        subject: prevSubject.subject,
        duration: prevSubject.start + prevSubject.duration - currentTime,
      });
    }

    prevEndTime = endTime;
  }

  // Add the last task
  answer.push(last);

  // Handle any remaining pending tasks
  if (pending.length) {
    for (let p of pending) {
      answer.push(p.subject);
    }
  }

  return answer;
}
