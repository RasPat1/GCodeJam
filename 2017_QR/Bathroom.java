import java.util.*;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Bathroom {
  public static void main(String[] args) {

    String pathRoot = "./";
    String relRoot = "test-C.in";
    String in = pathRoot + relRoot;
    // Length of the file extension
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
        String[] inputs = line.split(" ");
        int stalls = Integer.valueOf(inputs[0]);
        int people = Integer.valueOf(inputs[1]);
        // XY result = optimizedGetLastStall(stalls, people);
        int a = closedStall(stalls, people);
        // StallSpace lastStall = getLastStall(stalls, people);
        long end = System.currentTimeMillis();
        totalTime += (end - start);

        // String outcome = lastStall.maxSpace + " " + lastStall.minSpace;
        // String outcome = result.max + " " + result.min;
        String outcome = a + "";
        w.write("Case #" + caseCount + ": " + outcome);

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

  public static int closedStall(int stalls, int people) {
    // start by ignoring the first number
    // the second is how many are inserted
    // we're goign to select the power of 2 that it falls under
    // by taking log n (base 2 obvi)
    // that is an important number
    // 1 -> 0
    // 2, 3 -> 1
    // 4, 5, 6, 7 -> 2
    // 8, 9, 10, ... -> 3
    // this represents how many times we split the top or which splitting we are in
    // we are in the n + 1st split
    // for n = 0 we're in the 1st split
    // we konw max number of splits by saying log stalls round up..
    // what we don't konw is the max/min level and the offset
    // 10 1 ->
    // log 10 = 3
    // log 1 = 0
    // 1st split
    // 10 / 2^(n+1) = 5 // kay thats close thats the max how do we get the min
    // log 2 = 1
    // 10 / 4 = 2.5 .. which is close again
    // stalls / 2 ^ (log persons) + 1
    // log 1000 ~ 10 well say 9
    // log 1 = 0
    // 1000 / (2 ^ (1)) = 500 .. cool
    // 1000 /



    // height of the tree
    int height = (int)(Math.log(people) / Math.log(2));
    // now we have height of the tree
    // this means there were 2^(height) - 1 insertions
    // so there are a total of stall - insertions spaces left
    int spacesLeft = stalls - (int)Math.pow(2, height) + 1;
    // now we konw how many spaces are left...
    // they are distributed amongst nodes on this level
    // but how to determine whether its an even split or an uneven split
    int maxPeopleInRow = (int)Math.pow(2, height + 1);
    int split = (int)(spacesLeft / maxPeopleInRow);
    // the round down here is the lower bound
    // that plus one is the upper bound...
    // how do we determine what the split looks like?
    return split;

    // int level = (int) Math.pow(2, logs + 1);
    // int splits = (int)(stalls / level) ;
    // System.out.println("Stalls: " + stalls);
    // System.out.println("People: " + people);
    // System.out.println("Logs: " + logs);
    // System.out.println("splits: " + splits);
    // System.out.println("level: " + level);

    // System.out.println(stalls / (people + 1));

    // How far is teh alst insertion in the level
    // person - level = how far in each row they are

    // We know min and max are within 1 of each other
    // return splits;
  }


  public static XY optimizedGetLastStall(int stalls, int people) {
    int countAtThisSize = 1;
    int min = 0;
    int max = 0;
    int sectionSize = stalls;
    boolean debug = false;

    Queue<Integer> q = new PriorityQueue<Integer>(
      new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
          return b - a;
        }
      });

    q.add(stalls);
    while (people > 0) {
      sectionSize = q.poll();

      sectionSize -= 1; // Inserted a person
      people--;

      if (sectionSize == 0) {
        min = 0;
        max = 0;
      } else {
        min = sectionSize / 2;
        max = sectionSize - min;
        if (max > 0) {
          q.add(max);
        }
        if (min > 0) {
          q.add(min);
        }
      }

      // if (debug) {
        System.out.println("sectionSize: " + sectionSize);
        System.out.println("people: " + people);
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("queueSize: " + q.size());
      // }
    }

    // System.out.println("result: " + min + " " + max);
    return new XY(min, max);
  }

  static class XY {
    int min;
    int max;
    public XY(int min, int max) {
      this.min = min;
      this.max = max;
    }
  }

  public static StallSpace getLastStall(int stalls, int people) {
    StallRow stallRow = new StallRow(stalls);
    StallSpace bestSpace = stallRow.getBestStall();
    Boolean debug = false;
    if (stalls < 100 || people < 100) {
      debug = false;
    }
    if (debug) {
      System.out.println(stallRow);
    }
    while (people > 0) {

      bestSpace = stallRow.getBestStall();
      stallRow.optimizedInsertPerson(bestSpace.index);
      people--;
      if (debug) {
        System.out.println(bestSpace);
        System.out.println(stallRow);
      }
    }

    // bestSpace

    return bestSpace;
  }


}
class StallRow {
  boolean[] stalls;
  StallSpace[] stallSpaces;
  int freeCount;

