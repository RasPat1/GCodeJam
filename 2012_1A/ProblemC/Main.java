import java.util.*;
import java.io.*;
public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int i = 1; i <= t; ++i) {
      int carCount = in.nextInt();
      Car[] cars = new Car[carCount];
      for (int j = 0; j < carCount; j++) {
        char lane = in.next().charAt(0);
        double speed = in.nextDouble();
        double pos = in.nextDouble();
        cars[j] = new Car(lane, speed, pos);
        // System.out.println(cars[j]);
      }
// FIrst come up with basic properties and details of this system
      // if speed1 > speed2 && pos2 > pos1 then car1 and car2 must eventually cross
      // A cross either means drive past eachother in neighbouring lanes or crashing
      // A system is stable if the sorting the cars by position or sorting by speed leads to same ordering
      // Thats a weird way of saying if the faster cars are in the front and slower
      // in the back theyre not goign to hit each other
      // Possible approaches
        // Caluclate all Pairwise "Crosses" if >2 cars with same cross Time and pos they will crash
        // Can model road and driving using an effective lane switching strategy

// Think of this in algorithm types
      // Is it greedy?
      //





      System.out.println("Case #" + i + ": " + (i * 2) + " " + (i * i));
    }
  }


}

class Car {
  char lane;
  double speed;
  double pos;

  public Car(char lane, double speed, double pos) {
    this.lane = lane;
    this.speed = speed;
    this.pos = pos;
  }

  public String toString() {
    String result = "";
    result += lane + " " + speed + " " + pos;
    return result;
  }
}