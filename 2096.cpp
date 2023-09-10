#include <iostream>
#include <algorithm>
using namespace std;
int N;
int arr[3];
int mn_arr[3] = {0};
int mx_arr[3] = {0};

int main(){
    cin >> N;
    int temp0, temp1, temp2;
    for(int i = 1; i<=N; i++){
        cin >> arr[0] >> arr[1] >> arr[2];
        temp0 = mn_arr[0];
        temp1 = mn_arr[1];
        temp2 = mn_arr[2];

        mn_arr[0] = min(temp0,temp1) + arr[0];
        mn_arr[2] = min(temp1, temp2) + arr[2];
        mn_arr[1] = min(min(temp0,temp1),temp2) + arr[1];

        temp0 = mx_arr[0];
        temp1 = mx_arr[1];
        temp2 = mx_arr[2];

        mx_arr[0] = max(temp0,temp1) +arr[0];
        mx_arr[1] = max(max(temp0,temp1),temp2) + arr[1];
        mx_arr[2] = max(temp1,temp2) + arr[2];
    }
    cout << max(max(mx_arr[0],mx_arr[1]),mx_arr[2]) << " " << min(min(mn_arr[0],mn_arr[1]),mn_arr[2]);
}
