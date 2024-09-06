#include <bits/stdc++.h>
using namespace std;

vector<int> answer;
int maxPoint = -1;

// 점수 계산 함수
void calcPoint(vector<int>& comb, vector<int>& info) {
    int ryan = 0, apeach = 0;

    for (int i = 0; i < 11; i++) {
        if (info[i] == 0 && comb[i] == 0) continue; // 둘 다 맞히지 않은 경우
        if (info[i] >= comb[i]) {
            apeach += (10 - i);  // 어피치가 점수를 가져가는 경우
        } else {
            ryan += (10 - i);    // 라이언이 점수를 가져가는 경우
        }
    }

    if (ryan > apeach) {
        if (ryan - apeach > maxPoint) {
            answer = comb;
            maxPoint = ryan - apeach;  // 점수 차이 갱신
        } else if (ryan - apeach == maxPoint) {
            // 점수 차이가 같을 경우 더 낮은 점수를 많이 맞힌 경우를 선택
            for (int i = 10; i >= 0; i--) {
                if (comb[i] != answer[i]) {
                    if (comb[i] > answer[i]) {
                        answer = comb;
                    }
                    break;
                }
            }
        }
    }
}

// DFS 탐색 함수
void dfs(vector<int>& info, vector<int> current, int n, int idx) {
    if (idx == 11) {
        if (n > 0) current[10] += n;  // 남은 화살은 0점에 몰아 쏘기
        calcPoint(current, info);
        return;
    }

    // 현재 점수에 쏘지 않는 경우
    dfs(info, current, n, idx + 1);

    // 현재 점수에 쏘는 경우
    if (n > info[idx]) {
        current[idx] = info[idx] + 1;  // 이기기 위해서는 어피치보다 1발 더 쏘아야 함
        dfs(info, current, n - current[idx], idx + 1);
    }
}

// 최종 솔루션 함수
vector<int> solution(int n, vector<int> info) {
    vector<int> tmp(11, 0);
    dfs(info, tmp, n, 0);

    if (maxPoint == -1) return {-1};  // 우승할 수 없는 경우
    return answer;
}