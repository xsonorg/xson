package org.xson.core.util;

/**
 * @author david<xson_org@126.com>
 * @since JDK1.6
 */
public class ObjectHashMap<K, V> {

	// static final int DEFAULT_INITIAL_CAPACITY = 16;
	// static final int MAXIMUM_CAPACITY = 1 << 30;
	// static final float DEFAULT_LOAD_FACTOR = 0.75f;
	// final float loadFactor;

	transient Entry[]	table;

	transient int		size;

	int					threshold;

	// transient volatile int modCount;
	// transient int modCount;

	/*
	 * public ObjectHashMap(int initialCapacity, float loadFactor) { // if (initialCapacity < 0) // throw new IllegalArgumentException(
	 * "Illegal initial capacity: " // + initialCapacity); if (initialCapacity > MAXIMUM_CAPACITY) initialCapacity = MAXIMUM_CAPACITY; // if
	 * (loadFactor <= 0 || Float.isNaN(loadFactor)) // throw new IllegalArgumentException("Illegal load factor: " // + loadFactor);
	 * 
	 * // Find a power of 2 >= initialCapacity // int capacity = 1; // while (capacity < initialCapacity) // capacity <<= 1;
	 * 
	 * // this.loadFactor = loadFactor; // threshold = (int) (capacity * loadFactor); // table = new Entry[capacity]; }
	 * 
	 * public ObjectHashMap() { this.loadFactor = DEFAULT_LOAD_FACTOR; threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR); table = new
	 * Entry[DEFAULT_INITIAL_CAPACITY]; }
	 */

	public ObjectHashMap(int initialCapacity) {
		threshold = initialCapacity;
		table = new Entry[initialCapacity];
	}

	static int hash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	static int indexFor(int h, int length) {
		return h & (length - 1);
	}

	public V get(Object key) {

		// int hash = hash(key.hashCode());

		int hash = 0;
		if (key instanceof Number || key instanceof String) {
			hash = hash(key.hashCode());
		} else {
			hash = System.identityHashCode(key);
		}

		// int hash = hash(key.getClass().hashCode() + key.hashCode());
		for (Entry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
			// Object k;
			// if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
			// return e.value;
			Object k = e.key;
			if (k == key) {
				return e.value;
			}

			// if(e.hash != hash || k.getClass() != key.getClass()){
			// return null;
			// }
			if (k.getClass() != key.getClass()) {
				continue;
			}

			if ((key instanceof Number || key instanceof String) && key.equals(k)) {
				return e.value;
			}
		}
		return null;
	}

	public V put(K key, V value) {
		// int hash = hash(key.hashCode());

		int hash = 0;
		if (key instanceof Number || key instanceof String) {
			hash = hash(key.hashCode());
		} else {
			hash = System.identityHashCode(key);
		}

		// int hash = hash(key.getClass().hashCode() + key.hashCode());
		int i = indexFor(hash, table.length);
		// modCount++;
		// 此处放入,K-V.key的Hash可能相同,但key的值一定不同
		addEntry(hash, key, value, i);
		return null;
	}

	void addEntry(int hash, K key, V value, int bucketIndex) {
		Entry<K, V> e = table[bucketIndex];
		// 放入头部
		table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
		if (size++ >= threshold)
			resize(2 * table.length);
	}

	void resize(int newCapacity) {
		Entry[] oldTable = table;
		int oldCapacity = oldTable.length;
		// if (oldCapacity == MAXIMUM_CAPACITY) {
		// threshold = Integer.MAX_VALUE;
		// return;
		// }
		Entry[] newTable = new Entry[newCapacity];
		transfer(newTable);
		table = newTable;
		// threshold = (int) (newCapacity * loadFactor);
		threshold = newCapacity;
	}

	void transfer(Entry[] newTable) {
		Entry[] src = table;
		int newCapacity = newTable.length;
		for (int j = 0; j < src.length; j++) {
			Entry<K, V> e = src[j];
			if (e != null) {
				src[j] = null;
				do {
					Entry<K, V> next = e.next;
					int i = indexFor(e.hash, newCapacity);
					e.next = newTable[i];
					newTable[i] = e;
					e = next;
				} while (e != null);
			}
		}
	}

	public void clear() {
		// modCount++;
		Entry[] tab = table;
		for (int i = 0; i < tab.length; i++)
			tab[i] = null;
		size = 0;
	}

	protected static class Entry<K, V> {
		final K		key;
		V			value;
		Entry<K, V>	next;
		final int	hash;

		Entry(int h, K k, V v, Entry<K, V> n) {
			value = v;
			next = n;
			key = k;
			hash = h;
		}

		// public final K getKey() {
		// return key;
		// }
		// public final V getValue() {
		// return value;
		// }
		// public final V setValue(V newValue) {
		// V oldValue = value;
		// value = newValue;
		// return oldValue;
		// }
	}
}
