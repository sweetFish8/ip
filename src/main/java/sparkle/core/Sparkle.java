package sparkle.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import sparkle.exception.SparkleException;
import sparkle.task.Deadline;
import sparkle.task.Event;
import sparkle.task.Task;
import sparkle.task.Todo;

public class Sparkle {

  private static String logo =
      "NNNNNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNNNNN\n"
          + "NMNNMHXXyVVyyHqqqqkHHmWggH4k<<>>>>??1C?Tz111lOwXWMHgHMM@@@@@@@@NXMNNMN\n"
          + "NNMMSZXUUU0VOIWHkkkkkkHHHlXH2<;;;<<+J~..uvx1zZUWHMHHHMMM@@@@@@@@NXMMNN\n"
          + "NHZOvz1<?>><jWQHMHHHHHHWHUkbH<(+XHHmK _.dNe1+<<<1vMWg@HMHM@@@@@@@gHkkM\n"
          + "NHz>>>>>;>;jHMMHHMMHHngHHbkWMMHH@MMMb  .dHMs?WmaJJHpHWWMNZzOUUWHHHggkM\n"
          + "#Hz>>>>>;<dHHHHkqqHH@gMHmMHMMH@@H@@@b   dHH@<<7MBTWHpHHHHr__~<<jkOXHUM\n"
          + "NHz>>>;;;(MMNqkkkkqHHHHWHHH@@@@MMMM@N.  d@MI<~(?>`` ?WHHHN<;<(JHHd@KzM\n"
          + "#Hz>;;;<<JMHmmmmgHHXU6dHMHMMMHH@@@@@M[ .jM@m-_(c`````.XWHHR+dHH@@gHDjM\n"
          + "#Hz;;+Jd@gg@@HgqH9IdqHMBWgMHHM@@@@@@@N..(d@H97C `   ``?0VHMHWfjHHbWHXM\n"
          + "NH>::~~<<dMH@HpfWXdkB6gM@@HH@gHMM@H@@@L._jY=j!      `(uo-vWW#1HWWHgMKM\n"
          + "#H>:~~~:~JMgHppWWWY(dM@MHHH@@MMMHM@@@@N-<(v7<-__    `.-~_(dHkWbpbbbbSM\n"
          + "NH>~~:~~~(MmHWWY=(WWHM#XXMH@@H@@@@@@g@@Nv!           (61j<JHHH/HHHWHkM\n"
          + "NK<~~~~~~(MHKY-(gMH@HNQmwwHHM@@@@@@ggg@H{`` .     `````.??XWMkkWHkHHWM\n"
          + "NK<~~~~~~(#6JMMMWH@@@H@MQHXAdMH@@@@gggggr` __ `` ` (...`...WHHHHbHH@KM\n"
          + "NH<~~...(GdHMMMQ@M@@@@@HMMH@@HZHUH@@@gHgR  (::_-...-(OUG. ~dHHHHbbbMKM\n"
          + "#H<~~~_dH9HkWWGH@@@@@@@MHHHM@NwRAyVMWHXHHXK4kvWmQKYY"
          + "YW>.~dWWHgHHWWKM\n"
          + "#K>~~_jY.(HHWXM@@Mggg@HHgB&HgmgHmmkwXNSWHHWvWYTk-..______~~JMHHggggqHM\n"
          + "NK!_..%._(kHWM@@MgmggHH9! (HMHHqqqqgHHHHWHHkUkU9>~~~~~~~~~~(MWHHgggkWM\n"
          + "NK_...~.-dHdM@MMgmmgHHm...J0kWHkppkHmHHkHHUKHWm.......-((-_(HNVWHmgHSM\n"
          + "NH<...~-JWMHMHHggqgHHYUMMHHwHWHpffppHWpbbbod@g@@M@@@HHMWNmazuHffpHMHXM\n"
          + "#H<jJ+dk@HHHHMMHHqqK4-,BvNdMHKWVyVfKwfffWbr?WMH@M@g@HM99UHHNyXpppbH#4M\n"
          + "#H<__<UHMMgNWWg@HqHb <_U<_jf?~_7UWWD(WWfkXP (H@@@@gM@#_.+jWHNWfpbbW%jM\n"
          + "NHmJdHWWWgHgqb@@HqmS```., ``  `   _~(WqHWHHaJJ@H@@@MHK+j&dHHHHppkHH~jM\n"
          + "NHWbWHmHH@HHHWM@MqHm_`````` `` `     ,9vKWHHHHHHg@MgHWkHZHHHHMkpqHE.jM\n"
          + "#HudHgmH@MggHXM@@gH(l`````` ``` ````` <-(Z=.VWkggMqHNpWHkXWHHMMHqW$.jM\n"
          + "NHH@HHHMHggHV>JM@gH:?-``````` `_` `` .! __?~(HgHHkqqkkqHkXfppWHMMMh-jM\n"
          + "#HHgmKMHpHHWI;<WHmMx;z< ` _ `` ``` ````` ..dHHHpbHfWkqmHKHpfpWWkqH}.jM\n"
          + "NHHmK<?NKWDdc::?MggN(;:1,````` . `````.?j9C(WbbWHf`dpkqHMWffpkHkqH).jM\n"
          + "#HWD:<`(HHRvk<:;?MgYS+<gDi.````````  ..v<:(XbbWqK_.(pbHHHHWfpqHbqqb jM\n"
          + "NHI<<.<~/HqHgk1<;?WHU+<WI:1-...(J&dVYjD:;<dkWmHY`  dpH'(dHkkWHmHqHW|(M\n"
          + "#HI:(<11--UHgHHz+;<7W& Wc<+u0Z?7<<::j9<<idqqH#^    WK?i.HWfVffHHHHNNdM\n"
          + "NHnJZ=+WHkldHkbWZ!```__Xxw>_ jc;;<1d$-.dHHqH$ ``` JY-JWXWVfffWkUUWMMRM\n"
          + "NHGdCldgHSXHkWXHR `````jk1<_(jr``.V!?WHH9Y>`` .``` .dpqWbHkWfudkHqTAwM\n"
          + "NMNmkyd@HZXHkHww$``````.Wz7Iv!`.J=?TYY>:<~``` ..`` JHWHWHpVWIdHsvXqNMN\n"
          + "NNNNNkdMHXXHWH2uC```````jL```.JY` -_~~`````` -`.``.HmHHfpWWWRdWVwW#NNN\n"
          + "NMNNNNmQmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmdNNNMN";

