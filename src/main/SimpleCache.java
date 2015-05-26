package main;
import java.util.HashMap;

/**
 * Very simple cache
 * - Requires integer as key
 * - Uses hashmap to retrieve and set items
 * - Requires manual removal of items after reaching capacity
 * 
 * @author Sahil Singhal
 * @since May 25, 2015
 */
public class SimpleCache<T> implements ICache<T> {
	private HashMap<Integer, T> map  = new HashMap<Integer, T>();
	protected int capacity;
	
	public SimpleCache(int capacity){
		this.capacity = capacity;
	}

	@Override
	public void set(int key, T value) {
		if(this.map.size() == capacity)
			throw new RuntimeException("Cache is at capacity");
		else 	
			this.map.put(key, value);
	}

	@Override
	public T get(int key) {
		if(! (this.map.containsKey(key)) )
				throw new NullPointerException();
		
		return this.map.get(key);
	}
	
	public int size() {
		return this.map.size();
	}
	
	public T remove(int key) {
		return this.map.remove(key);
	}
}