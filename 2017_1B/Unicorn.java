import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.*;

public class Unicorn {
  static final char EMPTY_CHAR = '0';
  static Boolean finished = false;

  public static void main(String[] args) {

    String pathRoot = "./";
    String relRoot = "B-small-practice.in";
    String in = pathRoot + relRoot;
    //Length of the file extension
    int extLength = 2;
    String out = in.substring(0, in.length() - extLength)+"out";

    BufferedReader r = null;
    BufferedWriter w = null;

    System.out.println(isValid("YVYV".toCharArray()));
    System.out.println(isValid("RYBRBY".toCharArray()));
    System.out.println(isValid("YBRGRB".toCharArray()));
    System.out.println(isValid("YVYVY".toCharArray()));
    System.out.println(isValid("RYBRRBY".toCharArray()));
    System.out.println(isValid("YBRGBRB".toCharArray()));

    try {
      r = new BufferedReader(new FileReader(in));
      w = new BufferedWriter(new FileWriter(out));

      int testCount = 1;
      int totalTests = Integer.valueOf(r.readLine());

      String line;
      while((line = r.readLine()) != null) {
        long start = System.currentTimeMillis();
        String[] inputs = line.split(" ");
        int uCount = Integer.parseInt(inputs[0]);
        int[] uColors = new int[6];

        for (int i = 1; i < inputs.length; i++) {
          uColors[i-1] = Integer.parseInt(inputs[i]);
        }

        String outcome = new String(getArrangement(uCount, uColors));
        outcome = outcome.indexOf(EMPTY_CHAR + "") > -1 ? "IMPOSSIBLE" : outcome;

        long end = System.currentTimeMillis();
        System.out.println(testCount + " - Time : " + (end - start));
        w.write("Case #" + testCount + ": " + outcome);
        w.newLine();
        testCount++;
      }
    }
    catch (IOException e) {
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

  public static char[] getArrangement(int uCount, int[] uColors) {
    // Combinatoric search with DFS backtrack?
    // What does the solution space look like?
    //

    char[] result = new char[uCount];
    for (int i = 0; i < result.length; i++) {
      result[i] = EMPTY_CHAR;
    }

    for (int i = 0; i < uColors.length; i++) {
      if (uColors[i] > 0) {
        result[0] = getColorChar(i);
        uColors[i]--;
        break;
      }
    }

    finished = false;

    fillResult(uColors, result);

    return result;
  }


  public static void fillResult(int[] uColors, char[] result) {
    // dfs with backtrack ?

    // try {
    //   Thread.sleep(1000);
    //   System.out.println(Arrays.toString(result));
    // } catch (Exception e) {}

    int nextSquare = getNextSquare(result);
    if (nextSquare == -1) {
      finished = true;
      return;
    }

    Set<Character> candidateList = getAllPossibleChars(uColors, result, nextSquare);
    // System.out.println(Arrays.toString(candidateList.toArray()));

    for (Character c: candidateList) {
      int colorListPos = getColorListPos(c);
      uColors[colorListPos]--;
      result[nextSquare] = c;

      if (isValid(result)) {
        fillResult(uColors, result);
      }

      if (finished) {
        return;
      }

      uColors[colorListPos]++;
      result[nextSquare] = EMPTY_CHAR;
    }


  }

  public static int getNextSquare(char[] result) {
    for (int i = 0; i < result.length; i++) {
      if (result[i] == EMPTY_CHAR) {
        return i;
      }
    }

    return -1;
  }

  public static Set<Character> getAllPossibleChars(int[] uColors, char[] result, int nextSquare) {
    Set<Character> allCorns = new HashSet<>();

    for (int i = 0; i < uColors.length; i++) {
      if (uColors[i] > 0) {
        allCorns.add(getColorChar(i));
      }
    }

    int before = nextSquare - 1 >= 0 ? nextSquare : result.length - 1;
    int after = (nextSquare + 1) % result.length;

    allCorns.remove(result[before]);
    allCorns.remove(result[after]);

    return allCorns;
  }

  // public static Boolean isFinished(char[] result) {
  //   return isValid(result) && getNextSquare(result) == -1;
  // }

  public static Boolean isValid(char[] result) {
    for (int i = 0; i < result.length; i++) {
      char first = result[i];
      char second = result[i+1 > result.length - 1 ? 0 : i+1];
      if (!allowedPair(first, second, true)) {
        return false;
      }
    }

    return true;
  }

  public static int getColorListPos(char c) {
    switch (c) {
      case 'R': return 0;
      case 'O': return 1;
      case 'Y': return 2;
      case 'G': return 3;
      case 'B': return 4;
      case 'V': return 5;
    }

    return -1;
  }

  public static char getColorChar(int position) {
    switch (position) {
      case 0: return 'R';
      case 1: return 'O';
      case 2: return 'Y';
      case 3: return 'G';
      case 4: return 'B';
      case 5: return 'V';
    }

    return EMPTY_CHAR;
  }

  public static Boolean allowedPair(char c1, char c2, Boolean try1) {
    if (c1 == EMPTY_CHAR || c2 == EMPTY_CHAR) {
      return true;
    }
    if (c1 == c2) {
      return false;
    } else if (c1 == 'O' && (c2 == 'R' || c2 == 'Y')) {
      return false;
    } else if (c1 == 'G' && (c2 == 'Y' || c2 == 'B')) {
      return false;
    } else if (c1 == 'V' && (c2 == 'R' || c2 == 'V')) {
      return false;
    }

    return try1 ? allowedPair(c2, c1, false) : true;
  }
}
class Move {
  int i;
  char oldChar;
  char newChar;

  public Move(int[] uColors, int i, char[] result) {
  }
}
