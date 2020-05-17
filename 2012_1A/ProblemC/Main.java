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
        System.out.println(cars[j]);
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


  // Calculate the next "Collision time"
      // At that time switch lanes for car A or Car B
      // If only one could switch lanes, siwtch and continue
      // If both can switch lanes branch into two scenarios and follow those through
  // Calculate From each scenario maximum collision time or "POSSIBLE"
  // return max from total

      // collision times are calcualted pariwise between cars


      // loop through each pair of cars
      // Find the min collision time

      double time = getMaxColTime(cars, -1, -1, new ArrayList<String>(), 0);
      String timeString = (time == Double.MAX_VALUE) ? "POSSIBLE" : String.format("%.6f", time);

      System.out.println("Case #" + i + ": " + timeString);
    }
  }

  public static double getMaxColTime(Car[] cars, int excludeIndex1, int excludeIndex2, List<String> hashes, double timeCount) {
    double minTime = Double.MAX_VALUE;
    int carA = -1;
    int carB = -1;
    String hash = getHash(cars);
    if (hashes.contains(hash)) {
      return timeCount;
    } else {
      hashes.add(hash);
    }

    for (int i = 0; i < cars.length - 1; i++) {
      for (int j = i + 1; j < cars.length; j++) {

        if (i == excludeIndex1 && j == excludeIndex2 ||
            i == excludeIndex2 && j == excludeIndex1) {
          continue;
        }

        double timeToCollision = getColTime(cars[i], cars[j]);
        if (timeToCollision < minTime) {
          carA = i;
          carB = j;
          minTime = timeToCollision;
        }
      }
    }

    if (minTime == Double.MAX_VALUE) {
      return Double.MAX_VALUE; // Stable system. No collisions!
    } else {
      System.out.println("uhoh: " + minTime);
      updateCars(cars, minTime);
      timeCount += minTime;
    }

    if (canChange(cars, carA) && canChange(cars, carB)) {
      Car[] carBranch = copyCars(cars);
      cars[carA].switchLanes();
      carBranch[carB].switchLanes();
      double branch1 = getMaxColTime(cars, carA, carB, hashes, timeCount);
      double branch2 = getMaxColTime(carBranch, carA, carB, hashes, timeCount);
      double maxTime = branch1 > branch2 ? branch1 : branch2;
      return maxTime;
      // return getMaxColTime(cars, hashes, timeCount);
    } else if (canChange(cars, carA)) {
      cars[carA].switchLanes();
      double maxTime = getMaxColTime(cars, carA, carB, hashes, timeCount);
      return maxTime > minTime ? maxTime : minTime;
    } else if (canChange(cars, carB)) {
      cars[carB].switchLanes();
      double maxTime = getMaxColTime(cars, carA, carB, hashes, timeCount);
      return maxTime > minTime ? maxTime : minTime;
    } else { // neither can change
      return minTime;
    }

  }

  public static double getColTime(Car carA, Car carB) {
    double time = Double.MAX_VALUE;

    if (carA.pos < carB.pos && carA.speed > carB.speed ||
        carB.pos < carA.pos && carB.speed > carA.speed) {
      time = Math.abs(Car.CAR_SIZE - (Math.abs(carA.pos - carB.pos))) / Math.abs(carA.speed - carB.speed);
    }

    return time;
  }

  public static void updateCars(Car[] cars, double time) {
    for (int i = 0; i < cars.length; i++) {
      Car car = cars[i];
      car.pos += car.speed * time;
    }
  }

  public static boolean canChange(Car[] cars, int carIndex) {
    boolean canChange = true;
    Car car = cars[carIndex];

    for (int i = 0; i < cars.length; i++) {
      if (i == carIndex) {
        continue;
      }

      Car otherCar = cars[i];
      if (Math.abs(otherCar.pos - car.pos) < Car.CAR_SIZE) {
        canChange = false;
      }
    }

    return canChange;
  }

  public static Car[] copyCars(Car[] cars) {
    Car[] copy = new Car[cars.length];

    for (int i = 0; i < cars.length; i++) {
      Car oldCar = cars[i];
      Car newCar = new Car(oldCar.lane, oldCar.speed, oldCar.pos);
      copy[i] = newCar;
    }

    return copy;
  }

  public static String getHash(Car[] cars) {
    String result = "";

    for (int i = 0; i < cars.length; i++) {
      Car car = cars[i];
      result += car.lane + "_" + car.speed + "_" + car.pos;
    }

    return result;
  }

}

class Car {
  char lane;
  double speed;
  double pos;
  static final int CAR_SIZE = 5;

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

  public void switchLanes() {
    if (lane == 'R') {
      lane = 'L';
    } else if (lane == 'L') {
      lane = 'R';
    }
  }
}