

package vector;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Vector<T> implements SimpleList<T> {

	private T[] itemArray;

	private int size = 0; //keeps track of how many elements are actually stored in itemArray (non-null values)
	private static final int INITIAL_CAPACITY = 100;

	public Vector() {
		this(INITIAL_CAPACITY);  // calls the other constructor that takes an int parameter
	}
	
	public Vector(int capacity) {
		this.itemArray = (T[]) new Object[capacity];
		this.size = 0;
	}

	public int capacity() {
		return this.itemArray.length;
	}

	/**
	 * When the underlying array fills up, we need to somehow resize it to accommodate the
	 * Vectors's elements.
	 * Ignores the request if the requested new capacity is too small to fit the elements
	 * already in the Vector
	 */
	public void resize(int newCapacity) {
		// TODO: Implement this method

		if (newCapacity > size) { //ensure that the new size can incorporate the elements already in itemArray
			T[] temp = (T[]) new Object[newCapacity];
			for(int i = 0; i < size; i++) {
				temp[i] = this.itemArray[i];
			}

			this.itemArray = (T[]) new Object[newCapacity];

			for(int i = 0; i < size; i++) {
				this.itemArray[i] = temp[i];
			}
		}
	}


	@Override
	public int size() {
		// TODO: Implement this method

		int num = 0;
		for (int i = 0; i < this.itemArray.length; i++) { //figures out how many non-null values are in the array, same as size var
			if (this.itemArray[i] != null) {
				num++;
			}
		}
		return num;
	}

	@Override
	public void clear() { //sets each index to be null
		// TODO: Implement this method
		for (int i = 0; i < size; i++) {
			this.itemArray[i] = null;

		}
		size = 0;
	}

	@Override
	public void insertAtTail(T item) {
		// TODO: Implement this method
		this.insertAt(size, item); //uses insertAt to put the item in the last index
	}

	@Override
	public void insertAtHead(T item) {
		// TODO: Implement this method
		this.insertAt(0, item); //uses insertAt to put the item in the first index always
	}

	@Override
	public void insertAt(int index, T item) {
		// TODO: Implement this method
		if (index <= size && index >= 0) {

			//ensures the new array is big enough to hold everything
			if (size == capacity()) {
				this.resize(this.itemArray.length * 2);
			}

			//sets everything after the added object to the temporary array
			T[] temp = (T[]) new Object[this.itemArray.length];
			for (int i = index + 1; i < size + 1; i++) {
				temp[i] = this.itemArray[i-1];
			}

			temp[index] = item;

			//sets everything before the added object to the temporary array
			for (int i = 0; i < index; i++) {
				temp[i] = this.itemArray[i];
			}

			//now that the temp array is correct, set each element in itemArray to each corresponding temp element
			for (int i = 0; i < temp.length; i++) {
				this.itemArray[i] = temp[i];
			}

			size++;
		}
	}

	@Override
	public T removeAtTail() { //removes the last array element
		// TODO: Implement this method
		if (this.itemArray != null && size == this.itemArray.length) { //checks if every element in the array is non-null
			T[] temp = (T[]) new Object[this.itemArray.length];
			T returnVal = (T) new Object();

			int indexOfRemoved = this.itemArray.length - 1;
			returnVal = this.itemArray[indexOfRemoved];

			for (int i = 0; i < indexOfRemoved; i++) {
				temp[i] = this.itemArray[i];
			}

			for (int i = 0; i < temp.length; i++) {
				this.itemArray[i] = temp[i];
			}
			size--;
			return returnVal;
		} else {
			int indexOfRemoved = -1;
			T returnVal = (T) new Object();
			T[] temp = (T[]) new Object[this.itemArray.length];

			for (int i = 0; i < this.itemArray.length; i++) {

				if (this.itemArray[i] != null) {
					indexOfRemoved = i;
				}
			}

			returnVal = this.itemArray[indexOfRemoved];
			for (int i = 0; i < indexOfRemoved; i++) {
				temp[i] = this.itemArray[i];
			}

			for (int i = 0; i < temp.length; i++) {
				this.itemArray[i] = temp[i];
			}
			size--;
			return returnVal;
		}
	}

	@Override
	public T removeAtHead() { //removes the first array element
		// TODO: Implement this method
		if (this.itemArray != null && size == this.itemArray.length) {

			T returnVal = (T) new Object();
			returnVal = this.itemArray[0];

			T[] temp = (T[]) new Object[this.itemArray.length];
			for (int i = 1; i < this.itemArray.length; i++) {
				temp[i-1] = this.itemArray[i];
			}
			for (int i = 0; i < temp.length; i++) {
				this.itemArray[i] = temp[i];
			}
			size--;
			return returnVal;
		} else {
			T returnVal = (T) new Object();
			returnVal = this.itemArray[0];

			T[] temp = (T[]) new Object[this.itemArray.length];
			for (int i = 1; i < this.itemArray.length; i++) {
				temp[i-1] = this.itemArray[i];
			}
			for (int i = 0; i < temp.length; i++) {
				this.itemArray[i] = temp[i];
			}
			size--;
			return returnVal;
		}

	}

	@Override
	public int find(T item) { //search for an item by traversing the array
		// TODO: Implement this method
		for (int i = 0; i < size; i++) {
			if (this.itemArray[i].equals(item)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public T get(int index) { //accesses item at a specific index
		// TODO: Implement this method
		if (index < this.itemArray.length) {
			return this.itemArray[index];
		}
		return this.itemArray[index];
	}

	/*
	 * This toString() method allow you to print a nicely formatted version of your Vector. E.g.
	 *     System.out.println( myVector );
	 * It uses utility methods in the Java Arrays library.
	 */
	@Override
	public String toString() {
//		return Arrays.toString(this.itemArray); // prints entire array from 0 to capacity-1
		return Arrays.toString(Arrays.copyOfRange(this.itemArray, 0, this.size)); // prints from 0 to size-1
	}

}
