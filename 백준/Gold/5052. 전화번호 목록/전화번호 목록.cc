#include <iostream>
#include <vector>
#include <algorithm>


using namespace std;

int t, n;
int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cin >> t;
    while(t--){
        cin >> n;
        vector<string> a;
        bool check = false;
        for(int i = 0; i<n; i++){
            string temp;
            cin >> temp;
            a.push_back(temp);
        }

        sort(a.begin(), a.end());
        for(int i = 0; i<a.size()-1; i++){
            if(a[i] == a[i+1].substr(0,a[i].length())){
                check = true;
                break;
            }

        }
        if(check) cout << "NO" << "\n";
        else cout << "YES" << "\n";
    }
}