import java.io.*;
import java.util.ArrayList;

public class BotTrust {

  public static Integer solveProblem(String line) {
    String[] args = line.split(" ");
    ButtonSeq buttonSeq = new ButtonSeq();
    Integer orderNumber = 0;

    Robot orange = new Robot("O");
    Robot blue = new Robot("B");

    for (int i = 1; i < args.length; i+=2) {
      Target target = new Target();
      target.robotColor = args[i].charAt(0);
      target.buttonPosition = Integer.parseInt(args[i + 1]);
      target.order = orderNumber;
      orderNumber++;
      buttonSeq.add(target);
      if (target.robotColor == 'O') {
        orange.add
      }
    }

    Integer step = 0;


    while (!isDone) {

      for (int j = 0; j < buttonSeq.size(); j++) {
        Target t = buttonSeq.get(j);

        if (t.robotColor == orange.robotColor) {

        }
      }
    }

    while (buttonSeq.size() >= 0) {
      orange.move();
      blue.move();

      if (orange.canPush() && blue.canPush()) {
        // something based on whoose next target is farther
        if (orange.distanceToNextTarget > blue.distanceToNextTarget) {
          orange.push();
        } else {
          blue.push();
        }
      } else {
        orange.push();
        blue.push();
      }
    }




    return 1;
  }

  static class ButtonSeq {
    ArrayList<Target> sequence = new ArrayList<Target>();
    Boolean isBlueDone = false;
    Boolean isOrangeDone = false;

    public Boolean isDone {
        return isBlueDone && isOrangeDone;
    }

    public Integer size {
      reutrn sequence.size();
    }

    public void add(Target target) {
      sequence.add(target);
    }
  }

  static class Target {
    Character robotColor;
    Integer buttonPosition;
    Integer order;

    public String toString() {
      return robotColor + " " + buttonPosition;
    }
  }

  static class Robot {
    Character color;
    Integer position = 1;
    Integer distanceToTarget = null;
    Integer distanceToNextTarget = null;
    ArrayList<String> btnSequence = new ArrayList<String>();

    public Robot(Character color) {
      this.color = color;
    }

    public void add(Target target) {
      btnSequence.add(target);

      if (!distanceToTarget) {
        distanceToTarget = btnSequence.get(0).buttonPosition - position;
      } else if (!distanceToNextTarget && btnSequence.get(1).buttonPosition) {
        distanceToNextTarget = btnSequence.get(1).buttonPosition - position;
      }


    }
  }


  public static void main(String[] args) {
    String fileName = "A-small-practice.in";
    String fileOutputName = fileName + ".out";
    ArrayList<String> lines = loadData(fileName);

    Integer testCaseCount = Integer.parseInt(lines.get(0));
    ArrayList<Integer> solutions = new ArrayList<Integer>();

    for (int i = 1; i < lines.size(); i++) {
      String line = lines.get(i);
      Integer solution = solveProblem(line);
      solutions.add(solution);
    }

    writeSolutions(fileOutputName, solutions);
  }

  public static ArrayList<String> loadData(String fileName) {
    ArrayList<String> lines = new ArrayList<String>();
    String line = null;

    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while ((line = bufferedReader.readLine()) != null) {
        lines.add(line);
      }

      bufferedReader.close();
    } catch(FileNotFoundException e) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch(IOException e) {
      System.out.println("Error reading file '" + fileName + "'");
      e.printStackTrace();
    }

    return lines;
  }

  public static void writeSolutions(String fileOutputName, ArrayList<Integer> solutions) {
    try {
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutputName)));

      for (int i = 0; i < solutions.size(); i++) {
        writer.write("Case #" + i + ": " + solutions.get(i));
        writer.newLine();
      }

      writer.close();
    } catch (IOException e) {
      System.out.println("Error reading file '" + fileOutputName + "'");
    }



  }

}