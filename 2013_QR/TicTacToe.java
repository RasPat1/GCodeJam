package gCodeJam13;

import java.math.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TicTacToe {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\gCodeJam13\\";
		String relRoot = "A-large.in";
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
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;
			String winner;
			char[][] input = new char[4][4];
			while ((line = r.readLine()) != null) {
				caseCount++;
				winner = "-";
//				zero(input);
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						input[i][j] = line.charAt(j);
					}
						line = r.readLine();
				}

				//First row is X Second row is X
				//[][0] = rows, [][1] = cols, [][2] = diag1, [][3] = diag2
				int[][] count = new int[2][4];

				for (int i = 0; i < 4; i++) {
					if(winner != "-") break;
					zero(count);

					for (int j = 0; j < 4; j++) {
						if (input[i][j] == 'X' || input[i][j] == 'T') {
							count[0][0]++;
						}
						if (input[i][j] == 'O' || input[i][j] == 'T') {
							count[1][0]++;
						}
						if (input[j][i] == 'X' || input[j][i] == 'T') {
							count[0][1]++;
						}
						if (input[j][i] == 'O' || input[j][i] == 'T') {
							count[1][1]++;
						}
						if (input[j][j] == 'X' || input[j][j] == 'T') {
							count[0][2]++;
						}
						if (input[j][j] == 'O' || input[j][j] == 'T') {
							count[1][2]++;
						}
						if (input[j][3 - j] == 'X' || input[j][3 - j] == 'T') {
							count[0][3]++;
						}
						if (input[j][3 - j] == 'O' || input[j][3 - j] == 'T') {
							count[1][3]++;
						}
					}
					if (hasFour(count[0])) {
						winner = "X won";
					}
					if (hasFour(count[1])) {
						winner = "O won";
					}
				}

				if (winner == "-") {
					for (int i = 0; i < 4; i++) {
						for (int j = 0; j < 4; j++) {
							if (input[i][j] == '.') {
								winner = "Game has not completed";
							}
						}
					}
					if (winner == "-")
						winner = "Draw";
				}

				System.out.println(line);
				w.write("Case #" + caseCount + ": " + winner);
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
	public static boolean hasFour(int[] a) {
		for(int i =0; i < a.length; i++) {
			if(a[i] == 4) return true;
		}
		return false;
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
}