/*
  Reverse a linked list and return pointer to the head
  The input list will have at least one element  
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* Reverse(Node *head)
{
  // Complete this method
    if(head == NULL)
        return head;
    Node *p,*q,*r,*t;
    p = head;
    if(p->next == NULL)
        return head;
    q = p->next;
    if(q->next == NULL) {
        head = q;
        q->next = p;
        p->next = NULL;
        return head;
    }
    r = q->next;
    while(r != NULL) {
        t = r->next;
        r->next = q;
        q->next = p;
        p = q;
        q = r;
        r = t;
    }
    head->next = NULL;
    head = q;
    return head;
    
}
