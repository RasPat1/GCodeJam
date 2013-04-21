package googleCodeJam;

/**
 *https://code.google.com/codejam/contest/90101/dashboard#s=p0	 
 *
 */
import java.math.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Alien {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\googleCodeJam\\QR2009\\";
		String relRoot = "A-large-practice.in";
		// String relRoot = "D-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "1out";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));

			String[] line = r.readLine().split(" ");
			int wordLength = Integer.parseInt(line[0]);
			int totalWords = Integer.parseInt((line[1]));
			int testCases = Integer.parseInt(line[2]);
			int caseCount = 0;
			String[] words = new String[totalWords];
			boolean[] candidates = new boolean[totalWords];
			for (int i = 0; i < words.length; i++) {
				words[i] = r.readLine();
			}

			while (caseCount < testCases) {
				caseCount++;
				truify(candidates);
				String crit = r.readLine();
				int i = 0;
				for (int curLetter = 0; curLetter < wordLength; curLetter++) {
					String match = "";
					if (crit.charAt(i) == '(') {
						i++;
						while (crit.charAt(i) != ')') {
							match += crit.charAt(i);
							i++;
						}
					} else {
						match = "" + crit.charAt(i);
					}
					// we now have the pattern to match at curLetter
					// can decide to run this only on words that are still valid
					// candidates
					for (int j = 0; j < words.length; j++) {
						boolean flag = false;
						for (int k = 0; k < match.length(); k++) {
							if (words[j].charAt(curLetter) == match.charAt(k)) {
								flag = true;
								break;
							}
						}
						// A word is a candidate only if it
						// was a candidate and if it matches this letter option
						candidates[j] = candidates[j] & flag;
					}
					i++;
				}

				int result = 0;

				for (boolean b : candidates) {
					if (b)
						result++;
				}

				w.write("Case #" + caseCount + ": " + result + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				if (w != null) {
					w.flush();
					w.close();
				}
				System.out.println((System.currentTimeMillis() - start) / 1000);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void truify(boolean[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = true;
		}
	}
}
