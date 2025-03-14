package sparkle.core;

import java.util.Scanner;
import sparkle.exception.SparkleException;
import sparkle.task.Deadline;
import sparkle.task.Event;
import sparkle.task.Task;
import sparkle.task.Todo;

/**
 * The {@code Sparkle} class represents the main chatbot application. It handles user input, task
 * management, and interaction with the storage system.
 */
public class Sparkle {

  private Ui ui;
  private Storage storage;
  private TaskList tasks;

  /**
   * Initializes the Sparkle chatbot with a given file path for task storage.
   *
   * @param filePath The file path where tasks are stored.
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

  /** Runs the chatbot, processing user input in a loop until the "bye" command is given. */
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
                throw new SparkleException(
                    SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Todo");
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
                throw new SparkleException(
                    SparkleException.ErrorType.INVALID_FORMAT,
                    "Need a /by time! Or do you plan to finish it... never?");
              }
              tasks.addTask(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
              ui.showTaskAdded(tasks.getTasks().get(tasks.size() - 1), tasks.size());
              break;
            case "event":
              if (details.isEmpty()) {
                throw new SparkleException(
                    SparkleException.ErrorType.EMPTY_TASK_DESCRIPTION, "Event");
              }
              String[] eventParts = details.split(" /from ", 2);
              if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
                throw new SparkleException(
                    SparkleException.ErrorType.INVALID_FORMAT,
                    "Oops! You forgot the time slots! Gotta add both /from and /to, or this show"
                        + " ain't starting!");
              }
              String[] timeParts = eventParts[1].split(" /to ", 2);
              tasks.addTask(
                  new Event(eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
              ui.showTaskAdded(tasks.getTasks().get(tasks.size() - 1), tasks.size());
              break;
            case "delete":
              int taskToDelete = Integer.parseInt(details) - 1;
              Task removedTask = tasks.deleteTask(taskToDelete);
              ui.showTaskDeleted(removedTask, tasks.size());
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
   * The main entry point for the Sparkle chatbot.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    new Sparkle("data/sparkle.txt").run();
  }
}
