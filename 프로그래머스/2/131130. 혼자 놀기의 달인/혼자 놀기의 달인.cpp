#include <bits/stdc++.h>
using namespace std;

unordered_set<int> s;
/*
 * 상자의 수 = 카드의 수
 * cards 가 다음과 같을때 번호는 다음과 같이 붙는다
 * 8 6 3 7 2 5 1 4
 * 1 2 3 4 5 6 7 8
 *
 *
 */



int solution(vector<int> cards) {
    int n = cards.size();
    vector<bool> visited(n, false);
    vector<int> groupSize;

    for(int i =0; i<n; i++){
        if(!visited[i]){
            int size = 0;
            int current = i;

            while(!visited[current]){
                visited[current] = true;
                current = cards[current] - 1;
                size++;
            }

            groupSize.push_back(size);
        }
    }

    sort(groupSize.rbegin(), groupSize.rend());
    
    if (groupSize.size() >= 2) {
        return groupSize[0] * groupSize[1];
    } else {

        return 0;
    }
}

int main(){
    solution({8,6,3,7,2,5,1,4});
}