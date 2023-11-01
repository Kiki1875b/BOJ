#include<iostream>
using namespace std;

int map[100000];

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	int n, m; cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> map[i];
	}
	int start, end;
	start = 0;
	end = m-1;
	int tmp = 0;
	for (int i = 0; i < m; i++) {
		tmp += map[i];
	}
	int max = tmp;
	while (end != n-1) {
		tmp -= map[start];
		start++;
		end++;
		tmp += map[end];
		if (max < tmp) {
			max = tmp;
		}
	}
	cout << max;

	return 0;
}