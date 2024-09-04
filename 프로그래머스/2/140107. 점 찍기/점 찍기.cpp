#include <bits/stdc++.h>
using namespace std;

long long solution(int k, int d) {
    long long answer = 0;
    long long d_squared = (long long)d * d;  // d의 제곱을 미리 계산

    for (long long x = 0; x <= d; x += k) {
        // y^2 <= d^2 - x^2 을 만족하는 최대 y값을 찾는다.
        long long y_max = sqrt(d_squared - x * x);
        // y_max까지 k 간격으로 나올 수 있는 점의 개수를 더한다.
        answer += (y_max / k) + 1;
    }

    return answer;
}
