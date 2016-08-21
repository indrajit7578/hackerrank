/*
  Insert Node at a given position in a linked list 
  The linked list will not be empty and position will always be valid
  First element in the linked list is at position 0
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* InsertNth(Node *head, int data, int position)
{
  // Complete this method only
  // Do not write main function. 
    Node *p,*s,*t;
    int i;
    p = head;
    i=0;
    while(i<position && p->next!=NULL)
    {
        t = p;    
        p = p->next;
        i++;
    }
    s = (Node*)malloc(sizeof(Node));
    s->data = data;
    s->next = NULL;
    if(p==head)
    {
        s->next = head;
        head = s;
    }    
    else
    {
        s->next = p;
        
        t->next = s;
    }
        
    
    return (head);
    
}