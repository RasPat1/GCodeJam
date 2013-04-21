package googleCodeJam;
/**
 * 
 * @author RasPat
 *This is a skeleton for basic read write operations
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Rotate {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\googleCodeJam\\1A2010\\";;
		String relRoot = "A-large-practice.in";
		String in = pathRoot + relRoot;
		//Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength)+"out";
		
		BufferedReader r = null;
		BufferedWriter w = null;	
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String line[];
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;
			
			while (caseCount < testCases) {
				caseCount++;
				
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
				
				for(int i =0; i < boardSize; i++) {
					String tmp = r.readLine();
					for(int j = 0; j< boardSize; j++) {
						board[i][j] = tmp.charAt(j);
					}
				}
				
				//rotate(board);
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
				
				//check rows
				boolean rWin = false;
				boolean bWin = false;

				for(int i =0; i < boardSize; i++) {
						String match = String.valueOf(board[i]);
						if(match.indexOf(rWinString) != -1) {
							rWin = true;
						}
						if(match.indexOf(bWinString) != -1) {
							bWin = true;
						}
				}
				//check columns
				for(int j = 0; j < board.length; j++) {
					String match = "";
					for(int i = 0; i < board.length; i++) {
						match += board[i][j];
					}
					if(match.indexOf(rWinString) != -1) {
						rWin = true;
					}
					if(match.indexOf(bWinString) != -1) {
						bWin = true;
					}
				}
				//check diagonals 1 
				for(int i = 0; i <= board.length - winLen; i++) {		
					for(int j = 0; j <= board.length - winLen; j++) {
						String match = "";
						for(int l = 0; l < winLen; l++) {
							match += board[i+l][j+l];
						}
						if(match.indexOf(rWinString) != -1) {
							rWin = true;
						}
						if(match.indexOf(bWinString) != -1) {
							bWin = true;
						}
					}
				}
				
				//check diagonals 2
				for(int i = winLen - 1; i < board.length; i++) {
					for(int j = 0; j <= board.length - winLen; j++) {
						String match = "";
						for(int l = 0; l < winLen; l++) {
							match += board[i-l][j+l];
						}
						if(match.indexOf(rWinString) != -1) {
							rWin = true;
						}
						if(match.indexOf(bWinString) != -1) {
							bWin = true;
						}
					}
				}
				String winner = "";
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
