public class SList {
	private SNode head;
	public SList() {
		head = new SNode(0, null);
	}
	public void insert(double val, int pos) {
		insert(head, val, pos);
	}
	private void insert(SNode x, double val, int pos) {
		if(x.next == null) {
			x.next = new SNode(val, null);
		} else {
			if (pos == 0) {
				x.next = new SNode(val, x.next);
			} else {
				insert(x.next, val, pos-1);
			}
		}
	}
	public void printAll() {
		SNode tmp = head.next;
		while(tmp != null) {
			System.out.print(tmp.val);
			System.out.print(" ");
			tmp = tmp.next;
		}
		System.out.print("\n");
	}
}