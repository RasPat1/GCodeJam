package googleCodeJam;

/**
 * https://code.google.com/codejam/contest/32016/dashboard#s=p1
 */
import java.math.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Milkshakes {

	public static void main(String[] args) {
		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2008_1A\\";
		String relRoot = "B-small-practice.in";
		// String relRoot = "B-practice.in";
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
				int[] milkshakes = new int[Integer.parseInt(r.readLine())];
				int numCustomers = Integer.parseInt(r.readLine());
				int[][] cusPref = new int[numCustomers][];
				
				//Read in Customer Preferences
				for(int i = 0; i < numCustomers; i++) {
					line = r.readLine().split(" ");
					cusPref[i] = new int[line.length];
					for(int j = 0; j < line.length; j++) {
						cusPref[i][j] = Integer.parseInt(line[j]);
					}
				}
				printArray(cusPref);
				//Sort the list of Customer preferences by # of milkshake preferences
				sortArray(cusPref);
				printArray(cusPref);
				
				if(caseCount == 2) {
					return;
				}
				
				//First defaultto making all unmalted milkshakes
				//Then find any individual who has only one desired milkshake type
				//That prefernece will be unfulfilled if he wants a malted milkshake
				//change that type of milkshake to malted
				//DO this for evryone who has only one milkshake preference
				//Then find the people whose preferences are now unfulfillled.
				//If all of their choices were changed to malts then the group in unfulfillable
				//If they have other preferences that are regular milkshakes they are fine
				//If they have one other preference and it is a mlated shake then that needs to be made 
				//as a malted shake
				//Then check everyone again and repeate the procedure
				//There shodul be an easier way to do this methinks
				
				zero(milkshakes);
				
				for(int[] c: cusPref) {
					boolean satisfied = false;
					for(int i = 1; i < c.length - 1; i+=2) {
						if(milkshakes[c[i]] == c[i+1] ) {
							satisfied = true;
						}
					}
					if(satisfied == false) {
						for(int i = 1; i < c.length -1; i+=2) {
							if(milkshakes[c[i]] == -1) {
								milkshakes[c[i]] = c[i+1];
							}
						}
					}
				}
				
				

//				w.write();
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
	public static void sortArray(int[][] a) {
		Arrays.sort(a, new java.util.Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});
	}
	public static void printArray(int[][] a) {
		for(int i =0; i < a.length; i++) {
			System.out.println();
			for(int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]);
			}
		}
		System.out.println();
	}
	
	public static void zero(int[] a) {
		for(int i = 0; i < a.length;i++) {
			a[i] =-1;
		}
	}
}
