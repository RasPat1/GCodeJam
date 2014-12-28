package gcj1A;

/**
 * 
 * @author RasPat
 *This is a skeleton for basic read write operations
 *Make this template threaded
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Elf {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String pathRoot = "C:\\Users\\RasPat\\Dev\\GCodeJam\\2014_1A\\";
		String relRoot = "test.in";
		String in = pathRoot + relRoot;
		//Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength)+"out";
		String out1 = in.substring(0, in.length() - extLength)+"out1";
		String test = pathRoot + "test.in";
		BufferedReader r = null;
		BufferedWriter w = null;
		
		w = new BufferedWriter(new FileWriter(out));

			String line;
			int caseCount = 0;
			r = new BufferedReader(new FileReader(in));
			int testCases = Integer.parseInt(r.readLine()); // number of test cases

			while((line = r.readLine()) != null) {
				line = r.readLine();
				int[] vals = new int[1000];
				String[] inS = line.split(" ");
				for (int i = 0; i < 1000; i++) {
					vals[i] = Integer.parseInt(inS[i]);
				}
						
				int diff = diffCalc(vals); // Overall movement should be zero;
				w.write("Case #" + String.valueOf(caseCount + 1) + ": ");
				String outcome = "";

				w.write(outcome);
				w.newLine();
				caseCount++;
			}

		r.close();
		
		if(w != null) {
			w.flush();
			w.close();
		}

	}
	
	public static int diffCalc(int[] vals) {
		int sum = 0;
		int len = vals.length;
		int halfLen = len / 2;
		for(int i = 0; i < len; i++) {
			int diff = i - vals[i];
			if (diff > halfLen) {
				diff = len - diff;
			} else if (diff < (-1 * halfLen)) {
				diff = len + diff;
				diff = diff * -1;
			}
			sum += diff;
		}
		return sum;
	}
	
	
}
