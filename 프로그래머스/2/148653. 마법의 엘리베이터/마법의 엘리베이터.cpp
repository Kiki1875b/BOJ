#include <bits/stdc++.h>
using namespace std;

int solution(int storey) {
    int answer = 0;

    while(storey != 0){
        int digit = storey % 10;

        if(digit > 5){
            answer += 10 - digit;
            storey += 10 - digit;
        }else if(digit == 5){
            if((storey / 10) %10 >= 5){
                answer += 10 - digit;
                storey += 10 - digit;
            }else{
                answer += digit;
            }
        }else{
            answer += digit;
        }
        storey /= 10;
    }
    return answer;
}