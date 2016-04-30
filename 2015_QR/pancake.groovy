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

		String pathRoot = "/Users/Rasesh/Documents/repos/GCodeJam/2015_QR/";
		String relRoot = "B-small-attempt0.in";
		String inFile = pathRoot + relRoot;
		//Length of the file extension
		int extLength = 2;
		String out = inFile.substring(0, inFile.length() - extLength)+"out-new";
		
		BufferedReader r = null;
		BufferedWriter w = null;
		
		try {
			r = new BufferedReader(new FileReader(inFile));
			w = new BufferedWriter(new FileWriter(out));
			String line;
			Integer totalTestCases = r.readLine().toInteger()
			Integer caseCount = 1

			while((line = r.readLine()) != null) {
				Integer activeDiners = line.toInteger()
				List<Integer> pancakesPerPlate = 
					r.readLine().split(" ").collect{ it.toInteger() }

				Integer totalMinutes = 
					whenIsBreakFastOver(caseCount==7, pancakesPerPlate)

				w.writeLine("Case #${caseCount}: ${totalMinutes}");
				caseCount++

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


	/**
	 * Another Dynamic Programming classic
	 * A pancake is eaten per minute 
	 * (!! Too hard. Successor to saltine challenge. 
	 * For how many minutes can you eat a pancake a minute?)
	 * The time it will take for breakfast to be over is the max
	 * number of pancakes on the plates (with no special minutes)
	 * How to decide to have a special minute?
	 * Special minutes fornt load a long term cost
	 * So if you do decide to take special minutes they would all 
	 * be immediately
	 * smart approach: convert this shit to binary find the smallest number (1 = take special minute, 0 = do nothing minimize the number of bits in teh number which is the same as finding the smallest number)
	 * approach i'm gonan try first:
	 * 
	 * Not DP
	 * Find the max number of special minutes you can take
	 * find the number of minutes it will take to finish breakfast for taking between 0 and max special minutes
	 * Grab the minimum
	 * 
	 * After pancakes are moved from one plate to an empty one
	 * The new max will be how long till breakfast is over
	 * A special mintue takes one minute... (duh)
	 * So if the new max is 
	 *
	 */
	public static Integer whenIsBreakFastOver(Boolean print, List<Integer> pancakesPerPlate) {
		List<Integer> minutes = [pancakesPerPlate.max()]
		Integer specialMinutesTaken = 0
		if (print) {
println pancakesPerPlate
		}
		
		while(canTakeSpecialMinute(pancakesPerPlate)) {
			if (print) {
				println pancakesPerPlate
			}
			pancakesPerPlate = takeSpecialMinute(pancakesPerPlate)
			specialMinutesTaken++
			minutes << (pancakesPerPlate.max() + specialMinutesTaken)
		}
		if (print) { println minutes }
		minutes.min()
	}

	public static Boolean canTakeSpecialMinute(List<Integer> pancakesPerPlate) {
		return pancakesPerPlate.max() >= 4
	}
	public static List<Integer> takeSpecialMinute(List<Integer> pancakesPerPlate) {
		Integer max =  pancakesPerPlate.max()
		Integer index = pancakesPerPlate.findIndexOf{it == max}
		Integer shift = (max / 2).toInteger()
		pancakesPerPlate[index] = max - shift
		pancakesPerPlate << shift
		pancakesPerPlate
	}
}