function createPath(route, graph, path) {
    let time = 0;
    let ans = 0; // 충돌 상황 횟수
    let last;

    for (let i = 0; i < route.length - 1; i++) {
        let begin = [...graph[route[i]]];  // 현재 포인트의 좌표 (복사본 생성)
        let end = graph[route[i + 1]];  // 다음 포인트의 좌표
        last = end;

        // 최단 경로를 따라 이동
        while (begin[0] !== end[0] || begin[1] !== end[1]) {
            let key = JSON.stringify(begin);  // 좌표를 문자열로 변환하여 키로 사용
            path[time][key] = (path[time][key] || 0) + 1;
            if (path[time][key] === 2) ans++; // 2대 이상의 로봇이 모인 경우 충돌 카운트

            let xDiff = end[0] - begin[0];
            let yDiff = end[1] - begin[1];

            // r 좌표가 먼저 변하는 규칙
            if (xDiff !== 0) {
                begin[0] += xDiff > 0 ? 1 : -1;  // r 좌표 증가/감소
            } else if (yDiff !== 0) {
                begin[1] += yDiff > 0 ? 1 : -1;  // c 좌표 증가/감소
            }
            time++;
        }
    }

    let lastKey = JSON.stringify(last);  // 마지막 도착 지점 처리
    path[time][lastKey] = (path[time][lastKey] || 0) + 1;
    if (path[time][lastKey] === 2) ans++;  // 마지막 지점에서 충돌 여부 확인

    return ans;
}

function solution(points, routes) {
    let graph = {};  // 각 포인트의 좌표를 저장하는 맵
    let path = Array.from({ length: 20001 }, () => ({})); // 시간별 좌표에 로봇이 몇 대 모였는지 기록
    let ans = 0;

    // 포인트의 좌표를 그래프에 저장 (1-based index)
    for (let i = 0; i < points.length; i++) {
        graph[i + 1] = points[i];
    }

    // 각 로봇의 경로를 따라가며 충돌 상황을 계산
    for (let route of routes) {
        ans += createPath(route, graph, path);
    }

    return ans;
}