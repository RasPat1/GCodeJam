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

public class Round1AP1 {

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
		String out = in.substring(0, in.length() - extLength) + "out";
		String test = pathRoot + "test.in";
		String testo = pathRoot + "test.out1";
		BufferedReader r = null;
		BufferedWriter w = null;
		BufferedWriter answers = null;
		BufferedWriter samples = null;
		
		int times = 120;
	
		//Generate a bunch of these permutation guys;
		samples = new BufferedWriter(new FileWriter(test));
		answers = new BufferedWriter(new FileWriter(testo));
		samples.write("120");
		samples.newLine();
		for (int i = 0; i < times; i++) {
			samples.write("1000");
			samples.newLine();
			answers.write("Case #" + String.valueOf(i + 1) + ": ");

			int[] vals = new int[1000];
			int who = (int)(Math.random() * 2);
			if (who >= 1) {
				vals = good();
				show(vals, samples);
				answers.write("GOOD");
			} else {
				vals = bad();
				show(vals, samples);
				answers.write("BAD");
			}
			answers.newLine();
		}

		answers.close();
		samples.close();
		
		w = new BufferedWriter(new FileWriter(out));

		String line;
		int caseCount = 0;
		r = new BufferedReader(new FileReader(in));
		int testCases = Integer.parseInt(r.readLine()); // number of test cases
//		int[] sols = new int[testCases];

		while((line = r.readLine()) != null) {
			line = r.readLine();
			int[] vals = new int[1000];
			String[] inS = line.split(" ");
			for (int i = 0; i < 1000; i++) {
				vals[i] = Integer.parseInt(inS[i]);
			}
					
			w.write("Case #" + String.valueOf(caseCount + 1) + ": ");
			String outcome = "";
			
			int score = diffCalc2(vals);
			
			if (score < (472 + 500) / 2) {
				outcome = "BAD";
//				sols[caseCount] = 0;
			} else {
				outcome = "GOOD";
//				sols[caseCount] = 1;
			}
			
			
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
	
	public static int diffCalc2(int[] vals) {
		int score = 0;
		for(int i = 0; i < vals.length; i++) {
			if (vals[i] <= i) {
				score++;
			}
		}
		return score;
	}
	/*
	 * Take the magnitude of the distance form teh averages
	 * take the average of that
	 * on the good the average distance from the averages should be what??
	 * Run a test on some good lists and see what happens
	 * 
	 * On teh bad the distance will eb whatever
	 * 
	 * When given a test set Find the magnitude of difference form teh average
	 * Then find teh distance of the magnitude of diff from teh bad averages
	 * 
	 * The average difference of the average difference third central moment?
	 * 
	 * I guess we can do this chcek the mean(hint itll be 500), stdev, 3rd moment for the good and bad distributions
	 * 
	 * Then when we get a new set check the vars and see fi they are closer to the good or bad?
	 * 
	 * 
	 */
//	public static int diffCalc2(int[] vals) {
//		for (int i = 0; i < vals.length; i++) {
//			
//		}
//		
//		
//		return null;
//	}
	
	public static double getStdev(int[] vals, double[] means) {
		double stdev = 0;
		int len = vals.length;
		for (int i = 0; i < len; i++) {
			stdev += Math.pow((vals[i] - means[i]), 2);
		}
		stdev = Math.sqrt(stdev / len);
		return stdev;
	}
	
	public static double stdev2(int[] vals) {
		double stdev = 0;
		int len = vals.length;
		for (int i = 0; i < len; i++) {
			stdev = Math.pow((vals[i] - 500), 2) / len;
			
		}
		return stdev;
	}
	
	/*
	 * Create a bad algo n times and see what the average
	 * values at each index are.  In teh good they should be 500.
	 * Used to determine how far any index actually shifts from the bias
	 */
	public static double[] getMeans(int times, boolean useGood) {
		long[] avgArray = new long[1000];
		double[] diffArray = new double[1000];
		int[] vals = new int[1000];
		Arrays.fill(avgArray, 0L);
		for(int i = 0; i < times; i++) {
			if (useGood) {
				vals = good();
			} else {
				vals = bad();
			}
			for (int j = 0; j < vals.length; j++) {
				avgArray[j] += vals[j];
			}
		}
		for(int i = 0; i < avgArray.length; i++) {
			diffArray[i] = (avgArray[i] / times);
		}
		return diffArray;
//		System.out.println(Arrays.toString(diffArray));
	}
	
	
	public static int[] genList(BufferedWriter w1) throws IOException {
		int who = (int)(Math.random() * 2);
		int[] vals = new int[1000];
		if (who >= 1) {
			vals = good();
			w1.write("GOOD");
		} else {
			vals = bad();
			w1.write("BAD");
		}
		w1.newLine();
		return vals;
	}
	public static int[] good() {
		int[] vals = new int[1000];
		for (int k = 0; k < 1000; k++) {
			vals[k] = k;
		}
		for (int k = 0; k < 1000; k++) {
			int p = k + (int)(Math.random() * (1000-k));
			int tmp = vals[k];
			vals[k] = vals[p];
			vals[p] = tmp;
		}
		
		return vals;
	}
	
	public static int[] bad() {
		int[] vals = new int[1000];
		for (int k = 0; k < 1000; k++) {
			vals[k] = k;
		}
		for (int k = 0; k < 1000; k++) {
			int p = (int)(Math.random() * 1000);
			int tmp = vals[k];
			vals[k] = vals[p];
			vals[p] = tmp;
		}
		
		return vals;	}
	
	public static int[] rand() {
		int[] r = new int[1000];
		for (int i = 0; i < r.length; i++) {
			r[i] = (int)(Math.random() * r.length);
		}
		return r;
	}
	
	public static void show(int[] vals, BufferedWriter w) throws IOException {
		String v = Arrays.toString(vals);
		v = v.replace(",", "");
		v = v.replace("[", "");
		v = v.replace("]", "");

		w.write(v);
		w.newLine();
	}
	
}
