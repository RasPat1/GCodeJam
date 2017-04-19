import java.util.*;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Tidy {
  public static void main(String[] args) {

    String pathRoot = "./";
    String relRoot = "B-small-attempt0-real.in";
    String in = pathRoot + relRoot;
    //Length of the file extension
    int extLength = 2;
    String out = in.substring(0, in.length() - extLength)+"out";

    BufferedReader r = null;
    BufferedWriter w = null;

    try {
      r = new BufferedReader(new FileReader(in));
      w = new BufferedWriter(new FileWriter(out));
      int testCases = Integer.parseInt(r.readLine());
      int caseCount = 1;
      String line;
      long totalTime = 0;
      while((line = r.readLine()) != null) {
        long start = System.currentTimeMillis();
        int lastTidyNumber = getLastTidyNumber(Integer.valueOf(line));
        long end = System.currentTimeMillis();
        totalTime += (end - start);

        w.write("Case #" + caseCount + ": " + lastTidyNumber);

        w.newLine();
        caseCount++;
      }
      System.out.println("Time Taken: " + totalTime);
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

  public static int getLastTidyNumber(int n) {
    for (int i = n; i > 0; i--) {
      if (isTidy(i)) {
        return i;
      }
    }

    return 0;
  }

  public static Boolean isTidy(int n) {
    String nStr = String.valueOf(n);
    for (int i = 0; i < nStr.length() - 1; i++) {
      char c1 = nStr.charAt(i);
      char c2 = nStr.charAt(i+1);

      if (c1 > c2) {
        return false;
      }
    }

    return true;
  }
}
