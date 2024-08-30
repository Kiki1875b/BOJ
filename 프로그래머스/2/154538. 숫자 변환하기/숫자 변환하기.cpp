#include <bits/stdc++.h>
using namespace std;

int solution(int x, int y, int n) {
    if (y == x) return 0;
    
    queue<pair<int, int>> q;  // pair(current_value, step_count)
    set<int> visited;         // To avoid revisiting the same state
    
    q.push({y, 0});
    visited.insert(y);
    
    while (!q.empty()) {
        auto [cur, cnt] = q.front();
        q.pop();
        
        if (cur == x) return cnt;
        
        if (cur % 2 == 0 && cur / 2 >= x && !visited.count(cur / 2)) {
            q.push({cur / 2, cnt + 1});
            visited.insert(cur / 2);
        }
        if (cur % 3 == 0 && cur / 3 >= x && !visited.count(cur / 3)) {
            q.push({cur / 3, cnt + 1});
            visited.insert(cur / 3);
        }
        if (cur - n >= x && !visited.count(cur - n)) {
            q.push({cur - n, cnt + 1});
            visited.insert(cur - n);
        }
    }
    
    return -1;  // If no solution is found
}
