package main;
import java.util.HashMap;

/**
 * FIFO Cache class
 * - First-in first-out concept
 * - Retrieve and set from cache in constant time
 * - Removes the top item in list at capacity
 * - Removal occurs in constant time because it is connected to the node
 * 
 * @author Sahil Singhal
 * @since May 25, 2015
 */
public class FIFOCache<T> extends SimpleCache<T> implements ICache<T> {
	private HashMap<Integer, Node<T>> map;
    private DoublyLinkedList<T> list;
    
	public FIFOCache(int capacity) {
		super(capacity);
		this.map = new HashMap<Integer, Node<T>>();
		this.list = new DoublyLinkedList<T>();
	}
	
	/**
     * Retrieve item from FIFOCache
     * - place at end of cache
     */
    public T get(int key) {
        Object o = map.get(key);
        if(!(o instanceof Node))
            throw new NullPointerException();
        
        @SuppressWarnings("unchecked")
		Node<T> n = (Node<T>) o;
        this.map.put(key, n);
        
        return n.value;
    }
    
    /**
     * Set item in FIFOCache
     * - New item, at capacity: remove first item in list and in map
     * - Item exists, at capacity: set new value in map and keep position
     * - New item, not at capacity: add to list and map
     * - Item exists, not at capacity: set in map
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
            }
            this.map.put(key, n);
        }
    }
}