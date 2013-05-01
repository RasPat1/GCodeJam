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

public class basicio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\";
		String relRoot = "B-large-practice.in";
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
			while((line = r.readLine()) != null) {
				//Enter read/write code here
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
