package sparkle.task;

import sparkle.exception.SparkleException;

/**
 * Represents a task with a description and completion status.
 * This class provides functionality for marking a task as done or undone,
 * converting it to and from a file format, and retrieving the task's string
 * representation.
 */
public class Task {
  protected String description;
  protected boolean isDone;

  /**
   * Constructs a Task object with the specified description.
   * The task is initially marked as not done.
   * 
   * @param description A brief description of the task.
   */
  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * Constructs a Task object with the specified description and completion
   * status.
   * 
   * @param description A brief description of the task.
   * @param isDone      A boolean indicating whether the task is marked as done.
   */
  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  /**
   * Returns the status icon of the task, representing whether the task is done or
   * not.
   * 
   * @return A string representing the task's completion status ("X" for done, " "
   *         for undone).
   */
  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  public String getDescription() {
    return description;
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  /**
   * Converts the Task object into a string format suitable for saving to a file.
   * 
   * @return A string representation of the task in a format suitable for storage.
   */
  public String toFileFormat() {
    return (isDone ? "1" : "0") + " | " + description;
  }

  /**
   * Creates a Task object from a string array formatted for file loading.
   * 
   * @param parts A string array containing task data in the format [type, done,
   *              description].
   * @return A Task object initialized with the data from the array.
   * @throws SparkleException If the input data is in an invalid format.
   */
  public static Task fromFileFormat(String[] parts) throws SparkleException {
    char type = parts[0].charAt(0);

    switch (type) {
      case 'T':
        return Todo.fromFileFormat(parts);
      case 'D':
        return Deadline.fromFileFormat(parts);
      case 'E':
        return Event.fromFileFormat(parts);
      default:
        throw new SparkleException(
            SparkleException.ErrorType.INVALID_FORMAT,
            "Oops! This task data's a mess—good luck fixing that!");
    }
  }

  /**
   * Returns a string representation of the task, including its status and
   * description.
   * 
   * @return A string representation of the task in the format "[status]
   *         description".
   */
  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }
}