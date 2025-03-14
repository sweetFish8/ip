package sparkle.core;

import sparkle.task.Deadline;
import sparkle.task.Event;
import sparkle.task.Task;
import sparkle.task.Todo;
import sparkle.exception.SparkleException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the main logic of the Sparkle application, which
 * manages tasks.
 * It interacts with the user, processes commands, and updates the task list
 * accordingly.
 */
public class Sparkle {

  private Ui ui;
  private Storage storage;
  private TaskList tasks;

  /**
   * Initializes the Sparkle application with the given file path.
   * It sets up the UI, storage, and loads the task list from the specified file.
   * If loading the tasks fails, it initializes an empty task list.
   *
   * @param filePath The file path to load the tasks from.
   */
  public Sparkle(String filePath) {
    ui = new Ui();
    storage = new Storage();
    try {
      tasks = new TaskList(storage.load());
      ui.showLoadingSuccess();
    } catch (SparkleException e) {
      ui.showLoadingError();
      System.out.println("    Failed to load tasks: " + e.getMessage());
      tasks = new TaskList();
    }
  }

  /**
   * Runs the Sparkle application, continuously accepting user input, processing
   * commands,
   * and displaying relevant information. The application exits when the user
   * types the "bye" command.
   */
  public void run() {
    ui.showLogo();
    ui.showGreeting();

    Scanner scanner = new Scanner(System.in);

    try {
      while (true) {
        String userInput = scanner.nextLine().trim();
        String[] commandParts = Parser.parse(userInput);
        String command = commandParts[0];
        String details = commandParts[1];

        try {
          switch (command) {
            case "bye":
              storage.save(tasks.getTasks());
              ui.showExitMessage();
              return;
            case "list":
              ui.showTaskList(tasks.getTasks(), tasks.size());
              break;
            case "mark":
              tasks.markTaskAsDone(Integer.parseInt(details) - 1);
              ui.showMarkTaskDone(tasks.getTasks().get(Integer.parseInt(details) - 1));
              break;
            case "unmark":
              tasks.markTaskAsUndone(Integer.parseInt(details) - 1);
              ui.showMarkTaskUndone(tasks.getTasks().get(Integer.parseInt(details) - 1));
              break;
            case "todo":
              if (details.isEmpty()) {
                throw new SparkleException(SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Todo");
              }
              tasks.addTask(new Todo(details));
              ui.showTaskAdded(tasks.getTasks().get(tasks.size() - 1), tasks.size());
              break;
            case "deadline":
              if (details.isEmpty()) {
                throw new SparkleException(SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "");
              }
              String[] deadlineParts = details.split(" /by ", 2);
              if (deadlineParts.length < 2) {
                throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT,
                    "Need a /by time! Or do you plan to finish it... never?");
              }
              tasks.addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
              ui.showTaskAdded(tasks.getTasks().get(tasks.size() - 1), tasks.size());
              break;
            case "event":
              if (details.isEmpty()) {
                throw new SparkleException(SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Event");
              }
              String[] eventParts = details.split(" /from ", 2);
              if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
                throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT,
                    "Oops! You forgot the time slots! Gotta add both /from and /to, or this show ain't starting!");
              }
              String[] timeParts = eventParts[1].split(" /to ", 2);
              tasks.addTask(new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
              ui.showTaskAdded(tasks.getTasks().get(tasks.size() - 1), tasks.size());
              break;
            case "delete":
              int taskToDelete = Integer.parseInt(details) - 1;
              Task removedTask = tasks.deleteTask(taskToDelete);
              ui.showTaskDeleted(removedTask, tasks.size());
              break;
            case "find":
              if (details.isEmpty()) {
                throw new SparkleException(SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Find");
              }
              // findコマンドに基づき、キーワードでタスクを検索
              ArrayList<Task> matchingTasks = tasks.findTasks(details);
              ui.showMatchingTasks(matchingTasks);
              break;
            default:
              throw new SparkleException(SparkleException.ErrorType.UNKNOWN_COMMAND, command);
          }
        } catch (SparkleException e) {
          ui.showError(e);
        }
      }
    } finally {
      scanner.close();
    }
  }

  /**
   * The entry point of the Sparkle application. It initializes the application
   * with the file path
   * and starts the application by calling the `run()` method.
   *
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {
    new Sparkle("data/sparkle.txt").run();
  }
}