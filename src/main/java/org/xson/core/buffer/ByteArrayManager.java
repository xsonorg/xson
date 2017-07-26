package org.xson.core.buffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.xson.core.XsonException;

public class ByteArrayManager {

	private List<byte[]>		mem;
	private LinkedList<Integer>	markup;
	private final ReentrantLock	lock;
	private int					capacity;
	private int					number;

	public int getCapacity() {
		return capacity;
	}

	public ByteArrayManager(int number, int capacity) {
		this.capacity = capacity;
		this.number = number;
		mem = new ArrayList<byte[]>();
		markup = new LinkedList<Integer>();
		lock = new ReentrantLock();
		for (int i = 0; i < number; i++) {
			markup.add(i);
			mem.add(new byte[capacity]);
		}
	}

	public ByteArrayItem allocate(int baIndex) {
		return allocate(0, baIndex);
	}

	public ByteArrayItem allocate(int requiredCapacity, int baIndex) {
		Integer index = null;
		lock.lock();
		try {
			index = markup.poll();
		} finally {
			lock.unlock();
		}
		if (null != index) {
			return new ByteArrayItem(this, mem.get(index), baIndex, true, index);
		} else {
			// if (0 == requiredCapacity) {
			// return new ByteArrayItem(this, new byte[this.capacity], baIndex, false, -1);
			// } else if (requiredCapacity < this.capacity) {
			// return new ByteArrayItem(this, new byte[this.capacity], baIndex, false, -1);
			// } else {
			// return new ByteArrayItem(this, new byte[requiredCapacity], baIndex, false, -1);
			// }

			if (requiredCapacity <= this.capacity) {
				return new ByteArrayItem(this, new byte[this.capacity], baIndex, false, -1);
			} else {
				return new ByteArrayItem(this, new byte[requiredCapacity], baIndex, false, -1);
			}
		}
	}

	public void recycle(int index) {
		lock.lock();
		try {
			if (index >= number) {
				throw new XsonException("The retrieved index is invalid: " + index);
			}
			markup.add(index);
		} finally {
			lock.unlock();
		}
	}

}
