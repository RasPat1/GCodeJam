/**
 * @author RasPat
 * Problem can be found here:
 * https://code.google.com/codejam/contest/635101/dashboard#s=p0
 * This solution: Recursive(whyyy?!?!) alg for pulling out the paths, adding them and counting them
 * Simpler solution: Use a TreeSet. Add the sections of the new path. Count how many got added.
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileFixIt {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2010_1B\\";;
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
				int mkDirCount = 0;
				line = r.readLine().split(" ");
				int dirs = Integer.parseInt(line[0]);
				int toMkDirs = Integer.parseInt(line[1]);

				ArrayList<String[]> dirStruct = new ArrayList<String[]>();
				for(int i = 0; i < dirs; i++) {
					line = r.readLine().substring(1).split("/");
					dirStruct.add(line);
				}
				
				//parse the toMkDir paths to String arrays
				//if there is a directory that matches the whole thing
				//then don't need to make any
				// if not then take the sub array form 0 to end - 1
				//and add one to the mkdirCount
				//try to find a thing that matches the whole thing
				//if oyu find then done if not then recurse take subarray 0 to end-1
				for(int n = 0; n < toMkDirs; n++) {
					String[] curDir = r.readLine().substring(1).split("/");
					mkDirCount += match(curDir, dirStruct);
				}

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
	
	public static int match(String[] curDir, ArrayList<String[]> dirStruct) {
		boolean contains = false;
		for(String[] s: dirStruct) {
			if(Arrays.equals(s, curDir)) {
				contains = true; 
			}
		}
		if(contains) {
			return 0;
		} else {
			dirStruct.add(curDir);
			if(curDir.length == 1) {
				return 1;
			}
			return 1 + match(Arrays.copyOfRange(curDir, 0, curDir.length - 1), dirStruct);
		}	
	}
}
