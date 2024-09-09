#include <bits/stdc++.h>
using namespace std;
typedef long long ll;


vector<string> solution(vector<vector<int>> line) {
    vector<string> answer;

    set<pair<ll,ll>> pos;


    for(int i = 0; i<line.size() - 1;i++){
        for(int j = i+1; j<line.size(); j++){
            ll num = (ll) line[i][0] * (ll) line[j][1] - (ll)line[i][1] * (ll) line[j][0];
            if(num != 0){
                double x = ((double) line[i][1] * line[j][2] - (double)line[i][2] * line[j][1])/num;
                double y = ((double) line[i][2] * line[j][0] - (double)line[i][0] * line[j][2])/num;

                if(x - (ll)x == 0 && y-(ll)y==0){
                    pos.insert({x,y});
                }
            }
        }
    }

    ll minX = LLONG_MAX;
    ll minY = minX;

    ll maxX = LLONG_MIN;
    ll maxY = maxX;

    for(auto i : pos){
        minX = min(i.first, minX);
        minY = min(i.second, minY);
        maxX = max(i.first, maxX);
        maxY = max(i.second, maxY);
    }

    for(ll y = maxY; y >= minY; y--) { // Invert Y-axis for correct plotting
        string tmp = "";
        for(ll x = minX; x <= maxX; x++) {
            tmp += ".";
        }
        answer.push_back(tmp);
    }

    for(auto p : pos) {
        ll shiftedX = p.first - minX;
        ll shiftedY = maxY - p.second; // Invert Y-axis to match grid
        answer[shiftedY][shiftedX] = '*';
    }



    return answer;
}