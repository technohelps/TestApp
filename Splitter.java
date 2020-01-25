import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Splitter {

	public static void main(String args[]) throws IOException {
		File file = new File("C:\\Users\\Smart\\workspace\\SplitterApp\\bci.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String bci = br.readLine();

		System.out.println(bci);
		System.out.println("---------------------------------------------------");
		String[] terms = bci.split("");

		String e = null;
		String g = null;
		String s = null;
		String l = null;
		HashMap<String, String> done = new HashMap();

		System.out.println(terms.length);
		for (String code : terms) {
			System.out.println(code);
			if (terms.length == 2) {
				if (code.startsWith("01")) {
					g = code.substring(2, 16);
					e = code.substring(18, 24);
					String newCode = code.substring(24, 26);
					if (newCode.startsWith("10")) {
						l = code.substring(26, code.length());
					} else if (newCode.startsWith("21")) {
						s = code.substring(26, code.length());
					}
				} else if (code.startsWith("17")) {
					e = code.substring(2, 7);
					g = code.substring(9, 24);
					String newCode = code.substring(24, 26);
					if (newCode.startsWith("10")) {
						l = code.substring(26, code.length());
					} else if (newCode.startsWith("21")) {
						s = code.substring(26, code.length());
					}
				} else if (code.startsWith("10")) {
					l = code.substring(2, code.length());
				} else if (code.startsWith("21")) {
					s = code.substring(2, code.length());
				}
			} /// length == 2
			else if (terms.length == 3) {
				if (code.startsWith("01")) {
					g = code.substring(2, 16);
					if (code.length() > 16) {
						String newCode = code.substring(16, 18);
						if (newCode.startsWith("10")) {
							l = code.substring(18, code.length());
						} else if (newCode.startsWith("21")) {
							s = code.substring(18, code.length());
						}
					}
				} else if (code.startsWith("17")) {
					e = code.substring(2, 8);
					if (code.length() > 8) {
						String newCode = code.substring(8, 10);
						if (newCode.startsWith("10")) {
							l = code.substring(10, code.length());
						} else if (newCode.startsWith("21")) {
							s = code.substring(10, code.length());
						}
					}
				} else if (code.startsWith("10")) {
					l = code.substring(2, code.length());
				} else if (code.startsWith("21")) {
					s = code.substring(2, code.length());
				}
			}

		}
		System.out.println("---------------------------------------------------");
		System.out.println("g :" + g);
		System.out.println("e :" + e);
		System.out.println("s :" + s);
		System.out.println("l :" + l);
	}
}
