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
import java.util.Arrays;

public class Mkdir {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\googleCodeJam\\1B2010\\";;
		String relRoot = "A-small-practice.in";
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
				int mkDirCount = 0;
				line = r.readLine().split(" ");
				int dirs = Integer.parseInt(line[0]);
				int toMkDirs = Integer.parseInt(line[1]);
				String[][] dirStruct = new String[dirs][];
				for(int i = 0; i < dirs; i++) {
					line = r.readLine().substring(1).split("/");
					dirStruct[i] = new String[line.length];
					System.out.println();
					for (String s:line ) {
						System.out.print(s + " ");
					}
					for(int j =0; j < line.length; j++) {
						dirStruct[i][j] = line[j];
					}	
				}
				
				//parse the toMkDir paths to String arrays
				//if there is a direcory that matches the whole thing
				//then don't need to make any
				// if not then take the sub array form 0 to end - 1
				//and add one to the mkdirCount
				//try to find a thing that matches the whole thing
				//if oyu find then done if not then recurse take subarray 0 to end-1
				for(int n = 0; n < toMkDirs; n++) {
					String[][] tmp = null;
					String[] curDir = r.readLine().substring(1).split("/");
					mkDirCount += match(curDir, dirStruct, tmp);
					
				}
				
				
//				if(caseCount == 2) {
//					return;
//				}
				
				
				
				
				
				w.write("Case #" + caseCount + ": " + mkDirCount);
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
	
	public static int match(String[] curDir, String[][] dirStruct, String[][] tmp) {
		for(int i = 0; i < dirStruct.length; i++) {
			if(dirStruct[i].length == curDir.length) {
				for(int j = 0; j < curDir.length; j++) {
					if(!curDir[j].equals(dirStruct[i][j])) {
						break;
					} else if( j == curDir.length - 1 && curDir[j] == dirStruct[i][j]) {
						return 0;
					}
				}	
			}
		}
		dirStruct = addDir(curDir, dirStruct, tmp);
		if(curDir.length == 1) {
			return 1;
		}
		return 1 + match(Arrays.copyOfRange(curDir, 0, curDir.length - 1), dirStruct, tmp);	
	}
	
	public static String[][] addDir(String[] curDir, String[][] dirStruct, String[][] dirStruct1) {
		dirStruct1 = dirStruct;
		dirStruct = new String[dirStruct1.length + 1][];
		for(int i = 0; i < dirStruct1.length; i++) {
			dirStruct[i] = new String[dirStruct1[i].length];
			for(int j = 0; j < dirStruct1[i].length; j++) {
				dirStruct[i][j] = dirStruct1[i][j];
			}
		}
		dirStruct[dirStruct.length - 1] = new String[curDir.length];
		for(int i = 0; i < curDir.length; i++) {
			dirStruct[dirStruct.length - 1][i] = curDir[i];
		}
		return dirStruct;
	}
}
