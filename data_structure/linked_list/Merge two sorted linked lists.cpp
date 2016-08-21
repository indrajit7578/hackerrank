/*
  Merge two sorted lists A and B as one linked list
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* MergeLists(Node *headA, Node* headB)
{
  // This is a "method-only" submission. 
  // You only need to complete this method
    Node *new_head,*p,*q,*t;
    p = headA;
    q = headB;
    new_head = NULL; //null    1->2->null
    while(p!= NULL && q != NULL) {
        if(p->data < q->data) {
            if(new_head == NULL) {
                new_head = p;
                t = new_head;
            }
            else {
                t->next = p;
                t = t->next;
            }
            p = p->next;
        }
        else {
            if(new_head == NULL) {
                new_head = q;
                t = new_head;
            }
            else {
                t->next = q;
                t = t->next;
            }
            q = q->next;
        }
    }
    while(p != NULL) {
        if(new_head == NULL) {
            new_head = p;
            t = new_head;
        }
        else {
            t->next = p;
            t =t->next;    
        }
        
        p = p->next;
    }
    while(q != NULL) {
        if(new_head == NULL) {
            new_head = q;
            t = new_head;
        }
        else {
            t->next = q;
            t =t->next;    
        }
        q = q->next;
    }
    return new_head;
}
