package gCodeJam13;
/**
 * @author RasPat
 * Problem:
 * https://code.google.com/codejam/contest/2270488/dashboard#s=p3
 */
import java.math.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class treasure {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\gCodeJam13\\";
//		String relRoot = "D-small-attempt0.in";
		String relRoot = "D-practice.in";
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
			int maxTypes = 20;
			int numStartKeys;
			int chestsToOpen;
			
			while (caseCount < testCases) {
				line = r.readLine().split(" ");
				caseCount++;
				
				numStartKeys = Integer.parseInt(line[0]);
				chestsToOpen = Integer.parseInt(line[1]);
				
				int[] startKeyTypes = new int[maxTypes];
				line = r.readLine().split(" ");
//				for(int i = 0; i < line.length; i++) {
//					startKeyTypes[i] = Integer.parseInt(line[i]);
//				}
				for(int i = 0; i < line.length; i++) {
					startKeyTypes[Integer.parseInt(line[i]) - 1]++;
				}
				//Each row is a chest
				//col1 = Key type needed col2 = number of Keys col3 = array of keys in chest
				int[][] chests = new int[chestsToOpen][];
				
				//copy in each chest
				for(int i = 0; i < chests.length; i++) {
					line = r.readLine().split(" ");
					//or could just write line.length
					chests[i] = new int[line.length];
					for(int j = 0; j < line.length; j++) {
						chests[i][j] = Integer.parseInt(line[j]);
					}
				}
				printArray(chests);
				printArray(startKeyTypes);
				//solve the problem now
				
				
				
				
				
				
				
				w.write("Case #" + caseCount + ": ");

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
				System.out.println("Run Time: " + (System.currentTimeMillis() - start )/1000);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
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
	
	public static void printArray(int[] a) {
		System.out.println();
		for(int i =0; i < a.length; i++) {
				System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}