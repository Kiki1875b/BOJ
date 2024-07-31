#include <bits/stdc++.h>
using namespace std;

int solution(int n, vector<int> tops) {
    int answer = 0;

    /*
     * 각 아래방향 타일을 덮는 방법 4가지
     * 1. 위쪽 정삼각형과 함께 덮기
     * 2. 왼쪽 정삼각형과 함께 덮기
     * 3. 오른쪽 정삼각형과 함께 덮기
     * 4. 정삼각형으로 덮기
     *
     * 두개의 배열 필요
     * a 배열 = 3번 방법을 쓴 경우의 수
     * b 배열 = 3번 방법을 쓰지 않은 경우의 수
     */

    vector<int> a(n+1,0), b(n+1,0);
    a[0] = 0;
    b[0] = 1;

    for(int i = 1; i<=n; i++){
        if(tops[i-1] == 1){
            a[i] = ((a[i-1] + b[i-1])%10007);
            b[i] = ((a[i-1] * 2 + b[i - 1] * 3)%10007);
        }else{
            a[i] = ((a[i-1] + b[i-1])%10007);
            b[i] = ((a[i-1] + b[i-1] * 2) % 10007);
        }
    }

    answer = (a[n] + b[n]) % 10007;


    return answer;
}