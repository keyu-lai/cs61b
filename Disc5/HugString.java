public class HugString {
	public static CNode makeHugString(String s) {
		if(s.length() > 0) {
			CNode head = new CNode(s.charAt(0), null), runner = head;
			for(int i = 1; i < s.length(); ++i) {
				runner.next = new CNode(s.charAt(i), null);
				runner = runner.next;
			}
			return head;
		} else
			return null;
	}
	public static void swapSpace(CNode in) {
		CNode runner = in;
		while(runner != null) {
			if(runner.head == ' ') {
				runner.head = '6';
				runner.next = new CNode('1', new CNode('B', runner.next));
			}
			runner = runner.next;
		}
	}
	public static void printAll(CNode head) {
		while(head != null) {
			System.out.print(head.head);
			head = head.next;
		}
		System.out.print('\n');
	}
	public static void main(String args[]) {
		String s = "cann't take my eyes off you";
		CNode head = HugString.makeHugString(s);
		HugString.swapSpace(head);
		HugString.printAll(head);
	}
}