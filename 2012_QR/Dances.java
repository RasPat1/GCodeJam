/**
 * @author RasPat
 * Problem can be found here:
 * https://code.google.com/codejam/contest/1460488/dashboard#s=p1
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Dances {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2012_QR\\";
		String relRoot = "B-small-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "out";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String line;
			String testCases = r.readLine();
			int caseCount = 0;
			int dancers;
			int surprises;
			int bestResult;
			int[] results;
			while ((line = r.readLine()) != null) {
				String[] input = line.split(" ");
				for(String s : input) {
//					System.out.println(s);
				}
				
				//Input is of the form, Dancers,
				/**
				 * The first line of the input gives the number of test cases, 
				 * T. T test cases follow. Each test case consists of a single 
				 * line containing integers separated by single spaces. The first 
				 * integer will be N, the number of Googlers, and the second
				 * integer will be S, the number of surprising triplets of scores.
				 * The third integer will be p, as described above. Next will be 
				 * N integers ti: the total points of the Googlers.
				 */
				dancers = Integer.parseInt(input[0]);
				surprises = Integer.parseInt(input[1]);
				bestResult = Integer.parseInt(input[2]);
				results = new int[dancers];
				for(int i = 3; i < input.length; i++) {
					results[i - 3] = Integer.parseInt(input[i]);
					System.out.println(results[i-3]);
				}
//				System.out.println("this is the number of Dancers " +dancers );
//				System.out.println(input);
				caseCount++;
				System.out.println(line);
				int maxBest = maxBest(results, bestResult, surprises);
				System.out.println("maxxBest: " + maxBest);
				w.write("Case #" + caseCount + ": " + maxBest);
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
	public static int maxBest(int[] results, int bestResult, int surprises) {
		//Take the sum and subtract the desired best value
		//Is the difference between the bestResult-1*2 and bestResult+1*2
		//If not if the desired best is less than 10 increase by one and repeat this check
		//If it can be found then add one to the maxBest
			//If there are more than 0 surprises remaining start again but 
			//try bestResult -2 * 2 and bestResult +2 * 2
			//If it works then subtract one from the surprises and go to the next one
			//If still not don't worry about it skip it its not possible
		//If the diff is between 
		
		//If the number is >= bestResult*3 - 2 then add one to maxBest
		//else If the number is >= than bestResult*3 - 4 and surprises > 0 add one to maxBEst and remove oen form surprises
		
		int maxBest =0;
		
		for(int i: results) {
			if(i >= bestResult*3 - 2) {
				maxBest++;
			} else if(i >= bestResult*3 - 4 && surprises >0) {
				maxBest++;
				surprises--;
			}
		}
		System.out.println("maxBest: " + maxBest);
		if(surprises != 0) System.out.println("surprise" + surprises);
		
		return maxBest;
	}
}