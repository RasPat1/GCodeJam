
/**
 * @author RasPat
 * The problem can be found here:
 * https://code.google.com/codejam/contest/351101/dashboard#s=p2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reverse {

	public static void main(String[] args) {
		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2010_QRAfrica\\";
		String relRoot = "C-large-practice.in";
		// String relRoot = "D-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "out";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String line;
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;

			while (caseCount < testCases) {
				caseCount++;
				w.write("Case #" + caseCount + ": ");
				line = r.readLine();

				String[] key = { "2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55", "555",
						"6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999",
						"9999" };

				String output = "";
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == ' ') {
						output += "0"; 
						if(i != line.length() - 1 && line.charAt(i+1) == ' ') {
							output += " ";
						}
					} 
					else if(i != line.length() -1 && 
							line.charAt(i+1) != ' ' &&
							key[line.charAt(i) - 97].charAt(0) == key[line.charAt(i+1) - 97].charAt(0)) {
						output += key[line.charAt(i) - 97] + " ";
					}
					else {
						output += key[line.charAt(i) - 97];
					}
				}

				w.write(output);
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
