import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class LastWord {

  public static void main(String[] args) {

    String pathRoot = "./";
    String relRoot = "A-large-practice.in";
    String in = pathRoot + relRoot;
    //Length of the file extension
    int extLength = 2;
    String out = in.substring(0, in.length() - extLength)+"out";

    BufferedReader r = null;
    BufferedWriter w = null;


    try {
      r = new BufferedReader(new FileReader(in));
      w = new BufferedWriter(new FileWriter(out));
      String line;

      int totalTests = Integer.valueOf(r.readLine());
      int testCount = 1;
      long totalStart = System.currentTimeMillis();

      while((line = r.readLine()) != null) {
        long start = System.currentTimeMillis();
        String outcome = getOutcome(line);
        long end = System.currentTimeMillis();
        System.out.println(testCount + " - Time : " + (end -start));

        w.write("Case #" + testCount + ": " + outcome);
        w.newLine();
        testCount++;
      }

      long totalEnd = System.currentTimeMillis();
      System.out.println("Total Time Taken: " + (totalEnd - totalStart));
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
    // okay
    // input is the word we're goignto get
    // how to determine what the best possible outcome word is
    // initially as always just fuck it and do a simulation!
    List<String> words = new ArrayList<String>();

    for (char c : input.toCharArray()) {
      List<String> newWords = new ArrayList<>();
      if (words.size() == 0) {
        newWords.add("" + c);
      }
      for (String word : words) {
        String beforeWord = c + word;
        String afterWord = word + c;
        newWords.add(beforeWord);
        newWords.add(afterWord);
      }
      Collections.sort(newWords);
      words.clear();
      words.add(newWords.get(newWords.size() - 1));
      // words = newWords;
    }

    String lastWord = getSortedWord(words);

    return lastWord;
  }

  public static String getSortedWord(List<String> words) {
    Collections.sort(words);
    return words.get(words.size() - 1);
  }
}
