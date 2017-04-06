import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
  public final static String FILE_NAME = "tasks";

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Java Todo application\n" +
        "=====================\n" +
        "Command line arguments:\n" +
        " -l   Lists all the tasks\n" +
        " -a   Adds a new task\n" +
        " -r   Removes an task\n" +
        " -c   Completes an task");
    } else if (args[0].equals("-h")) {
      System.out.println("Java Todo application HELP\n" +
        "==========================\n" +
        "Command line arguments:\n" +
        " -l   Lists all the tasks\n" +
        " -a   Adds a new task\n" +
        " -r   Removes an task\n" +
        " -c   Completes an task");
    } else if (args[0].equals("-l")) {
      listAllTasks();
    } else if (args[0].equals("-a")) {
      addNewTask(args);
    } /*else if (args[0].equals("-r")) {
      removeTask(Integer.parseInt(args[1]));
    } else if(args[0].equals("-c")) {
      completeTask();
    }*/
  }

  private static void listAllTasks() {
    Path listAll = Paths.get(FILE_NAME);
    try {
      List<String> taskList = Files.readAllLines(listAll);
      if (taskList.size() == 0) {
        System.out.println("No todos for today!");
      } else {
        for (int i = 0; i < taskList.size(); i++) {
          System.out.println(i + 1 + " - " + isDoneToString(taskList.get(i)) + " " + onlyTask(taskList.get(i)));
        }
      }
    } catch (IOException e) {
      System.out.println("Something wrong with tasks.csv file");
    }
  }

  public static String isDoneToString(String task) {
    String[] subList = task.split(";");
    if (subList.length > 1 && subList[1].equals("done")) {
      return "[X]";
    } else {
      return "[ ]";
    }
  }

  public static String onlyTask(String task) {
    String[] subTasks = task.split(";");
    return subTasks[0];
  }

  public static void addNewTask(String[] task) {
    if (task.length >= 2) {
      String finalTask = convertArrayToString(task);
      Path fileTask = Paths.get(FILE_NAME);
      try {
        List<String> taskList = Files.readAllLines(fileTask);

        taskList.add(finalTask);
        Files.write(fileTask, taskList);
      } catch (IOException e) {
        System.out.println("Something wrong with tasks.csv file");
      }
    } else {
      System.out.println("Task missing\nMain -a <task>");
    }
  }

  private static String convertArrayToString(String[] task) {
    String finalTask = "";
    for (int i = 1; i < task.length; i++) {
      finalTask += " " + task[i];
    }
    finalTask = finalTask.trim();
    return finalTask;
  }

  /*
    private static void completeTask() {
  }

  private static void removeTask(int i) {
  }
*/
}

