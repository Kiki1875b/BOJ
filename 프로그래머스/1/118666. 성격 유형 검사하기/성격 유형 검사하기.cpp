#include <bits/stdc++.h>
using namespace std;

vector<char> types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};

string solution(vector<string> survey, vector<int> choices) {
    string answer = "";
    unordered_map<char, int> m;

    for(auto t : types){
        m[t] = 0;
    }

    vector<int> ansVector(4,0);

    for(int i = 0; i<survey.size(); i++){
        char first, second;
        istringstream iss(survey[i]);
        iss >> first >> second;
        int point = choices[i] - 4;

        if(point == 0) continue;
        else if(point < 0){
            m[first] += abs(point);
        }else{
            m[second] += abs(point);
        }
    }

    for(int i = 0; i<7; i +=2){
        char first = types[i];
        char second = types[i + 1];

        if(m[first] > m[second]){
            answer += first;
        }else if(m[first] < m[second]){
            answer += second;
        }else{
            answer += first;
        }
    }

    return answer;
}