#include <iostream>

using namespace std;


int N;
pair<char,char> tree[26];

void preorder(char current){
    if(current == '.') return;
    cout << current;
    preorder(tree[current-'A'].first);
    preorder(tree[current-'A'].second);
}

void inorder(char current){
    if(current=='.') return;
    inorder(tree[current-'A'].first);
    cout<<current;
    inorder(tree[current-'A'].second);
}

void postorder(char current){
    if(current=='.') return;
    postorder(tree[current-'A'].first);
    postorder(tree[current-'A'].second);
    cout<<current;
}


int main(){
    cin >> N;

    for (int i = 0; i<N; i++){
        char a,b,c;
        cin >> a >> b >> c;
        tree[a-'A'].first=b;
        tree[a-'A'].second=c;
    }
    preorder('A');
    cout <<"\n";
    inorder('A');
    cout<<'\n';
    postorder('A');
}