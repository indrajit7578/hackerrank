/*
   Find merge point of two linked lists
   Node is defined as
   struct Node
   {
       int data;
       Node* next;
   }
*/
int FindMergeNode(Node *headA, Node *headB)
{
    // Complete this function
    // Do not write the main method. 
    int count1=0,count2=0;
    Node *a,*b;
    a = headA;
    b = headB;
    while(a != NULL){
        count1++;
        a=a->next;
    }
        
        
    while(b != NULL){
        count2++;
        b = b->next;
    }
        
    int d = abs(count1-count2);
    a = headA;
    b = headB;
    if(count1 > count2)
        while(d--)
            a = a->next;
    else
        while(d--)
            b = b->next;
    while(a != b) {
        a = a->next;
        b = b->next;
        
    }
    return a->data;
        
}
