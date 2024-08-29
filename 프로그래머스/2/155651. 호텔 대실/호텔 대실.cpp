#include <bits/stdc++.h>
using namespace std;



vector<pair<int,int>> timeToInt(vector<vector<string>>& time){
    vector<pair<int,int>> ret;
    for(int i = 0; i<time.size(); i++){

        pair<int,int> temp {0,0};
        int hour, minute;
        char col;

        istringstream start(time[i][0]);
        start >> hour >> col >> minute;
        temp.first = hour * 60 + minute;

        istringstream end(time[i][1]);
        end >> hour >> col >> minute;
        temp.second = hour * 60 + minute + 10;

        ret.push_back(temp);
    }
    return ret;
}

bool isPossible(int num, vector<pair<int,int>>& time){

    priority_queue<int, vector<int>, greater<int>> pq;

    int cnt = 0, maxCnt = 0;

    for(auto t : time){
        while(!pq.empty() && t.first >= pq.top()){
            pq.pop();
            cnt--;
        }

        pq.push(t.second);
        cnt++;
        maxCnt = max(cnt, maxCnt);
        if(pq.size() > num) return false;
    }
    return true;
}

int solution(vector<vector<string>> book_time) {
    int answer = INT_MAX;

    vector<pair<int,int>> time = timeToInt(book_time);

    sort(time.begin(), time.end(), [](pair<int,int> a, pair<int,int> b){
        return a.first < b.first;
    });

    int l = 0; int r = 1001;

    while(l<=r){
        int mid = (l+r)/2;
        if(isPossible(mid, time)){
            answer = min(mid,answer);
            r = mid - 1;
        }else l = mid + 1;
    }



    return answer;

}