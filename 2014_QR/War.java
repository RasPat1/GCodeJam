/**
 * @author RasPat
 * Problem can be found here:
 * https://code.google.com/codejam/contest/32016/dashboard#s=p1
 * incomplete.
 */

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class War {

	public static void main(String[] args) {
		String pathRoot = "C:\\Users\\RasPat\\Dev\\gcj\\src\\gcj\\";
		String relRoot = "D-large.in";
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
                                int numBlocks = Integer.parseInt(r.readLine());
                                double[][] bWeights = new double[2][numBlocks];
                                double[][] dbWeights = new double[2][numBlocks]; 
                                for (int i = 0; i < 2; i++) {
                                    line = r.readLine().split(" ");
                                    for (int j = 0; j < numBlocks; j++) {
                                        bWeights[i][j] = Double.parseDouble(line[j]);
                                        dbWeights[i][j] = Double.parseDouble(line[j]);

                                    }
                                }
                                
                                //Warning garbage code ahead

				                Arrays.sort(bWeights[0]);
                                Arrays.sort(bWeights[1]);
                                Arrays.sort(dbWeights[0]);
                                Arrays.sort(dbWeights[1]);
                                
                                int nScore = 0;
                                int dnScore = 0;
                                
                                // Deceitful War
                                // Go from highest to lowest
                                // Every block that she has that is heavier than any remaining block is a point
                                // She places her heaviest block
                                // He places his heaviest block that is lighter than hers
                                
                                for (int i = numBlocks - 1; i >=0; i--) {
                                    double dnC = dbWeights[0][i];
                                    double dnK = -1;
                                    int dkIndex = -1;
                                    int dkHeavyIndex = numBlocks - 1;
                                    
                                    for (int j = 0; j < numBlocks; j++) {
                                        //Get the largest block of Ken's thats not burned and less than Naomi's
                                        if (dbWeights[1][j] < dnC && dbWeights[1][j] > dnK && dbWeights[1][j] != -1) {
                                            dkIndex = j;
                                        }
                                    }
                                    
                                    if (dkIndex == -1) {
                                        dbWeights[1][dkHeavyIndex] = -1;
                                        dkHeavyIndex--;
                                    } else {
                                        dnScore++;
                                        dbWeights[1][dkIndex] = -1;
                                        if (dkIndex == dkHeavyIndex) {
                                            dkHeavyIndex--;
                                        }
                                    }
                                }
                                
                                
                                // Real War
                                
                                // GO form lowest to highest
                                int kLowestIndex = 0;
                                for (int i = 0; i < numBlocks; i++) {
                                    double nC = bWeights[0][i];
                                    double nK = Double.MAX_VALUE;
                                    int kIndex = -1;
                                    
                                    for (int j = 0; j < numBlocks; j++) {    
                                        if (bWeights[1][j] > nC && bWeights[1][j] < nK && bWeights[1][j] != -1) {
                                            nK = bWeights[1][j];
                                            kIndex = j;
                                        }
                                    }
                                    if (kIndex == -1) {  //Didn't find one that will win burn the lowest one
                                        bWeights[1][kLowestIndex] = -1;
                                        nScore++;
                                        kLowestIndex++;
                                    } else {
                                        bWeights[1][kIndex] = -1;
                                        if (kIndex == kLowestIndex) {
                                            kLowestIndex++;
                                        }
                                    }
                                }
                                
//                                System.out.println(nScore);
//                                System.out.println(dnScore);
                                
				w.write(dnScore + " " + nScore);
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
	public static void printArray(double[][] a) {
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
