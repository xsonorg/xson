package org.xson.core.buffer;

public class ByteArrayItem {

	private ByteArrayManager	manager;
	/** 是否需要回收 */
	private boolean				recycling;
	private int					recyclingIndex;
	private int					capacity;
	/** XSONByteArray中的索引 */
	private int					baIndex;
	/** 使用的长度 */
	private int					limit;
	private byte[]				buffer;

	protected ByteArrayItem(ByteArrayManager manager, byte[] buffer, int baIndex, boolean recycling, int recyclingIndex) {
		this.manager = manager;
		this.buffer = buffer;
		this.baIndex = baIndex;
		this.capacity = buffer.length;
		this.recyclingIndex = recyclingIndex;
	}

	protected byte[] getArray() {
		return buffer;
	}

	protected void recycle() {
		if (recycling) {
			manager.recycle(recyclingIndex);
		} else {
			buffer = null;
		}
		this.manager = null;
	}

	// public void setLimit(int currentIndex) {
	// this.limit = currentIndex - this.baIndex;
	// }
	// public int getRemainingCapacity(int currentIndex) {
	// return this.capacity - (currentIndex - this.baIndex);
	// }

	public void setLimit(int currentIndex) {
		this.limit = currentIndex;
	}

	public int getRemainingCapacity(int currentIndex) {
		return this.capacity - currentIndex;
	}

	public int getBaIndex() {
		return baIndex;
	}

	public int getLimit() {
		return limit;
	}
}
