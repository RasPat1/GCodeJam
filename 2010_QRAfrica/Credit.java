package googleCodeJam;

	/**
	 * https://code.google.com/codejam/contest/351101/dashboard#s=p0
	 */
	import java.math.*;
	import java.util.Arrays;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	
	public class Credit {

		public static void main(String[] args) {
			String pathRoot = "C:\\Users\\RasPat\\workspace\\Exercises\\Exercises\\googleCodeJam\\QRAfrica2010\\";
			String relRoot = "A-large-practice.in";
//			String relRoot = "D-practice.in";
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
					

					int credit = Integer.parseInt(r.readLine().trim());
					int[] items = new int[Integer.parseInt(r.readLine().trim())];
					line = r.readLine().split(" ");
					for(int i = 0; i < items.length; i++) {
						items[i] = Integer.parseInt(line[i]);
					}
					for(int i = 0; i < items.length-1; i++) {
						for(int j = i + 1; j < items.length; j++) {
							if(credit == (items[i] + items[j])) {
								int I = i+1;
								int J = j + 1;
								w.write(I + " " + J);
								w.newLine();
								break;
							}
						}
					}
					
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
	}
