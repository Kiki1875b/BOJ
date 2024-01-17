#include <string>
#include <vector>
#include <algorithm>
#include <stack>
#include <iostream>
using namespace std;

int stringToInt(string time){
    return (60*stoi(time.substr(0,2))) + stoi(time.substr(3));
}


bool order(vector<string> v1, vector<string> v2){
    return stringToInt(v1[1]) < stringToInt(v2[1]);
}


vector<string> solution(vector<vector<string>> plans) {
    vector<string> answer;
    stack<pair<string, int>> s;

    sort(plans.begin(), plans.end(), order);

    int curTime = stringToInt(plans[0][1]);
    int nextTime = stringToInt(plans[1][1]);
    int count = 0;

    while(count < plans.size() || !s.empty()){
        if(!s.empty()){
            if(count == plans.size()){
                answer.push_back(s.top().first);
                s.pop();
                continue;
            }
            if(curTime < nextTime){
                int remain = s.top().second;
                int available = nextTime - curTime;

                if(remain <= available){
                    answer.push_back(s.top().first);
                    curTime += s.top().second;
                    s.pop();
                }else{
                    s.top().second = remain - available;
                    curTime = nextTime;
                }
                continue;
            }
        }

        curTime = stringToInt(plans[count][1]) + stoi(plans[count][2]);
        nextTime = count + 1  >= plans.size() ? 1440 : stringToInt(plans[count+1][1]);

        if(curTime>nextTime){
            s.push({plans[count][0], curTime - nextTime});
            curTime = nextTime;
        }else{
            answer.push_back(plans[count][0]);
        }
        ++count;

    }


    return answer;
}