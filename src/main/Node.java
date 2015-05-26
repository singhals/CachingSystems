package main;
/**
 * Node class
 * 
 * @author Sahil Singhal
 * @since May 23, 2015
 */

public class Node<T> {
    public int key;
    public T value;
    public Node<T> next;
    public Node<T> prev;
    
    /**
     * Empty Node constructor
     * - Used for dummy head and tail
     */
    public Node() {}
    
    /**
     * Simple Node constructor with a value
     */
    public Node(T value) {
    	this.value = value;
    }
    
    /**
     * Node constructor with key-value pair
     */
    public Node(int key, T value) {
        this.key = key;
        this.value = value;
    }
}