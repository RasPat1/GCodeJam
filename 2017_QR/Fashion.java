import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Fashion {
  public static void main(String[] args) {

    String pathRoot = "./";
    String relRoot = "test-A-large.in";
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
        //Enter read/write code here
         // dynamic programming?
        String[] inputs = line.split(" ");
        String cakes = inputs[0];
        int paddleSize = Integer.valueOf(inputs[1]);
        long start = System.currentTimeMillis();
        int minFlips = flipSearch(cakes, paddleSize);
        long end = System.currentTimeMillis();
        totalTime += (end - start);

        String outcome = minFlips == -1 ? "IMPOSSIBLE" : minFlips + "";

        w.write("Case #" + caseCount + ": ");
        w.write(outcome);

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

  // Perform breadth first search from original cakeString
  // queue up each new position
  // mark each known position
  // if we can get to a complete position we know it is the shortest path
  // since queueing is breadth first
  public static int flipSearch(String cakes, int paddleSize) {
    int iterationCount = 0;
    if (cakes.indexOf("-") == -1) {
      return 0;
    } else if (isImpossible(cakes, paddleSize)) {
      return -1;
    }

    // memo
    Map<String, Integer> memo = new HashMap<String, Integer>();
    // queue
    Queue<String> q = new LinkedList<>();

    memo.put(cakes, 0);
    q.add(cakes);

    while (!q.isEmpty()) {
      String baseCake = q.poll();
      int flipCount = memo.get(baseCake);
      for (int i = 0; i <= baseCake.length() - paddleSize; i++) {
        String newCake = flip(baseCake, i, paddleSize);
        iterationCount++;
        if (newCake.indexOf("-") == -1) {
          System.out.println(cakes + ": " + iterationCount);
          System.out.println("memoSize" + ": " + memo.size());
          System.out.println("queueSize" + ": " + q.size());
          return flipCount + 1;
        } else if (newCake.indexOf("-") == newCake.lastIndexOf("-")) {
          // do a parity check here?
          return -1; // If there's ever one minus get out its impossible.
        }
        if (memo.containsKey(newCake)) {
          continue;
        } else {
          memo.put(newCake, flipCount+1);
          q.add(newCake);
        }
      }
    }


    System.out.println(cakes + ": " + iterationCount);
    System.out.println("memoSize" + ": " + memo.size());
    System.out.println("queueSize" + ": " + q.size());
    return -1;
  }

  public static String flip(String cakes, int start, int paddleSize) {
    char[] cakeArray = cakes.toCharArray();
    for (int i = start; i < start + paddleSize; i++) {
      if (cakeArray[i] == '-') {
        cakeArray[i] = '+';
      } else if (cakeArray[i] == '+') {
        cakeArray[i] = '-';
      }
    }

    return new String(cakeArray);
  }

  public static Boolean isImpossible(String cake, int paddleSize) {
    String[] subCakes = new String[cake.length() - paddleSize + 1];
    for (int i = 0; i < cake.length() - paddleSize; i++) {
      subCakes[i] = cake.substring(i, i+paddleSize);
    }
    int minusCount = 0;
    for (String subCake : subCakes) {
      minusCount += subCake.split("-").length - 1;
    }

    return minusCount % 2 == 0;
  }

  public static String mapToString(Map<String, Integer> map) {
    StringBuilder sb = new StringBuilder();
    Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
    while (iter.hasNext()) {
        Map.Entry<String, Integer> entry = iter.next();
        sb.append(entry.getKey());
        sb.append('=').append('"');
        sb.append(entry.getValue());
        sb.append('"');
        if (iter.hasNext()) {
            sb.append('\n').append(' ');
        }
    }
    return sb.toString();
  }
}
