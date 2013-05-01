/**
 * 
 * @author RasPat
 *Problem can be found here:
 *https://code.google.com/codejam/contest/544101/dashboard#s=p0
 *Simple Solution: Push everything to the right and check for winners
 *
 *Same as Rotate except use for loops with boundaries to extract diagonals
 *rather than while loops
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class RotateAlternate {

	public static void main(String[] args) {
		
		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2010_1A\\";;
		String relRoot = "A-large-practice.in";
		String in = pathRoot + relRoot;
		//Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength)+"1out";
		
		BufferedReader r = null;
		BufferedWriter w = null;	
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String line[];
			//First line of Input is the # of test cases
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;
			
			while (caseCount < testCases) {
				caseCount++;
				//Second line is boardSize, winLength
				line = r.readLine().split(" ");
				int boardSize = Integer.parseInt(line[0]);
				int winLen = Integer.parseInt(line[1]);
				char[][] board = new char[boardSize][boardSize];
				
				
				String rWinString = "";
				String bWinString = "";
				
				for(int i = 0; i < winLen; i++) {
					rWinString += "R";
					bWinString += "B";
				}
				//read in the board
				for(int i =0; i < boardSize; i++) {
					String tmp = r.readLine();
					for(int j = 0; j< boardSize; j++) {
						board[i][j] = tmp.charAt(j);
					}
				}
				
				//rotate(board);
				//push everything to the right
				for(int i =0; i < boardSize; i++) {
					for(int j = boardSize - 1; j >= 0; j--) {
						if(board[i][j] != '.') {
							continue;
						} else {
							for(int k = j; k >= 0; k--) {
								if(board[i][k] == '.') {
									continue;
								} else {
									board[i][j] = board[i][k];
									board[i][k] = '.';
									break;
								}
							}
						}
					}
				}
				
				//Check for winners
				boolean rWin = false;
				boolean bWin = false;
				ArrayList<String> matches = new ArrayList<String>();
				
				
				for(int i = 0; i < board.length; i++) {
					//add rows
					matches.add(String.valueOf(board[i]));
					
					String matchC = "";
					for(int j = 0; j < board.length; j++) {
						matchC += board[j][i];
						String matchD1 = "";
						String matchD2 = "";
						
						//D1 and D2 constraints made so indexes don't go off the board
						//could make the 2 'l' loops into one but it seems clearer 
						//this way and winLen is typically small
						
						//Diagonal 1
						if (i <= board.length - winLen	&& j <= board.length - winLen) {
							for (int l = 0; l < winLen; l++) {
								matchD1 += board[i + l][j + l];
							}
							matches.add(matchD1);
						}
						//Diagonal 2
						if(i >= winLen - 1 && j <= board.length - winLen) {
							for(int l = 0; l < winLen; l++) {
								matchD2 += board[i-l][j+l];
							}
							matches.add(matchD2);
						}
						
//						//This way no fussing around with the boundary conditions just make sure to check
//						//if the returned string are long enough
//						int x1, x2, y1, y2;
//						x1 = x2 = i;
//						y1 = y2 = j;
//						//while still on board
//						while(x1 >= 0 && x1 < board.length && y1 >= 0 && y1 < board.length) {
//							matchD1 += board[x1][y1];
//							x1++;
//							y1++;
//						}					
//						while(x2 >= 0 && x2 < board.length && y2 >= 0 && y2 < board.length) {
//							matchD2 += board[x2][y2];
//							x2--;
//							y2++;
//						}
//						if(matchD1.length() >= winLen) {
//							matches.add(matchD1);
//						}
//						if(matchD2.length() >= winLen) {
//							matches.add(matchD2);
//						}
					}

					matches.add(matchC);
				}

				String winner = "";

				ListIterator<String> it = matches.listIterator();
				while(it.hasNext()) {
					String s = (String) it.next();


					if(s.contains(rWinString)) {
						rWin = true;
					}
					if(s.contains(bWinString)) {
						bWin = true;
					}
					//does this save any time?
					if((rWin && bWin)) {
						break;
					}
				}
				
				if(rWin && bWin) winner = "Both";
				else if(rWin) winner = "Red";
				else if(bWin) winner = "Blue";
				else winner = "Neither";
				
				w.write("Case #" + caseCount + ": " + winner);
				w.newLine();
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
}
