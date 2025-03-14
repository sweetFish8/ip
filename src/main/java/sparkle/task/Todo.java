package sparkle.task;

import sparkle.exception.SparkleException;

public class Todo extends Task {
  public Todo(String description) {
    super(description);
  }

  public Todo(String description, boolean isDone) {
    super(description, isDone);
  }

  @Override
  public String toFileFormat() {
    return "T | " + super.toFileFormat();
  }

  public static Todo fromFileFormat(String[] parts) throws SparkleException {
    if (parts.length < 3) {
      throw new SparkleException(SparkleException.ErrorType.INVALID_FORMAT, "Corrupted Todo data");
    }
    return new Todo(parts[2], parts[1].equals("1"));
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
