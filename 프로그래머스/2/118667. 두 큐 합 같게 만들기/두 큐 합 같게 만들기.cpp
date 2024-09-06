#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

/*
 * 길이가 같은 큐 2개
 * 하나에서 뽑아 다른 하나에 삽입할 수 있다
 * 목표 : 두 큐의 합이 같게 만들 수 있는 최소 횟수
 */

int solution(vector<int> queue1, vector<int> queue2) {
    int answer = 0;
    vector<ll> mergedQueue;
    mergedQueue.reserve(queue1.size() + queue2.size()); // 메모리 할당 최적화
    mergedQueue.insert(mergedQueue.end(), queue1.begin(), queue1.end());
    mergedQueue.insert(mergedQueue.end(), queue2.begin(), queue2.end());

    int n = queue1.size();
    ll sum = 0;
    ll q1 = 0;
    ll q2 = 0;

    for(int i = 0; i<n; i++){
        q1 += queue1[i];
        q2 += queue2[i];
        sum += queue1[i] + queue2[i];
    }
    if(sum % 2 != 0) return -1;


    int l = 0;
    int r = n;
    bool possible = false;
    
    while(l<=r && r< mergedQueue.size()){
        if(q1 == q2) {
            possible = true;
            break;
        }
        if(q1 > q2){
            q1 -= mergedQueue[l];
            q2 += mergedQueue[l];
            l++;
            answer++;
        }else{
            q2 -= mergedQueue[r];
            q1 += mergedQueue[r];
            r++;
            answer++;
        }
    }


    return possible ? answer : -1;
}