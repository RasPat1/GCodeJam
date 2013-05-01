
/**
 * @author RasPat
 * Problem can be found here:
 * https://code.google.com/codejam/contest/32016/dashboard#s=p0
 * Solution: multiply the largest from one vector with the smallest from the other
 */

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MinScalarProduct {

	public static void main(String[] args) {
		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2008_1A\\";
		String relRoot = "A-large-practice.in";
		// String relRoot = "A-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "out";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String[] line;
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;

			while (caseCount < testCases) {
				caseCount++;
				w.write("Case #" + caseCount + ": ");
				int vSize = Integer.parseInt(r.readLine());
				long[][] v = new long[2][vSize];
//				int[] v2 = new int[vSize];
				for(int j = 0; j < 2; j++) {
				line = r.readLine().split(" ");
					for(int i = 0; i < vSize; i++) {
						v[j][i] = Long.parseLong(line[i]);
					}
				}
				
				Arrays.sort(v[0]);
				Arrays.sort(v[1]);
				
				long minSum = 0;
				for(int i =0; i < v[1].length; i++) {
					minSum += v[0][i] * v[1][v[0].length - 1 - i];
				}

				w.write(Long.toString(minSum));
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