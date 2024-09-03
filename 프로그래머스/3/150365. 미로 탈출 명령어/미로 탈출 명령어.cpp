#include <bits/stdc++.h>
using namespace std;

string solution(int n, int m, int x, int y, int r, int c, int k) {
    string answer = "";
    int dist = abs(r - x) + abs(c - y);

    // Check if the target is reachable with exactly k moves
    if (dist > k || (k - dist) % 2 != 0) {
        return "impossible";
    }

    // Directions ordered by the problem's requirements for lexicographical order
    vector<pair<int, int>> directions = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    vector<char> dirChar = {'d', 'l', 'r', 'u'};

    while (k > 0) {
        bool moved = false;

        for (int i = 0; i < 4; i++) {
            int nx = x + directions[i].first;
            int ny = y + directions[i].second;
            int remainingDist = abs(r - nx) + abs(c - ny);

            if (nx > 0 && ny > 0 && nx <= n && ny <= m && k - 1 >= remainingDist) {
                answer += dirChar[i];
                x = nx;
                y = ny;
                k--;
                moved = true;
                break;
            }
        }

        // If no move is possible, it's impossible
        if (!moved) {
            return "impossible";
        }
    }

    return answer;
}

int main() {
    cout << solution(3, 4, 2, 3, 3, 1, 5) << endl; // Expected output: "dllrl"
    cout << solution(2, 2, 1, 1, 2, 2, 2) << endl; // Expected output: "dr"
    cout << solution(3, 3, 1, 2, 3, 3, 4) << endl; // Expected output: "impossible"
}
