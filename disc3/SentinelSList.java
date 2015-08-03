public class SentinelSList {
	private SNode front;
	private SNode back;
	public SentinelSList() {
		back = new SNode(0, null);
		front = new SNode(0, back);
	}
	public void insert(double val, int pos) {
		insert(front, val, pos);
	}
	private void insert(SNode x, double val, int pos) {
		if(x.next == back) {
			x.next = new SNode(val, back);
		} else {
			if (pos == 0) {
				x.next = new SNode(val, x.next);
			} else {
				insert(x.next, val, pos-1);
			}
		}
	}
	public void printAll() {
		SNode tmp = front.next;
		while(tmp != back) {
			System.out.print(tmp.val);
			System.out.print(" ");
			tmp = tmp.next;
		}
		System.out.print("\n");
	}
}