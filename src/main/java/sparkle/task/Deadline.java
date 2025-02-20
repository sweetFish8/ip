package sparkle.task;

import sparkle.exception.SparkleException;

public class Deadline extends Task {
  protected String by;

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  public Deadline(String description, boolean isDone, String by) {
    super(description, isDone);
    this.by = by;
  }

  @Override
  public String toFileFormat() {
    return "D | " + super.toFileFormat() + " | " + by;
  }

  public static Deadline fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 4) {
      throw new SparkleException(
          SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Deadline data");
    }
    return new Deadline(parts[2], parts[1].equals("1"), parts[3]);
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}
