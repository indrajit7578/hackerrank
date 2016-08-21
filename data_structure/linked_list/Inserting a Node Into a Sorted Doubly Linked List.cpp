/*
    Insert Node in a doubly sorted linked list 
    After each insertion, the list should be sorted
   Node is defined as
   struct Node
   {
     int data;
     Node *next;
     Node *prev;
   }
*/
Node* SortedInsert(Node *head,int data)
{
    // Complete this function
   // Do not write the main method. 
    Node *p,*t;
    p = head;
    if(head == NULL) {
        Node *node_new = new Node[1];
        node_new->data = data;
        node_new->next = NULL;
        node_new->prev = NULL;
        head = node_new;
    }
    else {
        while(p->next != NULL && (p->next)->data < data) {
            p = p->next;
        }
        if(p == head && p->data > data) {
            Node *node_new = new Node[1];
            node_new->data = data;
            node_new->next = p;
            node_new->prev = NULL;
            head = node_new;
            return head;
        }
        Node *node_new = new Node[1];
        node_new->data = data;
        node_new->next = p->next;
        p->next = node_new;
        node_new->prev = p;
        
    }
    return head;
}
