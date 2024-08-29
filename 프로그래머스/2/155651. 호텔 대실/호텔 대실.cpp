#include <bits/stdc++.h>
using namespace std;

vector<pair<int,int>> timeToInt(vector<vector<string>> time){
    vector<pair<int,int>> ret;
    for(int i = 0; i<time.size(); i++){
        vector<string> t = time[i];
        pair<int,int> temp = {0,0};

        int hour, minutes;
        char colon;

        istringstream timeStart(time[i][0]);
        timeStart >> hour >> colon >> minutes;
        temp.first = hour*60+minutes;

        istringstream timeEnd(time[i][1]);
        timeEnd >> hour >> colon >> minutes;
        temp.second = hour*60 + minutes + 10;

        ret.push_back(temp);
    }
    return ret;
}


bool isPossible(int num, vector<pair<int,int>>& time){
    int maxCnt = 0;
    int cnt = 0;

    std::sort(time.begin(), time.end(), [](pair<int,int> a, pair<int,int> b){
        return a.first < b.first;
    });
    priority_queue<int, vector<int>, greater<int>> pq;

    for(auto interval : time){

        while(!pq.empty() && pq.top() <= interval.first){
            pq.pop();
            cnt--;
        }

        pq.push(interval.second);
        cnt++;

        maxCnt = max(cnt, maxCnt);
        if (pq.size() > num) return false;
    }

    return true;
}


int solution(vector<vector<string>> book_time) {
    int answer = INT_MAX;
    vector<pair<int,int>> time = timeToInt(book_time);

    int l = 0;
    int r = 99999999;

    while(l<=r){
        int mid = (l+r)/2;
        if(isPossible(mid, time)){
            r = mid - 1;
            answer = min(answer, mid);
        }else{
            l = mid + 1;
        }
    }


    return answer;
}