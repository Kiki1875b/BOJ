#include <bits/stdc++.h>
using namespace std;

bool found= false;

string dfs(vector<string>& cards1,
           vector<string>& cards2,
           string goal,
           string current,
           int c1Idx, int c2Idx){

    if(current == goal) { found = true; return "Yes"; }


    if(c1Idx < cards1.size()) {
        dfs(cards1, cards2, goal, current + cards1[c1Idx], c1Idx + 1, c2Idx);
    }
    if(c2Idx < cards2.size()) {
        dfs(cards1, cards2, goal, current + cards2[c2Idx], c1Idx, c2Idx + 1);
    }


    return found == true ? "Yes" : "No";
}


string solution(vector<string> cards1, vector<string> cards2, vector<string> goal) {
    string answer = "";
    string t = "";

    for(auto i : goal){
        t+=i;
    }

    answer = dfs(cards1,cards2, t, "", 0,0);
    
    return answer;
}