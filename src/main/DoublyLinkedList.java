package main;
/**
 * Doubly Linked List class
 * 
 * @author Sahil Singhal
 * @since May 23, 2015
 */
public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    
    /**
     * Default DoublyLinkedList constructor
     */
    public DoublyLinkedList() {
        this.head = new Node<T>();
        this.tail = new Node<T>();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }
    
    /**
     * Remove a node from the list
     * @param n - node to be removed
     * @return the node that is being removed
     */
    public Node<T> removeNode(Node<T> n) {
        Node<T> before = n.prev;
        Node<T> after = n.next;
        Node<T> throwAway = n;
        
        before.next = after;
        after.prev = before;
            
        return throwAway;
    }
    
    /**
     * Add a node to the end of the list
     * @param newLast - new node to be added
     */
    public void addNode(Node<T> newLast) {
        Node<T> oldLast = this.tail.prev;
        
        newLast.prev = oldLast;
        newLast.next = this.tail;
        
        this.tail.prev = newLast;
        oldLast.next = newLast;
    }
    
    /**
     * Move a node to the end of the list
     * @param n - node that moves to end of the list
     */
    public void moveToEnd(Node<T> n) {
        Node<T> move = this.removeNode(n);
        this.addNode(move);
    }
    
    /**
     * Remove the top node of the list
     * @return node that is being removed
     */
    public Node<T> removeFirst() {
        Node<T> first = this.head.next;
        removeNode(first);
        return first;
    }
}