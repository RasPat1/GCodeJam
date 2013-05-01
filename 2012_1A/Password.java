/**
 * 
 * @author RasPat
 *Problem can be found here:
 * https://code.google.com/codejam/contest/1645485/dashboard#s=p0
 * Simple Solution: 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Password {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2012_1A\\";
		;
		String relRoot = "A-large-practice.in";
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
			// First line of Input is the # of test cases
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;

			while (caseCount < testCases) {
				caseCount++;
				line = r.readLine().split(" ");
				int lettersTyped = Integer.parseInt(line[0]);
				int passLength = Integer.parseInt(line[1]);
				line = r.readLine().split(" ");
				double[] letterProb = new double[lettersTyped];

				for (int i = 0; i < letterProb.length; i++) {
					letterProb[i] = Double.parseDouble(line[i]);
				}

				double expectedStrokesMin = enter(passLength, letterProb);

				for (int i = 1; i < letterProb.length; i++) {
					double backExp = backspace(passLength, letterProb, i);
					expectedStrokesMin = backExp < expectedStrokesMin ? backExp
							: expectedStrokesMin;
				}
				double keepExp = keep(passLength, letterProb);
				double result = keepExp < expectedStrokesMin ? keepExp
						: expectedStrokesMin;

				w.write("Case #" + caseCount + ": "
						+ String.format("%.6f", result));
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

	/**
	 * returns the expected value for if you continue typing and press enter
	 * takes the letterProb, passwordLength,
	 * 
	 * @return
	 */
	public static double keep(int passLength, double[] letterProb) {
		double rightProb = 1;
		// add one for enter
		int remainingStrokesIfRight = passLength - letterProb.length + 1;
		int remainingStrokesIfWrong = remainingStrokesIfRight + passLength + 1;
		double expectedStrokes = 0;
		for (double d : letterProb) {
			rightProb *= d;
		}
		expectedStrokes = remainingStrokesIfRight * rightProb;
		expectedStrokes += (1 - rightProb) * remainingStrokesIfWrong;

		return expectedStrokes;
	}

	/**
	 * returns the expected value with given number of backspaces
	 * 
	 * @return
	 */
	public static double backspace(int passLength, double[] letterProb,
			int numBackspaces) {
		double rightProb = 1;
		// any character that is erased must be typed again hence numBackspaces
		// * 2
		int remainingStrokesIfRight = passLength - letterProb.length + 1
				+ numBackspaces * 2;
		int remainingStrokesIfWrong = remainingStrokesIfRight + passLength + 1;
		double expectedStrokes = 0;
		for (int i = 0; i < letterProb.length - numBackspaces; i++) {
			rightProb *= letterProb[i];
		}
		expectedStrokes = remainingStrokesIfRight * rightProb;
		expectedStrokes += remainingStrokesIfWrong * (1 - rightProb);

		return expectedStrokes;
	}

	/**
	 * 
	 * @return
	 */
	public static double enter(int passLength, double[] letterProb) {
		double rightProb = 1;
		double expectedStrokes = 0;
		for (int i = 0; i < letterProb.length; i++) {
			rightProb *= letterProb[i];
		}
		if (letterProb.length == passLength) {
			expectedStrokes = 1 * rightProb;
			expectedStrokes += (passLength + 2) * (1 - rightProb);
		} else {
			expectedStrokes = 2 + passLength;
		}
		// one stroke for first enter one for second enter if wrong
		//

		return expectedStrokes;
	}
}
