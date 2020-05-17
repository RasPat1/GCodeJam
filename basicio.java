import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class basicio {

  public static void main(String[] args) {

    String pathRoot = "./";
    String relRoot = "test.in";
    String in = pathRoot + relRoot;
    //Length of the file extension
    int extLength = 2;
    String out = in.substring(0, in.length() - extLength)+"out";

    BufferedReader r = null;
    BufferedWriter w = null;

    int testCount = 1;
    int totalTests = Integer.valueOf(r.readLine());

    try {
      r = new BufferedReader(new FileReader(in));
      w = new BufferedWriter(new FileWriter(out));
      String line;
      while((line = r.readLine()) != null) {
        long start = System.currentTimeMillis();
        String outcome = getOutcome(line);
        long end = System.currentTimeMillis();
        System.out.println(testCount + " - Time : " + (end -start));
        w.write("Case #" + testCount + ": " + outcome);
        w.newLine();
        testCount++;
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

  public static String getOutcome(String input) {

    return input;
  }
}
