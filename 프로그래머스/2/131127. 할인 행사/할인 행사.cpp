#include <bits/stdc++.h>
using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    int answer = 0;
    unordered_map<string, int> m;

    // 초기 원하는 아이템들과 그 수량을 매핑
    for (int i = 0; i < want.size(); i++) {
        m[want[i]] = number[i];
    }

    // 슬라이딩 윈도우 방식으로 검사
    for (int i = 0; i <= discount.size() - 10; i++) {
        unordered_map<string, int> currentMap = m; // 현재 검사할 10일간의 상품
        bool valid = true;

        // 10일간의 할인 품목을 순회하며 필요한 수량을 체크
        for (int j = i; j < i + 10; j++) {
            if (currentMap.find(discount[j]) != currentMap.end()) {
                currentMap[discount[j]]--; // 원하는 상품 수량을 하나 줄임
            }
        }

        // 모든 상품의 수량이 다 맞았는지 확인
        for (auto& it : currentMap) {
            if (it.second > 0) { // 아직 남은 수량이 있다면 무효
                valid = false;
                break;
            }
        }

        if (valid) answer++;
    }

    return answer;
}
