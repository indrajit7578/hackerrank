/*
  Remove all duplicate elements from a sorted linked list
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* RemoveDuplicates(Node *head)
{
  // This is a "method-only" submission. 
  // You only need to complete this method. 
    Node *p;
    p = head;
    while(p != NULL) {
        if(p->next != NULL && (p->next)->data == p->data ) {
            p->next = (p->next)->next;
        }
        else {
            p = p->next;
        }
    }
    return head;
}
