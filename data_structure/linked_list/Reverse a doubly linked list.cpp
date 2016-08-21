/*
   Reverse a doubly linked list, input list may also be empty
   Node is defined as
   struct Node
   {
     int data;
     Node *next;
     Node *prev;
   }
*/
Node* Reverse(Node* head)
{
    // Complete this function
    // Do not write the main method. 
    if(head == NULL) {
        return head;
    }
    Node *p;
    p = head;
    while(p->next != NULL) {
        Node *t = p->next;
        p->next = p->prev;
        p->prev = t;
        p = t;
        
    }
    
    p->next = p->prev;
    p->prev = NULL;
    head = p;
    
    return head;
    
}
