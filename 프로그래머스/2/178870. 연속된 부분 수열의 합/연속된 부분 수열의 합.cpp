#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;

vector<int> solution(vector<int> sequence, int k) {
    vector<int> answer(2,0);
    queue<pair<int,int>> q;
    int n = sequence.size();
    int len = 1000001; 
    int sum = 0;
    
    for(int i = 0; i<n; i++){
        q.push({sequence[i],i});
        sum += sequence[i];
        while(sum > k){
            sum -= q.front().first;
            q.pop();
        }
        if(sum == k && q.size() < len){
            len = q.size();
            answer[0] = q.front().second;
            answer[1] = q.back().second;
        }
    }

    return answer;
}