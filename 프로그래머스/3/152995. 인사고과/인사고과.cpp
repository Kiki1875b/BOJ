#include <bits/stdc++.h>
using namespace std;

int solution(vector<vector<int>> scores) {
    int answer = 1;
    int wanho = scores[0][0] + scores[0][1];
    
    sort(scores.begin() + 1, scores.end(), [](vector<int> a, vector<int> b){
        return a[0]+a[1] > b[0] + b[1];
    });
    
    for(int i = 1; i< scores.size(); i++){
        if(scores[i][0] > scores[0][0] && scores[i][1] > scores[0][1]) return -1;
        int cur = scores[i][0] + scores[i][1];
        
        if(wanho < cur){
            answer++;
        }else break;
    }   
    
    int tmp = answer;
    for(int i = 2; i<tmp; i++){
        for(int j = 1; j < i; j++){
            if(scores[i][0] < scores[j][0] && scores[i][1] < scores[j][1]){
                answer--;
                break;
            }
        }
    }
    return answer;
}