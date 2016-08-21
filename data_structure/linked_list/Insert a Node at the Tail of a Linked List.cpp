/*
  Insert Node at the end of a linked list 
  head pointer input could be NULL as well for empty list
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* Insert(Node *head,int data)
{
  // Complete this method
    Node *p,*s,*t;
    p = head;
    while(p != NULL)
    {
        t = p;
         p = p->next;
    }
    s = (Node *)malloc(sizeof(Node));
    s->data = data;
    s->next = NULL;
    if(p == head)
        head = s;
    else
        t->next = s;
    return(head);
}