#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer(numbers.size(), -1);
    stack<int> s;

    s.push(0);

    for(int i = 1; i < numbers.size(); i++){
        int current = numbers[i];
        while(!s.empty()){
            if(numbers[s.top()] < current){
                answer[s.top()] = current;
                s.pop();
            }else break;
        }
        s.push(i);
    }
   
    return answer;
}

int main(){
    solution({2,3,3,5});
}