package googleCodeJam;

import java.math.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class googlerese {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\googleCodeJam\\";
		String relRoot = "A-small-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "out";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			char[] key = {'y','n', 'f', 'i', 'c', 'w', 'l','b',
					'k','u','o','m','x','s','e','v','z','p','d',
					'r','j','g','t','h','a','q'};
			char[] decrypt = {'y','h','e','s','o','c','v','x','d',
					'u','i','g','l','b','k','r','z','t','n','w','j',
					'p','f','m','a','q'};
			System.out.println((int)'a');
			String line;
			String testCases = r.readLine();
			int caseCount = 0;
			while ((line = r.readLine().trim()) != null) {
				caseCount++;
				System.out.println(line);
				w.write("Case #" + caseCount + ": ");
				int charInt;
				char realChar;
				for(int i = 0; i < line.length(); i++) {
					charInt = (int)line.charAt(i);
					if(charInt == 32) {
						w.write(' ');
					}
					else if(charInt >= 97 && charInt <= (97 + 26)) {
						realChar = decrypt[(charInt)- 97];
						w.write(realChar);
					} else {
						break;
					}
				}
				w.newLine();
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
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}