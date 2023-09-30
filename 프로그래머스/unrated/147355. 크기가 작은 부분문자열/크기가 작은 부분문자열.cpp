#include <string>
#include <vector>

using namespace std;

int solution(string t, string p) {
    int answer = 0;
    int sub = p.size();
    
    for(int i = 0; i<t.size() - sub + 1; i++){
        string num = t.substr(i,sub);
        bool same = true;
        for(int j = 0; j<num.size(); j++){
            if(num[j] < p[j]){
                answer++;
                same =false; 
                break;
                
            }
            if(num[j] > p[j]){
                same = false;
                break;
            }
            
        }
        if(same) answer++;
    }
    return answer;
}
