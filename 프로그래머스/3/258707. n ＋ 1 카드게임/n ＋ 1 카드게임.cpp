#include <bits/stdc++.h>
using namespace std;
int target, n;

bool possible(set<int>& s1, set<int>& s2){
    for(auto num : s1){
        int need = n + 1 - num;
        if(s2.find(need) != s2.end()){
            s1.erase(num);
            s2.erase(need);
            return true;
        }
    }
    return false;

}

int solution(int coin, vector<int> cards) {
    set<int> hand, draw;
    int answer = 1;
    n = cards.size();
    target = n/3;

    for(int i = 0 ; i<target; i++){
        hand.insert(cards[i]);
    }
    int curIdx = target;

    while(curIdx < n){
        for(int i = 0; i<2; i++){
            draw.insert(cards[curIdx++]);
        }

        if(hand.size() >= 2 && possible(hand,hand)){
            answer++;
        }else if(hand.size()>=1 && coin >= 1 && possible(hand,draw)){
            coin--; answer++;
        }else if(coin >= 2 && possible(draw, draw)){
            coin -= 2;
            answer++;
        }else{
            break;
        }
    }

    return answer;
}