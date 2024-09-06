#include <bits/stdc++.h>
using namespace std;

/*
 * 입차기록 출차기록이 주어진다
 * {기본시간, 기본요금, 단위시간, 단위요금}
 *
 */

int stringToTime(string time){
    int hour = stoi(time.substr(0,2));
    int minute = stoi(time.substr(3));

    return hour*60 + minute;
}
vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    int lastTime = 23*60 + 59;
    map<string, vector<int>> m;
    map<string, int> m2;

    for(int i = 0; i<records.size(); i++){
        int time = stringToTime(records[i].substr(0,5));
        string carNum = records[i].substr(6,4);

        m[carNum].push_back(time);
    }

    for(auto it : m){
        int time = 0;
        int cost = 0;

        for(auto i = 0; i<it.second.size()-1; i += 2){
            time += it.second[i + 1] - it.second[i];
        }
        if(it.second.size() % 2 != 0){
            time += lastTime - it.second.back();
        }

        if(time < fees[0]) cost = fees[1];
        else{
            cost = fees[1] + (time - fees[0])/fees[2] * fees[3];
            if((time - fees[0])%fees[2] != 0) {
                cost += fees[3];
            }
        }

        answer.push_back(cost);
    }

    return answer;
}