import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class Cake {

    static Set<Character>[][] candidates;

    public static void main(String[] args) {
        // char[][] test = {{'G', 'C', 'C'}, {'G', 'C', 'C'}, {'G', 'C', 'J'}};
        // Set<Character> charTest = new HashSet<Character>();
        // charTest.add('G');
        // charTest.add('C');
        // charTest.add('J');
        // System.out.println(isGood(test, charTest));
        long start = System.currentTimeMillis();
        String pathRoot = "./";
        String relRoot = "A-small-practice.in";
        // String relRoot = "A-test.in";
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
            int caseCount = 1;
            while((line = r.readLine()) != null) {
              long loopStart = System.currentTimeMillis();
              String[] sizes = line.split(" ");
              int rowCount = Integer.valueOf(sizes[0]);
              int colCount = Integer.valueOf(sizes[1]);

              char[][] input = new char[rowCount][colCount];

              for (int i = 0; i < rowCount; i++) {
                input[i] = r.readLine().toCharArray();
              }
              Set<Character> allChars = getCharSet(input);
              initCandidates(rowCount, colCount, allChars);
              char[][] outcome = getResult(input);
              clearCandidates();
              w.write("Case #" + caseCount + ": ");

              for (int i = 0; i < outcome.length; i++) {
                w.newLine();
                w.write(new String(outcome[i]));
              }

              long loopEnd = System.currentTimeMillis();
              System.out.println(caseCount + " - Time Taken: " + (loopEnd - loopStart));
              w.newLine();
              caseCount++;
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

      long end = System.currentTimeMillis();
      System.out.println("Time Taken: " + (end - start) + "ms");
    }
    public static char[][] getResult(char[][] input) {
      int[] ij = getNextSquare(input);
      int i = ij[0];
      int j = ij[1];

      Set<Character> candidates = getCandidates(i, j);

      for (Character candidate : candidates) {
        Move lastMove = move(input, i, j, candidate);
        getResult(input)
        if (isGood(input)) {
          return input;
        }
        unmove(input, lastMove);
      }

      return input;
    }

    public static Set<Character> getCandidates(int i, int j) {

    }

    public static void initCandidates(int d1, int d2, Set<Character> chars) {
      candidates = new HashSet<Character>[d1][d2];

      for (int i = 0; i < d1; i++) {
        for (int j = 0; j < d2; j++) {
          candidates[i][j] = new HashSet<Character>(chars);
        }
      }

    }

    public static void clearCandidates() {
      candidates = null;
    }

    public static Boolean isGood(char[][] input, Set<Character> charSet) {
      Map<Character, Integer> rowMapStart = new HashMap<>();
      Map<Character, Integer> rowMapStop = new HashMap<>();

      Map<Character, Integer> colMapStart = new HashMap<>();
      Map<Character, Integer> colMapStop = new HashMap<>();

      for (Integer i = 0; i < input.length; i++) {
        for (Integer j = 0; j < input[i].length; j++) {
          if (input[i][j] == '?') {
            // System.out.println("has Qs");
            return false;
          }

          char dummy = '0';

          char lastRowChar = j-1 >= 0 ? input[i][j-1] : dummy;
          char lastColChar = i-1 >= 0 ? input[i-1][j] : dummy;
          char curChar = input[i][j];

          // System.out.println("lastRowChar" + lastRowChar);
          // System.out.println("lastColChar" + lastColChar);
          // System.out.println("curChar" + curChar);

          Boolean success = true;

          if (lastRowChar != curChar) {
            // System.out.println(success);
            success = success && failOrAdd(rowMapStart, curChar, j);
            // System.out.println(success);
            if (lastRowChar != dummy) {
              // System.out.println("Should fail here");
              // printMap(rowMapStop);
              // System.out.println();
              // printMap(rowMapStart);
              // System.out.println();
              success = success && failOrAdd(rowMapStop, lastRowChar, j - 1);
              // printMap(rowMapStop);
              // System.out.println();
              // printMap(rowMapStart);
              // System.out.println();
            }
          }

          if (lastColChar != curChar) {
            // System.out.println(success);
            success = success && failOrAdd(colMapStart, curChar, i);
            // System.out.println("col check");
            // System.out.println(success);
            if (lastColChar != dummy) {
              success = success && failOrAdd(colMapStop, lastColChar, i - 1);
            }
          }

          if (j == 0) {
            // System.out.println(success);
            // success = success && failOrAdd(rowMapStart, curChar, 0);
          } else if (j == input[i].length - 1) {
            // System.out.println(success);
            success = success && failOrAdd(rowMapStop, curChar, input[i].length - 1);
          }

          if (i == 0) {
            // System.out.println(success);
            // success = success && failOrAdd(colMapStart, curChar, 0);
          } else if (i == input.length - 1) {
            // System.out.println(success);
            success = success && failOrAdd(colMapStop, curChar, input.length - 1);
          }
          // System.out.println(success);
          if (!success) {
            return false;
          }
        }
      }

      return true;
    }

    public static Boolean failOrAdd(Map<Character, Integer> map, char curChar, int expectedIndex) {
      if (map.get(curChar) == null) {
        map.put(curChar, expectedIndex);
      } else if (map.get(curChar) != expectedIndex) {
        return false;
      }

      return true;
    }

    public static Set<Character> getCharSet(char[][] input) {
      Set<Character> charSet = new HashSet<Character>();
      for (int i = 0; i < input.length; i++) {
        for (int j = 0; j < input[i].length; j++) {
          char c = input[i][j];
          if (c != '?') {
            charSet.add(c);
          }
        }
      }

      return charSet;
    }

    public static Move move(char[][] input, int i, int j, char candidate) {
      char prevChar = input[i][j];
      input[i][j] = candidate;
      return new Move(i, j, prevChar, candidate);
    }

    public static void unmove(char[][] input, Move lastMove) {
      input[lastMove.i][lastMove.j] = lastMove.prevChar;
    }

    public static void debug(char[][] input) {
      for (int i = 0; i < input.length; i++) {
        System.out.println(new String(input[i]));
      }
    }

    public static void printMap(Map<Character, Integer> map) {
      Iterator<Map.Entry<Character, Integer>> iter = map.entrySet().iterator();
      while (iter.hasNext()) {
        Map.Entry<Character, Integer> entry = iter.next();
        System.out.print(entry.getKey() + "=" + entry.getValue());
      }
    }
}
class Move {
  int i;
  int j;
  char prevChar;
  char newChar;

  public Move(int i, int j, char prevChar, char newChar) {
    this.i = i;
    this.j = j;
    this.prevChar = prevChar;
    this.newChar = newChar;
  }
}

