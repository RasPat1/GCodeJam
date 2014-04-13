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

import java.util.HashSet;
import java.util.Set;
public class Magic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String pathRoot = "/Users/rasesh/Documents/workspace/GCodeJam/src/";
		String relRoot = "A-small-attempt0.in";
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
			int[] guess = new int[2];
			int[][][] placement = new int[2][4][4];
			
			while(caseCount <= testCases) {
				String outcome = "";
				//Read in the data
				for (int a = 0; a < 2; a++) {
					guess[a] = Integer.parseInt(r.readLine()) -1;

					for (int i = 0; i < 4; i++) {
						String[] l = r.readLine().split(" ");
						for(int j = 0; j < 4; j++) {
							placement[a][i][j] = Integer.parseInt(l[j]);
						}
					}
				}
				int[][] possible = {placement[0][guess[0]], placement[1][guess[1]]};
				int dupes = -1;
				Set<Integer> d = new HashSet<Integer>();
				int cardCount = 0;
				int cardValue = 0;
				for (int i = 0; i < possible.length; i++) {
					for (int j = 0; j < possible[0].length; j++) {
						boolean isDup = !d.add(possible[i][j]);
						cardCount += (isDup)? 1 : 0;
						if(isDup) {
							cardValue = possible[i][j];
						}
					}
				}
				if(cardCount == 0) {
					outcome = "Volunteer cheated!"; 
				} else if(cardCount == 1) {
					outcome = Integer.toString(cardValue);
				} else {
					outcome = "Bad magician!";
				}
				
				
				w.write("Case #" + caseCount + ": " + outcome + "\n");
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
