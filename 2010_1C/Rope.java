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

public class Rope {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\googleCodeJam\\1C2010\\";;
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
				int crossCount = 0;
				caseCount++;
				int wireCount = Integer.parseInt(r.readLine());
				int[][] wires = new int[wireCount][2];
				for(int i = 0; i < wires.length; i++) {
					line = r.readLine().split(" ");
					wires[i][0] = Integer.parseInt(line[0]);
					wires[i][1] = Integer.parseInt(line[1]);
				}
				
				for(int i = 0; i < wires.length - 1; i++) {
					for(int j = i; j < wires.length; j++) {
						if ((wires[i][0] > wires[j][0] && wires[i][1] < wires[j][1]) ||
								(wires[i][0] < wires[j][0] && wires[i][1] > wires[j][1])) {
							crossCount++;
						}
					}
				}
				w.write("Case #" + caseCount + ": " + crossCount);
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
