/*
  Compare two linked lists A and B
  Return 1 if they are identical and 0 if they are not. 
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
int CompareLists(Node *headA, Node* headB)
{
  // This is a "method-only" submission. 
  // You only need to complete this method 
    Node *p,*q;
    p = headA;
    q = headB;
    while(p!= NULL && q != NULL)
    {
        if(p->data != q->data)
            break;
        else
        { 
            p = p->next;
            q = q->next;
        } 
    }
    if(p == q)
        return 1;
    else
        return 0;
    
}