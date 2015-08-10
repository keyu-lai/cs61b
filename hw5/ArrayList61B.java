import java.util.AbstractList;

public class ArrayList61B<T> extends AbstractList<T> {
	protected T[] l;
	private int size;

	public ArrayList61B(int initialCapacity) {
		if(initialCapacity < 1)
			throw new IllegalArgumentException();
		l = (T[]) new Object[initialCapacity];
		size = 0;
	}

	public ArrayList61B() {
		this(1);
	}

	public T get(int i) {
		return l[i];
	}

	public boolean add(T item) {
		if(size == l.length)
			resize(2*size);
		l[size++] = item;
		return true;
	}

	private void resize(int n) {
		T[] nl = (T[]) new Object[n];
		for(int i = 0; i < l.length; ++i)
			nl[i] = l[i];
		l = nl;
		System.out.println(nl.length);
	}

	public int size() {
		return this.size;
	}
}