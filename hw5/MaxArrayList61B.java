import java.util.Comparator;

public class MaxArrayList61B<T extends Comparable<T>> extends ArrayList61B<T> {
	@Override
	public boolean add(T item) {
		for(int i = 0; i < l.length; ++i) {
			if(item.compareTo(get(i)) < 0)
				return false;
		} 
		return super.add(item);
	}
}