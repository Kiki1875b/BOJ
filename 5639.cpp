#include <iostream>

using namespace std;

struct Node{
    int data;
    Node* left;
    Node* right;
};

Node* insert(Node* node, int val){
    if(node == NULL){
        node = new Node();
        node->data = val;
        node->left = node->right = NULL;
    }else if(node->data >= val){
        node->left = insert(node->left, val);
    }else{
        node->right = insert(node->right, val);
    }
    return node;
}

void postOrder(Node* node){
    if(node->left != NULL){
        postOrder(node->left);
    }
    if(node -> right != NULL){
        postOrder(node->right);
    }
    cout << node->data << '\n';
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    Node* root = NULL;
    int val;
    while(cin >> val){
        if(val == EOF) break;
        root = insert(root, val);
    }
    postOrder(root);
}