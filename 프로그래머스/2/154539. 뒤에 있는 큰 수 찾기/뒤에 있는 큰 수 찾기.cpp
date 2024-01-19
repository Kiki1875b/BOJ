#include <string>
#include <vector>
#include <stack>
using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer(numbers.size(), -1);
    stack<int> st;
    
    st.push(numbers[numbers.size() -1]);
    
    for(int i = numbers.size() -2 ; i >=0; i--){
        bool bSuccess = false;
        while(!st.empty()){
            int t = st.top();
            st.pop();
            if(t > numbers[i]){
                answer[i] = t;
                bSuccess = true;
                break;
            }
        }
        if(bSuccess){
            st.push(answer[i]);
        }
        st.push(numbers[i]);
    }
    


    return answer;
}
