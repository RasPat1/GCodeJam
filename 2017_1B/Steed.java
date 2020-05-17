import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Steed {

  public static void main(String[] args) {

    String pathRoot = "./";
    String relRoot = "A-large.in";
    String in = pathRoot + relRoot;
    //Length of the file extension
    int extLength = 2;
    String out = in.substring(0, in.length() - extLength)+"out";

    BufferedReader r = null;
    BufferedWriter w = null;

    try {
      r = new BufferedReader(new FileReader(in));
      w = new BufferedWriter(new FileWriter(out));

      int testCount = 1;
      int totalTests = Integer.valueOf(r.readLine());

      String line;
      while((line = r.readLine()) != null) {
        long start = System.currentTimeMillis();
        String[] inputs = line.split(" ");
        long destination = Long.valueOf(inputs[0]);
        int horseCount = Integer.valueOf(inputs[1]);
        int[][] horses = new int[horseCount][2];
        for (int i = 0; i < horseCount; i++) {
          String[] horseData = r.readLine().split(" ");
          horses[i][0] = Integer.parseInt(horseData[0]); // position
          horses[i][1] = Integer.parseInt(horseData[1]); // speed
        }

        double speed = getSpeed(horses, destination);

        NumberFormat formatter = new DecimalFormat("#0.000000");
        String outcome = formatter.format(speed);

        long end = System.currentTimeMillis();
        System.out.println(testCount + " - Time : " + (end - start));
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

  public static double getSpeed(int[][] horses, long destination) {
    // key insight 1: Horses don't care about anythign behind them
    // ey insight 2: Horses can only slow down
    // sort horses by position
    // start with horse in front (end of array farthest horse)
    // determine time till he reaches destination
    // go to horse behind him
    // calculate naive time to destination
    // if its larger than horse in front of him no change
    // if its smaller (he reaches faster) he will reach at same time as net guy!
    // go until we find destination time of horse directly in front of us
    // that is the time it will take us to reach destination
    // divide by distance to get speed

    // sort horses by length
    for (int i = 0; i < horses.length - 1; i++) {
      for (int j = i + 1; j < horses.length; j++) {
        if (horses[i][0] > horses[j][0]) {
          int[] tmp = new int[2];
          tmp = horses[i];
          horses[i] = horses[j];
          horses[j] = tmp;
        }
      }
    }

    double[] time = new double[horses.length];

    for (int i = time.length - 1; i >= 0; i--) {
      double horseInFrontFinishTime = 0;
      if (i + 1 < time.length) {
        horseInFrontFinishTime = time[i+1];
      }

      double timeRemaining = (double)(destination - horses[i][0]) / (double)horses[i][1];
      timeRemaining = timeRemaining > horseInFrontFinishTime ? timeRemaining : horseInFrontFinishTime;
      time[i] = timeRemaining;
    }

    if (time[0] == 0) {
      System.out.println(destination);
      for (int i = 0; i < horses.length; i++) {
        System.out.println(horses[i][0] + ":" + horses[i][1]);
        System.out.println(i + ":" + time[i]);
      }
    }
    return destination / time[0];
  }
}
