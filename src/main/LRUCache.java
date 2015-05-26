package main;
import java.util.HashMap;

/**
 * LRUCache class
 * - Least recently used concept
 * - Retrieve and set from cache in constant time
 * - Removes the least recently used item, after capacity is reached
 * - Removal occurs in constant time because it is connected to the node
 * 
 * @author Sahil Singhal
 * @since May 23, 2015
 *
 */
public class LRUCache<T> extends SimpleCache<T> implements ICache<T>{
    private HashMap<Integer, Node<T>> map;
    private DoublyLinkedList<T> list;
    
    public LRUCache(int capacity) {
        super(capacity);
        this.map = new HashMap<Integer, Node<T>>();
        this.list = new DoublyLinkedList<T>();
    }
    
    /**
     * Retrieve item from LRUCache
     * - place at end of cache
     * @throws NullPointerException when key does not exist
     */
    public T get(int key) {
        Object o = map.get(key);
        if(!(o instanceof Node))
            throw new NullPointerException();
        
        @SuppressWarnings("unchecked")
		Node<T> n = (Node<T>) o;
        this.list.moveToEnd(n);
        this.map.put(key, n);
        
        return n.value;
    }
    
    /**
     * Set item in LRUCache
     * - New item, at capacity: remove first item and add new node to list and map
     * - Item exists, at capacity: set new value in map and move to end of list
     * - New item, not at capacity: add to list and map
     * - Item exists, not at capacity: move to end of list, set in map
     */
    @SuppressWarnings("unchecked")
	public void set(int key, T value) {
        // At capacity
        if(this.map.size() == this.capacity) {
            Object o = this.map.get(key);
            // New node at capacity
            if(!(o instanceof Node)) {
                Node<T> n = new Node<T>(key, value);
                Node<T> throwAway = list.removeFirst();
                this.list.addNode(n);
                this.map.remove(throwAway.key);
                this.map.put(key, n);
            // Node exists at capacity 
            } else {
				Node<T> n = (Node<T>) o;
                n.value = value;
                n.key = key;
                this.list.moveToEnd(n);
                this.map.put(key, n);
            }
        // Have not reached capacity
        } else {
            Object o = this.map.get(key);
            Node<T> n;
            // Node does not exist
            if(!(o instanceof Node)) {
                n = new Node<T>(key, value);
                this.list.addNode(n);
            // Node exists
            } else {
                n = (Node<T>) o;
                n.key = key;
                n.value = value;
                this.list.moveToEnd(n);
            }
            this.map.put(key, n);
        }
    }
}