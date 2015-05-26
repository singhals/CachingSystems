package tests;

import static org.junit.Assert.*;
import main.LRUCache;

import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {
	LRUCache<Integer> cap5;

	@Before
	public void setUp() throws Exception {
		this.cap5 = new LRUCache<Integer>(5);
	}

	@Test
	public void testGet() {
		this.cap5.set(0, 1);
		this.cap5.set(4, 3);
		this.cap5.set(2, 4);
		this.cap5.set(5, 6);
		
		assertEquals(1, (int) this.cap5.get(0));
		assertEquals(3, (int) this.cap5.get(4));
		assertEquals(4, (int) this.cap5.get(2));
		assertEquals(6, (int) this.cap5.get(5));
	}

	@Test(expected = NullPointerException.class)
	public void testGetException() {
		this.cap5.get(0);
	}
	
	@Test
	public void testSet() {
		this.cap5.set(0, 1);
		this.cap5.set(4, 3);
		this.cap5.set(2, 4);
		this.cap5.set(5, 6);
		
		// Set existing key before capacity reached
		this.cap5.set(5, 8);
		assertEquals(8, (int) this.cap5.get(5));
		
		// Set new key before capacity reached
		this.cap5.set(3, 5);
		assertEquals(1, (int) this.cap5.get(0));
		
		// Set new key at capacity
		// Make key at 0 most recently used item
		// Key 4 becomes least recently used
		this.cap5.get(0);
		this.cap5.set(1, 3);
		assertEquals(3, (int) this.cap5.get(1));
		
		// TODO: check exception here
		// Value at key 4 should be removed
		//this.cap5.get(4);
		
		// Set existing key at capacity
		this.cap5.set(2, 6);
		assertEquals(6, (int) this.cap5.get(2));
	}
}
