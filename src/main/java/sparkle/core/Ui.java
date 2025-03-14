package sparkle.core;

import java.util.ArrayList;
import sparkle.exception.SparkleException;
import sparkle.task.Task;

/**
 * The {@code Ui} class handles user interactions by displaying messages and task information to the
 * console.
 */
public class Ui {

  private static final String separator =
      "    ____________________________________________________________\n";
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

  /** Displays the Sparkle logo. */
  public void showLogo() {
    System.out.println(separator + "    Hey hey, I'm Sparkle!\n" + separator + logo);
  }

  /** Displays the greeting message. */
  public void showGreeting() {
    System.out.println(
        separator + "    Got any cool, daring quests or risky biz? Just hit me up!\n" + separator);
  }

  /** Displays a message indicating that tasks have been successfully loaded. */
  public void showLoadingSuccess() {
    System.out.println(
        separator
            + "    Look at that! Your tasks made it back in one piece—miraculous!\n"
            + separator);
  }

  /** Displays an error message when loading tasks fails. */
  public void showLoadingError() {
    System.out.println(separator + "    Your tasks took a wrong turn and got lost...Tragic!");
  }

  /**
   * Displays the list of tasks.
   *
   * @param tasks The list of tasks to display.
   * @param taskCount The number of tasks in the list.
   */
  public void showTaskList(ArrayList<Task> tasks, int taskCount) {
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

  /**
   * Displays a message confirming that a task has been added.
   *
   * @param task The task that was added.
   * @param taskCount The total number of tasks after adding the new task.
   */
  public void showTaskAdded(Task task, int taskCount) {
    System.out.print(separator);
    System.out.println("    Let's make it fun! I've added this task:");
    System.out.println("      " + task);
    System.out.println(
        "    Looks like you've got " + taskCount + " tasks in your list~ Better get moving!");
    System.out.println(separator);
  }

  /**
   * Displays a message confirming that a task has been marked as done.
   *
   * @param task The task that was marked as done.
   */
  public void showMarkTaskDone(Task task) {
    System.out.println(separator);
    System.out.println("    Boom! Task's done and dusted~");
    System.out.println("      " + task);
    System.out.println(separator);
  }

  /**
   * Displays a message confirming that a task has been marked as undone.
   *
   * @param task The task that was marked as undone.
   */
  public void showMarkTaskUndone(Task task) {
    System.out.println(separator);
    System.out.println("    Not done yet, but it's still on the radar!");
    System.out.println("      " + task);
    System.out.println(separator);
  }

  /**
   * Displays a message confirming that a task has been deleted.
   *
   * @param removedTask The task that was deleted.
   * @param remainingTasksCount The number of remaining tasks after deletion.
   */
  public void showTaskDeleted(Task removedTask, int remainingTasksCount) {
    System.out.println(separator);
    System.out.println("    Got it! Poof! This task is gone:");
    System.out.println("      " + removedTask);
    System.out.println(
        "    Look at that! You've got " + remainingTasksCount + " tasks left to juggle!");
    System.out.println(separator);
  }

  /**
   * Displays an error message.
   *
   * @param e The exception containing the error message.
   */
  public void showError(SparkleException e) {
    System.out.println(separator);
    System.out.println(e.getMessage());
    System.out.println(separator);
  }

  /** Displays the exit message. */
  public void showExitMessage() {
    System.out.print(separator);
    System.out.println("    See you around, Stelle~ Try to stay out of trouble next time!");
    System.out.println(separator);
  }

/**
 * Displays tasks that match a search query.
 *
 * @param matchingTasks The list of tasks that match the search criteria.
 */
  public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println(separator);
            System.out.println("    Nothing here! Did you imagine it?");
            System.out.println(separator);
        } else {
            System.out.println(separator);
            System.out.println("    Look what we've got! Your matching tasks:");
            int index = 1;
            for (Task task : matchingTasks) {
                System.out.println("    " + index + ". " + task);
                index++;
            }
            System.out.println(separator);
        }
    }
}
