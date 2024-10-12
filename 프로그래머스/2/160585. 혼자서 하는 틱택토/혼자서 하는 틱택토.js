function solution(board) {
  var answer = 1;
  let oCnt = 0;
  let xCnt = 0;

  // O와 X의 개수를 카운트
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board[0].length; j++) {
      if (board[i][j] === "O") oCnt++;
      else if (board[i][j] === "X") xCnt++;
    }
  }

  let xWin = false;
  let oWin = false;

  // O의 승리 조건 확인 (가로, 세로, 대각선)
  for (let i = 0; i < 3; i++) {
    if (board[i][0] === "O" && board[i][1] === "O" && board[i][2] === "O") oWin = true; // 가로
    if (board[0][i] === "O" && board[1][i] === "O" && board[2][i] === "O") oWin = true; // 세로
  }
  if (board[0][0] === "O" && board[1][1] === "O" && board[2][2] === "O") oWin = true; // 대각선
  if (board[0][2] === "O" && board[1][1] === "O" && board[2][0] === "O") oWin = true; // 반대 대각선

  // X의 승리 조건 확인 (가로, 세로, 대각선)
  for (let i = 0; i < 3; i++) {
    if (board[i][0] === "X" && board[i][1] === "X" && board[i][2] === "X") xWin = true; // 가로
    if (board[0][i] === "X" && board[1][i] === "X" && board[2][i] === "X") xWin = true; // 세로
  }
  if (board[0][0] === "X" && board[1][1] === "X" && board[2][2] === "X") xWin = true; // 대각선
  if (board[0][2] === "X" && board[1][1] === "X" && board[2][0] === "X") xWin = true; // 반대 대각선

  // 규칙 검증
  if (Math.abs(oCnt - xCnt) > 1) return 0; // O와 X의 차이가 1 이상이면 잘못된 상태
  if (xCnt > oCnt) return 0; // X의 개수가 더 많을 수 없음
  if (xWin && oWin) return 0; // O와 X가 동시에 승리할 수 없음
  if (oWin && xCnt === oCnt) return 0; // O가 이겼다면 X는 더 적어야 함
  if (xWin && oCnt > xCnt) return 0; // X가 이겼다면 O의 수와 같아야 함

  return answer;
}
