#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// section_len은 배열 section의 길이입니다.
int solution(int n, int m, int section[], size_t section_len) {
    int answer = 0;
    int cur = 0;
    for(int i = 0; i<section_len; i++){
        if(section[i] < cur) continue;
        answer++;
        cur = section[i] + m;
    }
    
    
    
    return answer;
}