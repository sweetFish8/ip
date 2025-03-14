package sparkle.task;

import sparkle.exception.SparkleException;

/**
 * Represents a deadline task that has a specified deadline time. Inherits from the Task class and
 * includes functionality for handling deadlines.
 */
public class Deadline extends Task {
  // Field to store the deadline time
  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  public Deadline(String description, boolean isDone, String by) {
    super(description, isDone);
    this.by = by;
  }

  /**
   * Converts the deadline task into a string format suitable for saving to a file.
   *
   * @return A string representation of the deadline in a format suitable for storage.
   */
  @Override
  public String toFileFormat() {
    return "D | " + super.toFileFormat() + " | " + by;
  }

  /**
   * Creates a Deadline object from a string array formatted for file loading.
   *
   * @param parts A string array containing deadline data in the format [type, done, description,
   *     by].
   * @return A Deadline object initialized with the data from the array.
   * @throws SparkleException If the input data is in an invalid format.
   */
  public static Deadline fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 4) {
      throw new SparkleException(
          SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Deadline data");
    }
    return new Deadline(parts[2], parts[1].equals("1"), parts[3]);
  }

  /**
   * Returns a string representation of the deadline task, including its description and deadline
   * time.
   *
   * @return A string representation of the deadline task in the format "[D][description] (by:
   *     [deadline])".
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}
