package gCodeJam13;
/**
 * 
 * https://code.google.com/codejam/contest/2270488/dashboard#s=p2
 */
import java.math.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
public class palindrone {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\gCodeJam13\\";
		String relRoot = "C-large-1.in";
//		String relRoot = "C-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "intout";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String[] line;
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;
			long lower;
			long higher;
			long lowerSqrt;
			long higherSqrt;
			int palCount = 0;
			int lowDigitCount = 0;
			int highDigitCount = 0;
			while (caseCount < testCases) {
				line = r.readLine().split(" ");
				caseCount++;
			
				lower = Long.parseLong(line[0]);
				higher = Long.parseLong(line[1]);
				lowerSqrt = (long) Math.sqrt(lower) - 1;
				higherSqrt = (long) Math.sqrt(higher) + 1;
//				lower = BigInteger(line[0]);
//				higher = BigInteger(line[1]);
//				lowerSqrt = lower.pow(.5);
//				higherSqrt = (int) Math.sqrt(higher);
				palCount = 0;
				long x;
				for(long i = lowerSqrt; i <= higherSqrt; i++) {
					if(isPal(i) && isPal(x = (long) Math.pow(i, 2)) && x >=lower && x <=higher) {
						palCount++;
					}
				}
				
				w.write("Case #" + caseCount + ": " + palCount);

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
				System.out.println((System.currentTimeMillis() - start )/1000);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static boolean isPal(long p) {
		
		String s = Long.toString(p);
		
		for(int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}