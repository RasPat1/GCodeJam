/**
 * 
 * @author RasPat
 *Problem can be found here:
 * https://code.google.com/codejam/contest/1836486/dashboard#s=p0
 * Simple Solution: 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SafetyInNumbers {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2012_1B\\ProbA\\";
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
				int numCon = Integer.parseInt(line[0]);
				int[] scores = new int[numCon];
				

				for (int i = 0; i < scores.length; i++) {
					scores[i] = Integer.parseInt(line[i+1]);
				}
//				System.out.println(scores[0]);
				double[] result = new double[numCon];
//				for(int i = 0; i < scores.length; i++) {
//					result[i] = minScore(scores, i);
//				}
				
//				for( int i = 0; i < result.length; i++) {
//					if(result[i] < 0) {
//						double sub = result[i]/(result.length - 1);
//						for(int j =0; j < result.length; j++) {
//							if(i == j) continue;
//							result[j] += sub;
//						}
//					}
//				}
//				for(int i = 0; i < result.length; i++) {
//					if(result[i] < 0) result[i] = 0;
//				}
				result = Scores(scores);
				double checkSum = 0;
				for(double d: result) {
					checkSum += d;
				}
				System.out.println("CheckSum " + checkSum);
				w.write("Case #" + caseCount + ":");
				for(double d: result) {
					w.write(" " + String.format("%.6f", d));
				}
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
				System.out.println("Time elapsed: "+ (System.currentTimeMillis() - start));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Find out how much they need to be equal to you.
	 * IF its more than 100 you need 0
	 * if its less than 100 you need the remaining vote% divided by the number of guys not bigger than you
	 * until you get to someone bigger than you
	 * @param list
	 * @param conIndex
	 * @return
	 */
	
	/**
	 * or take the lowest guy in the list and give him how much he needs to be to be equal to the next lowest guy
	 * then give those guys how much they need ot be equal to the next guy and so on until 100% of the vote is out
	 * 
	 * @param list
	 * @param conIndex
	 * @return
	 */
	
	/**
	 * take teh difference between your value and their values that's how much they need to get
	 * divide that by the sum thats the percent that they took to get equal to you.  If it's more
	 * than one then you need 0.  If it's less than one then they can catch up to you and now you
	 * need votes to not be eliminated.
	 * 
	 * @param list
	 * @param conIndex
	 * @return
	 */
	public static double minScore(int[] list, int conIndex) {
		int sum = 0;
		int diff = 0;
//		double[] equalScore = new double[list.length];
		for(int i : list) {
			sum += i;
		}
		
		System.out.println("sum is: " + sum);
		for(int i = 0; i < list.length; i++) {
			if(i == conIndex) {
				
//				equalScore[i] = 101.0d;
				continue;
			}
			diff += list[conIndex] - list[i];
//			double numer = list[i] - list[conIndex];
//			equalScore[i] = ((numer / sum) + 1)*0.5d;
		}
//		double minScore = 102.0d;
//		for(double d: equalScore) {
//				minScore = (d<minScore)?d:minScore;
//		}
//		int impScores = list.length;
//		for(double d : list) {
//			if(d/sum > 2d/impScores) {
//				impScores--;
//				if(d == (double) list[conIndex]) {
//					return 0d;
//				}
//			}
//		}
		int effLen = list.length;
		double result = (sum - diff) / (list.length * sum);
		int resultInt = sum - diff;
		if(resultInt < 0) {
			resultInt = 0;
		}
		if(result < 0 ) {
			result = 0;
		}
		return result;
//		return 2d/((double) impScores) - (list[conIndex]/sum);
	}
	
	public static double[] Scores(int[] scores) {
		double[] result = new double[scores.length];
		int[] diff = new int[scores.length];
		int[] diff2 = new int[scores.length];
		int effLen = 0;
		int sum = 0;
		for(int i = 0; i < scores.length; i++) {
			sum += scores[i];
//			for(int j = 0; j < scores.length; j++) {
//				if(i==j)continue;
//				diff[i] += scores[i] - scores[j];
//			}
		}
//		diff = calcDiff(scores, sum);
		
//		for(int i = 0; i < scores.length; i++) {
//			if(diff[i] > sum) {
//				diff2[i] = sum;
//				effLen--;
//				continue;
//			}
//			for(int j = 0; j < scores.length; j++) {
//				if(i==j || diff[j] > sum)continue;
//				diff2[i] += scores[i] - scores[j];
//			}
//		}
		
//		for(int i = 0; i < diff.length; i++) {
//			if(diff[i] > sum) {
//				effLen--;
//				for(int j = 0; j < diff.length; j++) {
//					diff[j] -= scores[j] - scores[i]; 
//				}
//				diff[i] = sum;
//			}
//		}
		
		diff = calcDiff(scores, sum);
		for(int i: diff) {
			if(i < sum) effLen++;
		}
		for(int i = 0; i < result.length; i++) {
			result[i] = (double) ((sum - diff[i])*100) / (sum * effLen);
		}
		
		for(double d: result) {
			if(d<0) {
				System.out.println("uh OH");
			}
		}
		return result;
	}
	
	public static int[] calcDiff(int[] scores, int sum) {
		int[] diff = new int[scores.length];
		for(int i = 0; i < scores.length; i++) {
			for(int j = 0; j < scores.length; j++) {
				if(i==j)continue;
				diff[i] += scores[i] - scores[j];
			}
		}
		diff = calcDiff(diff, scores, sum);
		return diff;
	}
	
	public static int[] calcDiff(int[] diff, int[] scores, int sum) {
		int[] diff2 = new int[scores.length];
		for(int i = 0; i < scores.length; i++) {
			if(diff[i] >= sum) {
				diff2[i] = sum;
				continue;
			}
			
			for(int j = 0; j < scores.length; j++) {
				if(i==j || diff[j] >= sum)continue;
				diff2[i] += scores[i] - scores[j];
			}
		}
		for(int i: diff2) {
			if(i > sum) {
				System.out.println(i - sum);
				diff2 = calcDiff(diff2, scores, sum);
			}
		}
		return diff2;
	}
	
	
}
