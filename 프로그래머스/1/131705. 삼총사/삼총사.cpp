#include <string>
#include <vector>

using namespace std;
vector<vector<int>> combs;
int answer = 0;
void dfs(vector<int>& number, vector<int> v, int idx, int sum){
    
    if(v.size() == 3 && sum == 0){
        answer++;
        return;
    }

    
    for(int i = idx; i<number.size();i++){
        v.push_back(number[i]);
        dfs(number, v, i + 1, sum + number[i]);
        v.pop_back();
    }
    return;
}
int solution(vector<int> number) {

    vector<int> tmp;
    dfs(number, tmp, 0, 0);
    
    
    return answer;
}