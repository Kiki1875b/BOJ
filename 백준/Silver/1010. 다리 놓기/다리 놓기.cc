#include <iostream>


using namespace std;
int T;

int main(){
    cin >> T;
    while(T--){
        int N, M;
        cin >> N >> M;
        
        long long ans = 1;
        int r = 1;
        for(int i = M ; i > M - N; i--){
            ans *= i;
            ans /= r++;
        }
        cout << ans << "\n";
    }
}