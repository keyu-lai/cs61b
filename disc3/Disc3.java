public class Disc3 {
	public static void main(String[] args) {
		// Q1
		int[] x = new int[]{12, 45, 7345, 23, 123, 76, 14};
		for(int i: x) {
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.print("\n");
		x = insert(x, 9, 4);
		for(int i: x) {
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.print("\n");
		// Q2
		SList l = new SList();
		l.insert(34, 100);
		l.insert(28, 100);
		l.insert(74, 100);
		l.insert(54, 100);
		l.insert(3, 100);
		l.printAll();
		l.insert(9, 3);
		l.printAll();
		// Q3
		SentinelSList sl = new SentinelSList();
		sl.insert(34, 100);
		sl.insert(28, 100);
		sl.insert(74, 100);
		sl.insert(54, 100);
		sl.insert(3, 100);
		sl.printAll();
		sl.insert(9, 3);
		sl.printAll();
		// Challenge Problem
		x = xify(new int[]{4, 3, 2, 1});
		for(int i: x) {
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.print("\n");

	}
	public static int[] insert(int[] x, int val, int position) {
		int[] res = new int[x.length+1];
		for(int i = 0; i < position; i++) {
			res[i] = x[i];
		}
		res[position] = val;
		for(int i = position+1; i < x.length+1; i++) {
			res[i] = x[i-1];
		}
		return res;
	}
	public static int[] xify(int[] x) {
		int sum = 0;
		for(int i: x) {
			sum += i;
		}
		int[] res = new int[sum];
		int index = 0;
		for(int i = 0; i < x.length; ++i) {
			int num = x[i];
			while(num-- > 0) {
				res[index++] = x[i];
			}
		}
		return res;
	}
}