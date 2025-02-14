import java.util.Scanner;

public class Sparkle {

  static String logo =
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
  static String separator = "    ____________________________________________________________\n";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println(separator + "    Hey hey, I'm Sparkle!\n" + separator + "\n" + logo);

    System.out.println(
        separator + "    Got any cool, daring quests or risky biz? Just hit me up!\n" + separator);

    Task[] tasks = new Task[100];
    int taskCount = 0;

    while (true) {
      try {
        String userInput = scanner.nextLine().trim();
        String[] commandParts = userInput.split(" ", 2);
        String command = commandParts[0].toLowerCase();
        String details = commandParts.length > 1 ? commandParts[1].trim() : "";

        switch (command) {
          case "bye":
            System.out.print(separator);
            System.out.println("    See you around, Stelle~ Try to stay out of trouble next time!");
            System.out.println(separator);
            scanner.close();
            return;

          case "list":
            printTaskList(tasks, taskCount);
            break;

          case "mark":
          case "unmark":
            handleMarkTask(tasks, taskCount, details, command.equals("mark"));
            break;

          case "todo":
            if (details.isEmpty())
              throw new SparkleException(SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Todo");
            tasks[taskCount] = new Todo(details);
            printAddedTask(tasks[taskCount++], taskCount);
            break;

          case "deadline":
            String[] deadlineParts = details.split(" /by ", 2);
            if (deadlineParts.length < 2)
              throw new SparkleException(
                  SparkleException.ErrorType.INVALID_FORMAT, "Deadline requires a /by time.");
            tasks[taskCount] = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            printAddedTask(tasks[taskCount++], taskCount);
            break;

          case "event":
            String[] eventParts = details.split(" /from ", 2);
            if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
              throw new SparkleException(
                  SparkleException.ErrorType.INVALID_FORMAT, "Event requires /from and /to time.");
            }
            String[] timeParts = eventParts[1].split(" /to ", 2);
            tasks[taskCount] =
                new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
            printAddedTask(tasks[taskCount++], taskCount);
            break;

          default:
            throw new SparkleException(SparkleException.ErrorType.UNKNOWN_COMMAND, command);
        }
      } catch (SparkleException e) {
        printErrorMessage(e.getMessage());
      }
    }
  }

  private static void printTaskList(Task[] tasks, int taskCount) {
    System.out.print(separator);
    if (taskCount == 0) {
      System.out.println("    Looks like there's nothing fun to mess with... How boring!");
    } else {
      System.out.println("    Here are the tasks in your list~ ");
      for (int i = 0; i < taskCount; i++) {
        System.out.println("    " + (i + 1) + ". " + tasks[i]);
      }
    }
    System.out.println(separator);
  }

  private static void handleMarkTask(Task[] tasks, int taskCount, String userInput, boolean isMark)
      throws SparkleException {
    try {
      int taskNumber = Integer.parseInt(userInput) - 1;
      if (taskNumber < 0 || taskNumber >= taskCount) {
        throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
      }
      if (isMark) {
        tasks[taskNumber].markAsDone();
        System.out.print(separator);
        System.out.println("    Boom! Task's done and dusted~");
      } else {
        tasks[taskNumber].markAsUndone();
        System.out.print(separator);
        System.out.println("    Not done yet, but it's still on the radar!");
      }
      System.out.println("    " + tasks[taskNumber]);
      System.out.print(separator);
    } catch (NumberFormatException e) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
    }
  }

  private static Integer parseTaskIndex(String input, int size) {
    try {
      int taskNumber = Integer.parseInt(input) - 1;
      return (taskNumber >= 0 && taskNumber < size) ? taskNumber : null;
    } catch (NumberFormatException e) {
      return null;
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

  private static void printErrorMessage(String message) {
    System.out.print(separator);
    System.out.println("    Whoops! " + message);
    System.out.println(separator);
  }
}
