package main;
/**
 * Cache interface
 * 
 * @author Sahil Singhal
 * @since May 24th, 2015
 *
 */
public interface ICache<T> {
	/**
	 * Set a new key-value pair in the cache
	 * @param key the key that is used to set the value
	 * @param value the value that is to be set in the cache
	 */
	void set(int key, T value);
	
	/**
	 * Get the value from a cache using a key
	 *
	 * @param key the key needed to retrieve that value
	 * @return the value from the cache
	 * @throws NullPointerException when key does not exist
	 */
	T get(int key);
}