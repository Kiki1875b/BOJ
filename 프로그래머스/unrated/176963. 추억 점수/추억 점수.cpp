#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    int idx;
    int sum = 0;
    vector<int> answer;
    
    for(int i = 0; i<photo.size(); i++){
        for(int j = 0; j<photo[i].size(); j++){
            if(find(name.begin(), name.end(), photo[i][j]) != name.end()){
                idx = find(name.begin(), name.end(), photo[i][j]) - name.begin();
                sum += yearning[idx];
            }
        }
        answer.push_back(sum);
        sum = 0;
    }
    
    
    return answer;
}