  private static final String FILE_PATH = "../data/sparkle.txt";
  private static final String DIRECTORY_PATH = "../data";

  private static final String separator =
      "    ____________________________________________________________\n";

  public static void main(String[] args) {
    new Sparkle().run();
  }

  private void run() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(separator + "    Hey hey, I'm Sparkle!\n" + separator + logo);
    System.out.println(
        separator + "    Got any cool, daring quests or risky biz? Just hit me up!\n" + separator);

    ArrayList<Task> tasks = new ArrayList<>();
    try {
      tasks = loadFile();
      System.out.println("    Tasks loaded successfully!");
    } catch (SparkleException e) {
      System.out.println("    Failed to load tasks: " + e.getMessage());
    }

    while (true) {
      try {
        String userInput = scanner.nextLine().trim();
        String[] commandParts = userInput.split(" ", 2);
        String command = commandParts[0].toLowerCase();
        String details = commandParts.length > 1 ? commandParts[1].trim() : "";

        switch (command) {
          case "bye":
            saveTasks(tasks);
            System.out.print(separator);
            System.out.println("    See you around, Stelle~ Try to stay out of trouble next time!");
            System.out.println(separator);
            scanner.close();
            return;

          case "list":
            printTaskList(tasks, tasks.size());
            break;

          case "mark":
            handleMarkTask(tasks, details, command.equals("mark"));
            saveTasks(tasks);
            break;
          case "unmark":
            handleMarkTask(tasks, details, command.equals("mark"));
            saveTasks(tasks);
            break;

          case "todo":
            if (details.isEmpty())
              throw new SparkleException(SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Todo");
            tasks.add(new Todo(details));
            printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
            saveTasks(tasks);
            break;

          case "deadline":
            if (details.isEmpty())
              throw new SparkleException(
                  SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Deadline");
            String[] deadlineParts = details.split(" /by ", 2);
            if (deadlineParts.length < 2)
              throw new SparkleException(
                  SparkleException.ErrorType.INVALID_FORMAT,
                  "Need a /by time! Or do you plan to finish it... never?");
            tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
            saveTasks(tasks);
            break;

          case "event":
            if (details.isEmpty())
              throw new SparkleException(
                  SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Event");
            String[] eventParts = details.split(" /from ", 2);
            if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
              throw new SparkleException(
                  SparkleException.ErrorType.INVALID_FORMAT,
                  "Oops! You forgot the time slots! Gotta add both /from and /to, or this show"
                      + " ain't starting!");
            }
            String[] timeParts = eventParts[1].split(" /to ", 2);
            tasks.add(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
            printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
            saveTasks(tasks);
            break;

          case "delete":
            deleteTask(tasks, details);
            saveTasks(tasks);
            break;

          default:
            throw new SparkleException(SparkleException.ErrorType.UNKNOWN_COMMAND, command);
        }
      } catch (SparkleException e) {
        System.out.println(separator);
        System.out.println(e.getMessage());
        System.out.println(separator);
      }
    }
  }

  private static void printTaskList(ArrayList<Task> tasks, int taskCount) {
    System.out.print(separator);
    if (taskCount == 0) {
      System.out.println("    Looks like there's nothing fun to mess with... How boring!");
    } else {
      System.out.println("    Here are the tasks in your list~ ");
      for (int i = 0; i < tasks.size(); i++) {
        System.out.println("    " + (i + 1) + ". " + tasks.get(i));
      }
    }
    System.out.println(separator);
  }

  private static void handleMarkTask(ArrayList<Task> tasks, String userInput, boolean isMark)
      throws SparkleException {
    try {
      int taskNumber = Integer.parseInt(userInput) - 1;
      if (taskNumber < 0 || taskNumber >= tasks.size()) {
        throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
      }
      if (isMark) {
        tasks.get(taskNumber).markAsDone();
        System.out.print(separator);
        System.out.println("    Boom! Task's done and dusted~");
      } else {
        tasks.get(taskNumber).markAsUndone();
        System.out.print(separator);
        System.out.println("    Not done yet, but it's still on the radar!");
      }
      System.out.println("    " + tasks.get(taskNumber));
      System.out.println(separator);
    } catch (NumberFormatException e) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
    }
  }

  private static void printAddedTask(Task task, int taskCount) {
    System.out.print(separator);
    System.out.println("    Let's make it fun! I've added this task:");
    System.out.println("      " + task);
    System.out.println(
        "    Looks like you've got " + taskCount + " tasks in your list~ Better get moving!");
    System.out.println(separator);
  }

  private static void deleteTask(ArrayList<Task> tasks, String userInput) throws SparkleException {
    try {
      int taskNumber = Integer.parseInt(userInput) - 1;
      if (taskNumber < 0 || taskNumber >= tasks.size()) {
        throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
      }
      Task removedTask = tasks.remove(taskNumber);
      System.out.print(separator);
      System.out.println("    Got it! Poof! This task is gone:");
      System.out.println("      " + removedTask);
      System.out.println("    Look at that! You've got " + tasks.size() + " tasks left to juggle!");
      System.out.println(separator);
    } catch (NumberFormatException e) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
    }
  }

  private void saveTasks(ArrayList<Task> tasks) {
    try {
      new File(DIRECTORY_PATH).mkdirs();
      BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
      for (Task task : tasks) {
        writer.write(task.toFileFormat() + "\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("    Task list just went poof! Try saving again!s");
    }
  }

  public static ArrayList<Task> loadFile() throws SparkleException {
    ArrayList<Task> newtasks = new ArrayList<>();
    File file = new File(FILE_PATH);

    if (!file.exists()) {
      return newtasks;
    }

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(" \\| ");
        newtasks.add(Task.fromFileFormat(parts));
      }
    } catch (FileNotFoundException e) {
      throw new SparkleException(
          SparkleException.ErrorType.INVALID_FORMAT,
          "That file's playing hide and seek… and winning!");
    }
    printTaskList(newtasks, newtasks.size());
    return newtasks;
  }
}
