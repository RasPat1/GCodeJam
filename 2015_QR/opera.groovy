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
		String relRoot = "A-large.in";
		String inFile = pathRoot + relRoot;
		//Length of the file extension
		int extLength = 2;
		String out = inFile.substring(0, inFile.length() - extLength)+"out";
		
		BufferedReader r = null;
		BufferedWriter w = null;
		
		try {
			r = new BufferedReader(new FileReader(inFile));
			w = new BufferedWriter(new FileWriter(out));
			String line;
			Integer totalTestCases = r.readLine().toInteger()
			Integer caseCount = 1

			while((line = r.readLine()) != null) {
				List<String> inputs = line.split(" ")
				Integer maxShyness = inputs[0].toInteger()
				List<Integer> crowd = inputs[1].collect { it.toInteger() }
				Integer addedFriendCount = canHazFriends(maxShyness, crowd)

				w.writeLine("Case #${caseCount}: ${addedFriendCount}");
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

	public static Integer canHazFriends(Integer maxShyness, List<Integer> crowd) {
		Integer standingCount = 0;
		Integer friendsAdded = 0;
		// in the crowd the index is shynessLevel 
		// the value is the number of people who have that shynessLevel
		crowd.eachWithIndex {
			Integer peopleCount,
			Integer shynessLevel ->

			Integer deficit = shynessLevel - standingCount

			if (deficit > 0) {
				standingCount += deficit
				friendsAdded += deficit
			}
			standingCount += peopleCount

		}
		return friendsAdded
	}


}