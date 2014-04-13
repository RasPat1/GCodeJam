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

public class Cookie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String pathRoot = "/Users/rasesh/Documents/workspace/GCodeJam/src/";
		String relRoot = "B-large.in";
		String in = pathRoot + relRoot;
		//Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength)+"out";
		
		BufferedReader r = null;
		BufferedWriter w = null;
		
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String line;
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 1;
			

			while(caseCount <= testCases) {
			
				//Read in the data
				String[] l = r.readLine().split(" ");
				double C = Double.valueOf(l[0]);
				double F = Double.valueOf(l[1]);
				double X = Double.valueOf(l[2]);
								
				int depth = 500000;
				double[] nTime = new double[depth];
				double[] nRate = new double[depth];
				double[] eqn = new double[depth];
				double[] memoTime = new double[depth];
				for(int i = 0; i < depth; i++) {
					nTime[i] = C / (2 + i * F);
					nRate[i] = 2 + i * F;
					if(i == 0) {
						memoTime[i] = 0;
					} else {
						memoTime[i] = memoTime[i-1] + nTime[i - 1];
					}
				}
				double minTime = Double.MAX_VALUE;
				for(int i = 0; i < depth -1; i++) {
					eqn[i] = X / nRate[i] + memoTime[i];
					minTime = (eqn[i] < minTime) ? eqn[i] : minTime;
				}

							
				w.write("Case #" + caseCount + ": " + minTime + "\n");
				caseCount++;
			}
		}
		catch (IOException e){
			e.printStackTrace();
		} 
		finally {
			try{ 
				r.close();
				if(w != null) {
					w.flush();
					w.close();
				}
			} catch (IOException e){
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
}
