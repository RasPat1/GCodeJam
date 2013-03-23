/**
 * 
 * @author RasPat
 *This is a solution to a problem for google code jam.  
 * Process
 *store the data
 *sort (from low to high) by the number of stars required to complete the 2 star difficulty 
 *Begin by completing the 2 star difficulty with the lowest star requirement
 *if this is not possible complete as many one star levels as required with a preference to not
 *complete the one star version of the level with the lowest 2 star difficulty
 *repeat
 *
 *Input Form::
 *NUMBER OF TEST CASES
 *NUMBER OF LEVELS
 *1STAR 2STAR
 *...
 *NUMBER OF LEVELS
 *1STAR 2STAR
 *...
 *...
 *All Integers
 *
 *  Full Problem here:
 *  https://code.google.com/codejam/contest/1645485/dashboard#s=p1
 */

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class stars {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspaceClassic\\GCodeJam\\";
		String relPath = "B-small-practice.in";

		// Length of the file extension
		int extLength = 2;
		String in = pathRoot + relPath;
		String out = pathRoot + relPath.substring(0, relPath.length() - extLength) + "out";

		BufferedReader r = null;
		BufferedWriter w = null;

		int test = 0;
		int cases = 0;
		int levels = 0;

		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String line;
			// Read in the number of test cases
			cases = Integer.parseInt(r.readLine().trim());
			System.out.println("# of cases is: " + cases);
			// Read in the input one Test case at a time
			// the conditional reads in the number of levels
			while ((line = r.readLine()) != null) {
				test++;
				levels = Integer.parseInt(line.trim());
				System.out.println("Current test # is: " + test);
				System.out.println("Number of levels is: " + levels);

				// The first col is level # or -1 for one star solved or -2 for
				// two stars solved
				// the second col is how many stars necessary for a one star
				// rating
				// The Third col is how many stars necessary for a two star
				// rating
				int[][] gameConfig = new int[levels][3];

				storeLevels(levels, gameConfig, r);
				// sort by 2 star difficulty rating
				sort(gameConfig);
				// For debug purposes
				print(gameConfig);
				// Calculate the minimal number of moves
				int moves = play(gameConfig);

				// Form is "Case #XX: YY", where XX is test number, YY is number
				// of Moves or Too Bad if not possible
				w.write("Case #" + test + ": ");
				if (moves == -1) {
					w.write("Too Bad");
				} else {
					w.write("" + moves);
				}
				w.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				r.close();
				w.flush();
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isDone(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i][0] != -2)
				return false;
		}
		return true;
	}

	/**
	 * takes in a array of the level configuration that expects to be sorted by
	 * # of stars required for achieving a two star rating. Sorted from low to
	 * high
	 * 
	 * @param gameConfig
	 * @return
	 */
	public static int play(int[][] gameConfig) {
		int stars = 0;
		int i = 0;
		int moves = 0;
		boolean possible = true;

		while (!isDone(gameConfig) && possible) {
			if (gameConfig[i][2] <= stars) {
				if (gameConfig[i][0] == -1) {
					gameConfig[i][0] = -2;
					stars++;
					moves++;
				} else {
					gameConfig[i][0] = -2;
					stars += 2;
					moves++;
				}
				i++;
			} else {
				// Trace through the array backwards
				// Find any other one star level that you can do with the
				// last resort being the the one star version of the level with
				// the lowest 2 star requirement
				for (int n = gameConfig.length - 1; n >= i; n--) {
					if (gameConfig[n][1] <= stars && gameConfig[n][0] >= 0) {
						gameConfig[n][0] = -1;
						moves++;
						stars++;
						break;
					}
					if (n == i) {
						return -1;
					}
				}
			}
		}

		return moves;
	}

	public static void storeLevels(int levels, int[][] gameConfig,
			BufferedReader r) {
		for (int i = 0; i < levels; i++) {
			try {
				gameConfig[i][0] = i;
				String[] nums = r.readLine().trim().split(" ");
				// not very robust assumes 2 entries per line if less will throw
				// indexoutofBounds
				gameConfig[i][1] = Integer.parseInt(nums[0]);
				gameConfig[i][2] = Integer.parseInt(nums[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Sort each row by their value in the second column sorts second column
	 * from low to high
	 * @param gameConfig
	 */
	public static void sort(int[][] gameConfig) {
		Arrays.sort(gameConfig, new java.util.Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[2] - b[2];
			}
		});
	}

	public static void print(int[][] gameConfig) {
		System.out.println("Sorted by Column 2");
		for (int i = 0; i < gameConfig.length; i++) {
			for (int j = 0; j < gameConfig[i].length; j++) {
				System.out.print(gameConfig[i][j] + " ");
			}
			System.out.println();
		}
	}



}
