#include <string>
#include <vector>

using namespace std;

string solution(vector<string> cards1, vector<string> cards2, vector<string> goal) {
    string answer = "Yes";
    int gidx = 0;
    int idx1 = 0;
    int idx2 = 0;
    while(true){
        if(gidx == goal.size()) break;
        
        
        if(goal[gidx] == cards1[idx1]){
            gidx++;
            idx1++;
        }else if(goal[gidx] == cards2[idx2]){
            gidx++;
            idx2++;
        }else{
            answer = "No";
            break;
        }
        
        
    }
    
    
    
    
    return answer;
}