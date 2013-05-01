/**
 * 
 * @author RasPat
 *Problem can be found here:
 * https://code.google.com/codejam/contest/1645485/dashboard#s=p2
 * Simple Solution: 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CruiseControl {

	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspace\\GCodeJam\\2012_1A\\";
		;
		String relRoot = "C-small-practice.in";
		String in = pathRoot + relRoot;
		// Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength) + "2out";

		BufferedReader r = null;
		BufferedWriter w = null;
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String[] line;
			// First line of Input is the # of test cases
			int testCases = Integer.parseInt(r.readLine());
			int caseCount = 0;

			while (caseCount < testCases) {
				caseCount++;
				int carCount = Integer.parseInt(r.readLine());
				line = r.readLine().split(" ");
				//char
				Car[] road = new Car[carCount];
				for(int i = 0; i < road.length; i++) {
					road[i] = new Car(line[0].charAt(0), 
							Double.parseDouble(line[1]), Double.parseDouble(line[2]));
				}
				
				//have a road full of Cars
				
				
				
				
				
				double result = 0.0d;
				
				w.write("Case #" + caseCount + ": "
						+ String.format("%.6f", result));
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

//	public class Car {
//		final static int LENGTH = 5;
//		char lane;
//		double speed;
//		double pos;
//		
//		public Car(char lane, double speed, double pos) {
//			this.lane = lane;
//			this.speed = speed;
//			this.pos = pos;
//		}
//		public void changeLane() {
//			if(lane == 'R') {
//				lane = 'L';
//			} else lane = 'R';
//		}
//		
//	}
}

