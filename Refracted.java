import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Refracted {

	public static void main(String args[]) throws IOException {
		File file = new File("C:\\Users\\Smart\\workspace\\SplitterApp\\barcode.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String barcode = br.readLine();
		br.close();

		System.out.println("---------------------------------------------------");
		System.out.println(barcode);
		System.out.println("---------------------------------------------------");

		String[] terms = barcode.split(Character.toString((char) 29));

		String gtinRegex = "(?<=01)(\\d{14})((?=21)|(?=17)|(?=10)|($))";
		String expDateRegex = "(?<=17)(\\d{6})((?=21)|(?=01)|(?=10)|($))";
		String snRegex = "(?<=21)(\\d{1,14})($)";
		String lotNoRegex = "(?<=10)([a-zA-Z0-9]{1,14}$)";

		Pattern gtinPattern = Pattern.compile(gtinRegex);
		Pattern expDatePattern = Pattern.compile(expDateRegex);
		Pattern snPattern = Pattern.compile(snRegex);
		Pattern lotNoPattern = Pattern.compile(lotNoRegex);

		String expDate = null;
		String gtin = null;
		String sn = null;
		String lotNo = null;

		for (String code : terms) {
			Matcher lotNoMatcher = lotNoPattern.matcher(code);
			Matcher gtinMatcher = gtinPattern.matcher(code);
			Matcher expDateMatcher = expDatePattern.matcher(code);
			Matcher snMatcher = snPattern.matcher(code);

			if (gtinMatcher.find()) {
				gtin = getMatchedRecord(code, gtinMatcher);
				String newCode = code.substring(gtinMatcher.end(), code.length());
				snMatcher = snPattern.matcher(newCode);
				lotNoMatcher = lotNoPattern.matcher(newCode);
				if (snMatcher.find()) {
					sn = getMatchedRecord(newCode, snMatcher);
				} else if (lotNoMatcher.find()) {
					lotNo = getMatchedRecord(newCode, lotNoMatcher);
				}
			}
			if (expDateMatcher.find()) {
				expDate = getMatchedRecord(code, expDateMatcher);
				String newCode = code.substring(expDateMatcher.end(), code.length());
				snMatcher = snPattern.matcher(newCode);
				lotNoMatcher = lotNoPattern.matcher(newCode);
				if (snMatcher.find()) {
					sn = getMatchedRecord(newCode, snMatcher);
				} else if (lotNoMatcher.find()) {
					lotNo = getMatchedRecord(newCode, lotNoMatcher);
				}
			}

			if (lotNoMatcher.find()) {
				lotNo = getMatchedRecord(code, lotNoMatcher);
			}

			if (snMatcher.find()) {
				sn = getMatchedRecord(code, snMatcher);
			}
		}

		System.out.println("---------------------------------------------------");
		System.out.println("gtin :" + gtin);
		System.out.println("expDate :" + expDate);
		System.out.println("sn :" + sn);
		System.out.println("lotNo :" + lotNo);
		System.out.println("---------------------------------------------------");
	}

	private static String getMatchedRecord(String input, Matcher matcher) {
		return input.substring(matcher.start(), matcher.end());
	}
}
