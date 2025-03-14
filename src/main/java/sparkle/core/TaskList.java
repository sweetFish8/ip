package sparkle.core;

import java.util.ArrayList;
import sparkle.exception.SparkleException;
import sparkle.task.Task;

/**
 * The {@code TaskList} class manages a collection of tasks. It provides methods to add, remove, and
 * modify tasks.
 */
public class TaskList {

  private ArrayList<Task> tasks;

  /** Constructs an empty {@code TaskList}. */
  public TaskList() {
    tasks = new ArrayList<>();
  }

  /**
   * Constructs a {@code TaskList} with the given list of tasks.
   *
   * @param tasks The initial list of tasks.
   */
  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Adds a task to the list.
   *
   * @param task The task to be added.
   */
  public void addTask(Task task) {
    tasks.add(task);
  }

  /**
   * Deletes a task from the list.
   *
   * @param taskNumber The index of the task to be deleted.
   * @return The deleted task.
   * @throws SparkleException If the task number is invalid.
   */
  public Task deleteTask(int taskNumber) throws SparkleException {
    if (taskNumber < 0 || taskNumber >= tasks.size()) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
    }
    Task deleteTask = tasks.get(taskNumber);
    tasks.remove(taskNumber);
    return deleteTask;
  }

  /**
   * Marks a task as done.
   *
   * @param taskNumber The index of the task to be marked as done.
   * @throws SparkleException If the task number is invalid.
   */
  public void markTaskAsDone(int taskNumber) throws SparkleException {
    if (taskNumber < 0 || taskNumber >= tasks.size()) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
    }
    tasks.get(taskNumber).markAsDone();
  }

  /**
   * Marks a task as undone.
   *
   * @param taskNumber The index of the task to be marked as undone.
   * @throws SparkleException If the task number is invalid.
   */
  public void markTaskAsUndone(int taskNumber) throws SparkleException {
    if (taskNumber < 0 || taskNumber >= tasks.size()) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_TASK_NUMBER, "");
    }
    tasks.get(taskNumber).markAsUndone();
  }

  /**
   * Returns the list of tasks.
   *
   * @return The list of tasks.
   */
  public ArrayList<Task> getTasks() {
    return tasks;
  }

  /**
   * Returns the number of tasks in the list.
   *
   * @return The number of tasks.
   */
  public int size() {
    return tasks.size();
  }
}
