
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> sequence, int k) {
    vector<int> answer;

    vector<pair<int,int>> v;
    int sum = 0;
    int i = 0; int j =0;

    sum += sequence[0];


    while(i < sequence.size()){
        if(sum > k){
            sum -= sequence[j];
            j++;
        }
        else if( k > sum ){
            i++;
            sum += sequence[i];
        }
        else if ( sum == k ){
            v.push_back({j,i});

            i++;
            sum += sequence[i];
        }
    }
    int min1 = 0; int min2 = INT_MAX;
    for(auto t : v){
        if( min2 - min1 > t.second - t.first){
            min2 = t.second; min1 = t.first;
        }
    }
    //cout << min1 << " " << min2;

    answer.push_back(min1); answer.push_back(min2);
    return answer;
}