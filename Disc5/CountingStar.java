import java.util.Arrays;

public class CountingStar {
	public static String[] starCount(String[] args) {
		int count = 0;
		for(String s: args) {
			if(s.equals("Star")) 
				count ++;
		}
		if (count % 2 == 0) {
			return Arrays.copyOf(args, args.length);
		} else {
			String[] res = new String[args.length - count];
			int i = 0;
			for(String s: args) {
				if(!s.equals("Star")) 
					res[i++] = s;
			}
			return res;
		}
	}

	public static void main(String[] args) {
		String[] strings = {"Star", "Why", "Stan", "Starr", "Tea", "ok"};
		for(String s: CountingStar.starCount(strings)) {
			System.out.print(s + " ");
		}
	}
}