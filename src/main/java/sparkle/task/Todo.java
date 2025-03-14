package sparkle.task;

import sparkle.exception.SparkleException;

/**
 * Represents a Todo task with a description and completion status.
 * Inherits from the Task class and includes functionality for handling Todo
 * tasks.
 */
public class Todo extends Task {

  /**
   * Constructs a Todo object with the specified description.
   * The task is initially marked as not done.
   * 
   * @param description A brief description of the task.
   */
  public Todo(String description) {
    super(description);
  }

  /**
   * Constructs a Todo object with the specified description and completion
   * status.
   * 
   * @param description A brief description of the task.
   * @param isDone      A boolean indicating whether the task is marked as done.
   */
  public Todo(String description, boolean isDone) {
    super(description, isDone);
  }

  /**
   * Converts the Todo task into a string format suitable for saving to a file.
   * 
   * @return A string representation of the Todo task in a format suitable for
   *         storage.
   */
  @Override
  public String toFileFormat() {
    return "T | " + super.toFileFormat();
  }

  /**
   * Creates a Todo object from a string array formatted for file loading.
   * 
   * @param parts A string array containing Todo data in the format [type, done,
   *              description].
   * @return A Todo object initialized with the data from the array.
   * @throws SparkleException If the input data is in an invalid format.
   */
  public static Todo fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 3) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Oops! Your todo data is a total mess!");
    }
    return new Todo(parts[2], parts[1].equals("1"));
  }

  /**
   * Returns a string representation of the Todo task, including its description
   * and completion status.
   * 
   * @return A string representation of the Todo task in the format
   *         "[T][description]".
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
