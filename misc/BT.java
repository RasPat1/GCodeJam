import java.io.*;
import java.util.*:
/*
* https://code.google.com/codejam/contest/975485/dashboard
*/
class BT {
  public static void main(String[] args) throws IOException {
    String inPath = "A-small-practice.in";

    // Length of the file extension
    int extLength = 2;
    String out = in.substring(0, in.length() - extLength) + "out";
    BufferedReader r = null;
    BufferedWriter w = null;

    w = new BufferedWriter(new FileWriter(out));

    String line = null;
    int caseCount = 0;
    r = new BufferedReader(new FileReader(in));
    int testCases = Integer.parseInt(r.readLine()); // number of test cases

    while((line = r.readLine()) != null) {
      line = r.readLine();
      String[] vals = line.split(" ");
      int cmdCount = Integer.parseInt(vals[0]);
      for (int i = 1; i < vals.length; i+=2) {

      }
      String[] inS = line.split(" ");
      for (int i = 0; i < 1000; i++) {
        vals[i] = Integer.parseInt(inS[i]);
      }


      w.write("Case #" + String.valueOf(caseCount + 1) + ": ");
      String outcome = "";

      w.write(outcome);
      w.newLine();
      caseCount++;
    }

    r.close();

    if(w != null) {
      w.flush();
      w.close();
    }

  }
}