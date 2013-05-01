package gCodeJam13;
/**
 * Code as is from participation in 2013 Qualification round.
 *  https://code.google.com/codejam/contest/2270488/dashboard#s=p1
 */
import java.math.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class lawn {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\gCodeJam13\\";
		String relRoot = "B-large.in";
//		String relRoot = "B-practice.in";
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
				line = r.readLine().split(" ");
				caseCount++;
				int[][] input = new int[Integer.parseInt(line[0])][Integer.parseInt(line[1])];
				
				for (int i = 0; i < input.length; i++) {
					line = r.readLine().split(" ");
					for (int j = 0; j < input[i].length; j++) {
						input[i][j] = Integer.parseInt(line[j]);
					}
				}
				
				printArray(input);
				String result = valid(input)?"YES":"NO";
				w.write("Case #" + caseCount + ": " + result);

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
	
	//If every square on the same column and row is 
	//taller than it than that lawn is not possible
	public static boolean valid(int[][] input) {
		int max;
		boolean row;
		boolean col;
		for(int i = 0; i < input.length; i++) {
			for(int j = 0; j < input[i].length; j++) {
				max = input[i][j];
				row = true;
				col = true;
				for(int m = 0; m < input[i].length; m++) {
					if(m == j) {continue;}
					if(input[i][m] > max) {
						row = false;
						break;
					}
				}
				for(int n = 0; n < input.length; n++) {
					if(n == i) {continue;}
					if(input[n][j] > max) {
						col = false;
						break;
					}
				}
				if(row == false && col == false) {
					return false;
				}
			}
		}
		return true;
	}
	public static void zero(int[][] a) {
		for(int i =0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				a[i][j] = 0;
			}
		}
	}
	public static void zero(char[][] a) {
		for(int i =0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				a[i][j] = '0';
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
	}
}