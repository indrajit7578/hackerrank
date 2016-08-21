/*
  Delete Node at a given position in a linked list 
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* Delete(Node *head, int position)
{
  // Complete this method
    Node *p,*s,*t;
    int i = 0;
    p = head;
    while(p->next != NULL && i<position)
    {
        t = p;
        p = p->next;
        i++;
    }
    if(p == head)
    {
        s = p;
        head = p->next;
    }
    else
    {
        t->next = p->next;
        s = p;
            
    }
    free(s);
    return(head);
    
}