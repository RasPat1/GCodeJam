package gCodeJam13;
/**
 * Credit to Faruk Akgul.  Used his version of sqrt for the BigInteger class.
 * That's legal right?  his code's too good.  Feels like cheating.  Will try it another way.
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

public class palindroneBigInt {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\gCodeJam13\\";
		String relRoot = "C-large-1.in";
//		String relRoot = "C-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "1out";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String[] line;
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;
			BigInteger lower;
			BigInteger higher;
			BigInteger lowerSqrt;
			BigInteger higherSqrt;
			long palCount = 0;
			int lowDigitCount = 0;
			int highDigitCount = 0;
			while (caseCount < testCases) {
				line = r.readLine().split(" ");
				caseCount++;
//				lower = Integer.parseInt(line[0]);
//				higher = Integer.parseInt(line[1]);
//				lowerSqrt = (int) Math.sqrt(lower) - 1;
//				higherSqrt = (int) Math.sqrt(higher) + 1;
				
				lower = new BigInteger(line[0]);
				higher = new BigInteger(line[1]);
				lowDigitCount = line[0].length(); 
				highDigitCount = line[1].length(); 
//				lowerSqrt = BigInteger.TEN.pow((int) (lowDigitCount / 2));
//				higherSqrt = BigInteger.TEN.pow((int) (highDigitCount / 2 + 1));
				lowerSqrt = sqrt(lower);//BigInteger.TEN.pow((int) (lowDigitCount / 2));
				higherSqrt = sqrt(higher);//BigInteger.TEN.pow((int) (highDigitCount / 2 + 1));	
				palCount = 0;
				BigInteger x;
				for(BigInteger i = lowerSqrt; i.compareTo(higherSqrt) <= 0; i = i.add(BigInteger.ONE)) {
					if(isPal(i) && isPal(x = i.pow(2)) && x.compareTo(lower) >=0 && x.compareTo(higher) <=0) {
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
			System.out.print((System.currentTimeMillis() - start)/60000);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static boolean isPal(int p) {
		
		String s = Integer.toString(p);
		
		for(int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	public static boolean isPal(BigInteger p) {
		
		String s = p.toString();
		
		for(int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
	  public static BigInteger sqrt(BigInteger n) {
		    BigInteger a = BigInteger.ONE;
		    BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		    while(b.compareTo(a) >= 0) {
		      BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
		      if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
		      else a = mid.add(BigInteger.ONE);
		    }
		    return a.subtract(BigInteger.ONE);
		  }
}