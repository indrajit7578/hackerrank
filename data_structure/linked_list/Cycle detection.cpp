/*
Detect a cycle in a linked list. Note that the head pointer may be 'NULL' if the list is empty.

A Node is defined as: 
    struct Node {
        int data;
        struct Node* next;
    }
*/

bool has_cycle(Node* head) {
    // Complete this function
    // Do not write the main method
    Node *p,*t;
    p = head;
    t = p->next;
    while(t != NULL) {
        if(p == NULL)
            return 0;
        p = p->next;
        if(t->next == NULL)
            return 0;
        t = (t->next)->next;
        if(p == t)
            return 1;
    }
    return 0;
}

