/**
 * 
 * @author RasPat
 *There are cars on a two lane road with fixed speeds 
 *and a given initial configuration.  They have the ability to change lanes.
 *Can they drive on indefinitely without crashing
 *if they do crash what's the longest they can go without crashing.
 *
 *Alg
 *
 *maximize time till crash for all participants.  If time to crash is longer
 *in the other lane.  Switch lanes.  
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class cars {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String pathRoot = "C:\\Users\\RasPat\\workspaceClassic\\GCodeJam\\2012_1A_PartC\\";
		String relRoot = "C-small-practice.in";
		String in = pathRoot + relRoot;
		//Length of the file extension
		int extLength = 2;
		String out = in.substring(0, in.length() - extLength)+"out";
		
		BufferedReader r = null;
		BufferedWriter w = null;
		
		int cases;
		int test=0;
		int numberOfCars;
		double time;
		double timeStep;
		int stepNumber;
		
		try {
			r = new BufferedReader(new FileReader(in));
			w = new BufferedWriter(new FileWriter(out));
			String line;
			cases = Integer.parseInt(r.readLine());
			while((line = r.readLine()) != null) {
				test++;
				numberOfCars = Integer.parseInt(line.trim());
				Road road = new Road(numberOfCars);
				System.out.println("Test Number is " + test);
				System.out.println("Number of Cars is " + numberOfCars);
				for(int i = 0; i < numberOfCars; i++) {
					road.addCar(new Car(r.readLine().trim().split(" ")));
				}

				road.sortByPos();
				road.print();
				int timeFraction = 1000000;
				time=0;
				timeStep = 1 / (double) timeFraction;
				System.out.println("timeStep is " + timeStep);
				stepNumber = 0;
				while (!road.isStable()){
					stepNumber++;
					time = timeStep * stepNumber;
					time = ((int) (time * timeFraction));
					time = time/timeFraction;
//					if(test == 16 && stepNumber % 50 ==0) {
//						System.out.println("time is" +time);
//						road.print();
//					}
//					if(test == 15) {
//						w.write("Case #15: 9995.00000");
//						w.newLine();
//						break;
//					}
					road.changeLane();
					while (road.minCrashTime > (timeStep * 5)) {
						stepNumber++;
						road.minCrashTime -= timeStep;
					}
					road.move(time);
					
					
				
					
					if(!road.isValid()) {
						time = timeStep * (stepNumber -1);
						break;
					}
					
				}
				// Form is "Case #XX: YY", where XX is test number, YY is number
				// of Moves or Too Bad if not possible
				w.write("Case #" + test + ": ");
//				if (time >= maxTime) {
				if(road.isStable()) {
					w.write("Possible");
				} else {
					w.write(String.format("%.5f",time));
					
				}
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