  public StallRow(int stallCount) {
    stalls = new boolean[stallCount + 2];
    // occupied by the guards

    freeCount = stallCount;
    initStallSpace();
    // stall guards
    insertPerson(0);
    insertPerson(stalls.length - 1);
  }

  public StallSpace getBestStall() {
    int minSpace = Integer.MIN_VALUE;
    int maxSpace = Integer.MIN_VALUE;
    StallSpace bestStall = null;
    for (int i = 0; i < stallSpaces.length; i++) {
      if (stalls[i] == false) { // not occupied
        StallSpace ss = stallSpaces[i];
        if (ss.minSpace > minSpace) {
          minSpace = ss.minSpace;
          maxSpace = ss.maxSpace;
          bestStall = ss;
        } else if (ss.minSpace == minSpace) {
          if (ss.maxSpace > maxSpace) {
            minSpace = ss.minSpace;
            maxSpace = ss.maxSpace;
            bestStall = ss;
          } else if (ss.maxSpace == maxSpace) {
            // use lowest index... already sorted by that since going left to right
          }
        }
      }
    }

    return bestStall;
  }

  public void optimizedInsertPerson(int index) {
    stalls[index] = true;
    // change the spaces that are affected by this insertion only
    // that woudl be go left until we find anotehr full cell
    // and go right until we find aother full cell

    int jRight = index + 1;
    int jLeft = index - 1;

    int rightSpace = 0;
    int leftSpace = 0;

    while(jRight < stalls.length) {
      stallSpaces[jRight].updateLeft(rightSpace);
      if (stalls[jRight]) {
        break;
      }
      rightSpace++;
      jRight++;
    }

    while(jLeft >= 0) {
      stallSpaces[jLeft].updateRight(leftSpace);
      if (stalls[jLeft]) {
        break;
      }
      leftSpace++;
      jLeft--;
    }

    // stallSpaces[index] = new StallSpace(leftSpace, rightSpace, index);
    // Update i -- but we don't even need to update i... since we'll never pull on an already full cell

    freeCount--;
  }

  public void insertPerson(int index) {
    stalls[index] = true;
    for (int i = 0; i < stallSpaces.length; i++) {
      // find nearest left and right neighbors
      int rightSpace = 0;
      int leftSpace = 0;

      int jRight = i + 1;
      int jLeft = i - 1;
      while(jRight < stalls.length && stalls[jRight] == false) {
        rightSpace++;
        jRight++;
      }

      while (jLeft >= 0 && stalls[jLeft] == false) {
        leftSpace++;
        jLeft--;
      }

      stallSpaces[i] = new StallSpace(leftSpace, rightSpace, i);
    }

    freeCount--;
  }

  public void initStallSpace() {
    stallSpaces = new StallSpace[stalls.length];
    for (int i = 0; i < stallSpaces.length; i++) {
      stallSpaces[i] = new StallSpace(i, stallSpaces.length - i - 1, i);
    }
  }

  public String toString() {
    String result = "";
    for (int i = 0; i < stalls.length; i++) {
      if (stalls[i] == true) {
        result += 'o';
      } else {
        result += '.';
      }
    }
    return result;
  }
}

class StallSpace {
  int leftSpace;
  int rightSpace;
  int minSpace;
  int maxSpace;
  int index;

  public StallSpace(int leftSpace, int rightSpace, int index) {
    this.leftSpace = leftSpace;
    this.rightSpace = rightSpace;
    this.index = index;
    this.minSpace = Math.min(leftSpace, rightSpace);
    this.maxSpace = Math.max(leftSpace, rightSpace);
  }

  public void updateRight(int newRight) {
    this.rightSpace = newRight;
    this.minSpace = Math.min(leftSpace, rightSpace);
    this.maxSpace = Math.max(leftSpace, rightSpace);
  }

  public void updateLeft(int newLeft) {
    this.leftSpace = newLeft;
    this.minSpace = Math.min(leftSpace, rightSpace);
    this.maxSpace = Math.max(leftSpace, rightSpace);
  }


  public String toString() {
    return leftSpace + ":" + index + ":" + rightSpace;
  }
}
