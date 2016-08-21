#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;

void swap(vector< pair<int,int> > &tree,int k,int root,int level ) {
    
    if(tree[root].first == -1 && tree[root].second == -1)
        return;
    
    if(level % k == 0) {
        //swap left and right;
        int t = tree[root].first;
        tree[root].first = tree[root].second;
        tree[root].second = t;
    }
    if(tree[root].first != -1)
        swap(tree,k,tree[root].first,level+1);
    if(tree[root].second != -1)
        swap(tree,k,tree[root].second,level+1);
    
}

void printInorder(vector< pair<int,int> > &tree, int root) {
    if(tree[root].first != -1)
        printInorder(tree,tree[root].first);
    cout<<root<<" ";
    if(tree[root].second != -1)
        printInorder(tree,tree[root].second);
}

int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    vector< pair<int,int> > tree;
    int n,k,t,i,j,l,m,x,y;
    cin>>n;
    tree.resize(n+1);
    tree.clear();
    for(i=1;i<=n;i++) {
        cin>>x>>y;
        tree[i]=make_pair(x,y);
    }
    cin>>t;
    while(t--) {
        cin>>k;
        swap(tree,k,1,1);
        printInorder(tree,1);
        cout<<endl;
    }
    return 0;
}
