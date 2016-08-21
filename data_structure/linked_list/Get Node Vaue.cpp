/*
  Get Nth element from the end in a linked list of integers
  Number of elements in the list will always be greater than N.
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
int GetNode(Node *head,int positionFromTail)
{
  // This is a "method-only" submission. 
  // You only need to complete this method. 
    Node *p,*t;
    Node *arr[100];
    int count = 0;
    p = head;
    while(p != NULL) {
        arr[count] = p;
        count++;
        
        p = p->next;
    }
    return arr[count-1-positionFromTail]->data;
    
}
