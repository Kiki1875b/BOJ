#include <bits/stdc++.h>
using namespace std;

/*
 * 컨테이너 벨트가 총 2개 있다 : 본 벨트, 보조 벨트
 *
 * 본 벨트는 맨 앞의 상자만 뺄 수 있다
 * 보조벨트는 마지막에 넣은 상자만 뺄 수 있다
 * 본 벨트에서 순서가 맞지 않는다면 보조벨트에 저장한다
 * 총 몇개의 상자가 실리는지
 *
 * order = [4,3,1,2,5] 일때 1,2,3,4,5 를 order 의 순서로 만들 수 있는지
 */
int solution(vector<int> order) {
    int answer = 0;
    queue<int> q;
    stack<int> s;

    for(int i = 1; i<=order.size(); i++){
        q.push(i);
    }

    for(int i = 0; i<order.size(); i++){

        while(true) {
            if (q.front() != order[i]) {
                if (s.empty() || s.top() != order[i]) {
                    s.push(q.front());
                    q.pop();
                } else if (s.top() == order[i]) {
                    answer++;
                    s.pop();
                    break;
                }
            } else if (q.front() == order[i]) {
                answer++;
                q.pop();
                break;
            }

            if(q.empty() && q.front() != order[i] && s.top() != order[i]) return answer;
        }

    }
    return answer;
}